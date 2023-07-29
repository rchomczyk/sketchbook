package moe.rafal.sketchbook.sketchbook;

import moe.rafal.sketchbook.sketch.Sketch;

import java.util.Set;

public interface Sketchbook {

    void registerSketch(Sketch sketch);

    void registerSketches(String packageName);

    Set<Sketch> detectSketches(String packageName);

    void runAllSketches();
}
