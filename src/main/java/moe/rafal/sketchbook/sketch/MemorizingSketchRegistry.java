package moe.rafal.sketchbook.sketch;

import java.util.HashSet;
import java.util.Set;

class MemorizingSketchRegistry implements SketchRegistry {

    private final Set<Sketch> sketches;

    MemorizingSketchRegistry() {
        this.sketches = new HashSet<>();
    }

    @Override
    public void register(Sketch sketch) {
        sketches.add(sketch);
    }

    @Override
    public Set<Sketch> getRegisteredSketches() {
        return sketches;
    }
}
