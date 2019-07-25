public static RendererStrategy create(SVGRenderer renderer,Canvas canvas){
  if (canvas.isHardwareAccelerated() || renderer.mAlpha != 1.0f) {
    return new BitmapRendererStrategy(renderer);
  }
  if (!canvas.isHardwareAccelerated()) {
    return new PictureRendererStrategy(renderer);
  }
  return new NormalRendererStrategy(renderer);
}
