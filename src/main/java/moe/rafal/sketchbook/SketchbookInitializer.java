package moe.rafal.sketchbook;

import moe.rafal.sketchbook.sketch.SketchRegistry;
import moe.rafal.sketchbook.sketch.SketchRegistryFactory;
import moe.rafal.sketchbook.sketchbook.Sketchbook;
import moe.rafal.sketchbook.sketchbook.SketchbookFactory;

final class SketchbookInitializer {

    private static final String SKETCHES_PACKAGE_NAME = "moe.rafal.sketchbook.sketches";

    private SketchbookInitializer() {}

    public static void main(String[] args) {
        final SketchRegistry sketchRegistry = SketchRegistryFactory.produceSketchRegistry();
        final Sketchbook sketchbook = SketchbookFactory.produceSketchbook(sketchRegistry);
        sketchbook.registerSketches(SKETCHES_PACKAGE_NAME);
        sketchbook.runAllSketches();
    }
}
