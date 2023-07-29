package moe.rafal.sketchbook.sketchbook;

import moe.rafal.sketchbook.sketch.SketchRegistry;

public final class SketchbookFactory {

    private SketchbookFactory() {}

    public static Sketchbook produceSketchbook(SketchRegistry sketchRegistry) {
        return new SketchbookImpl(sketchRegistry);
    }
}
