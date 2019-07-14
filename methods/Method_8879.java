public boolean hasChanges(){
  if (lastState != null) {
    return enhanceValue != lastState.enhanceValue || contrastValue != lastState.contrastValue || highlightsValue != lastState.highlightsValue || exposureValue != lastState.exposureValue || warmthValue != lastState.warmthValue || saturationValue != lastState.saturationValue || vignetteValue != lastState.vignetteValue || shadowsValue != lastState.shadowsValue || grainValue != lastState.grainValue || sharpenValue != lastState.sharpenValue || fadeValue != lastState.fadeValue || tintHighlightsColor != lastState.tintHighlightsColor || tintShadowsColor != lastState.tintShadowsColor || !curvesToolValue.shouldBeSkipped();
  }
 else {
    return enhanceValue != 0 || contrastValue != 0 || highlightsValue != 0 || exposureValue != 0 || warmthValue != 0 || saturationValue != 0 || vignetteValue != 0 || shadowsValue != 0 || grainValue != 0 || sharpenValue != 0 || fadeValue != 0 || tintHighlightsColor != 0 || tintShadowsColor != 0 || !curvesToolValue.shouldBeSkipped();
  }
}
