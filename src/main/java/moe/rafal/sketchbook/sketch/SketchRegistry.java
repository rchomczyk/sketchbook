package moe.rafal.sketchbook.sketch;

import java.util.Set;

public interface SketchRegistry {

    void register(Sketch sketch);

    Set<Sketch> getRegisteredSketches();
}
