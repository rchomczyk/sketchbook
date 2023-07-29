package moe.rafal.sketchbook.sketchbook;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import moe.rafal.sketchbook.sketch.Sketch;
import moe.rafal.sketchbook.sketch.SketchRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class SketchbookImpl implements Sketchbook {

    private final SketchRegistry sketchRegistry;

    SketchbookImpl(SketchRegistry sketchRegistry) {
        this.sketchRegistry = sketchRegistry;
    }

    @Override
    public void registerSketch(Sketch sketch) {
        sketchRegistry.register(sketch);
    }

    @Override
    public void registerSketches(String packageName) {
        detectSketches(packageName).forEach(this::registerSketch);
    }

    @Override
    public Set<Sketch> detectSketches(String packageName) {
        try (final ScanResult result = scanClasspath(packageName)) {
            return result.getClassesImplementing(Sketch.class).stream()
                    .map(this::initializeSketchOrThrow)
                    .collect(Collectors.toUnmodifiableSet());
        }
    }

    private ScanResult scanClasspath(String packageName) {
        return new ClassGraph().acceptPackages(packageName).scan();
    }

    private Sketch initializeSketch(ClassInfo sketchClassInfo)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        final Class<?> sketchClass = sketchClassInfo.loadClass();
        final Logger proxiedLogger = Logger.getLogger(sketchClass.getSimpleName());
        return (Sketch) sketchClass.getDeclaredConstructor(Logger.class).newInstance(proxiedLogger);
    }

    private Sketch initializeSketchOrThrow(ClassInfo sketchClassInfo) {
        try {
            return initializeSketch(sketchClassInfo);
        } catch (Exception exception) {
            throw new IllegalStateException("Could not initialize sketch, because of unexpected exception.", exception);
        }
    }

    @Override
    public void runAllSketches() {
        sketchRegistry.getRegisteredSketches().forEach(Sketch::run);
    }
}
