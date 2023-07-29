package moe.rafal.sketchbook.sketch;

public final class SketchRegistryFactory {

    private SketchRegistryFactory() {}

    public static SketchRegistry produceSketchRegistry() {
        return new MemorizingSketchRegistry();
    }
}
