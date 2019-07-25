public static StaticStickerNormalFilter hit(final Vector3 target,final List<GLImageFilter> mFilters){
  for (  GLImageFilter glImageFilter : mFilters) {
    if (glImageFilter instanceof StaticStickerNormalFilter) {
      StaticStickerNormalFilter staticStickerNormalFilter=((StaticStickerNormalFilter)glImageFilter);
      screenToStageCoordinates(staticStickerNormalFilter.camera,target);
      return ((StaticStickerNormalFilter)glImageFilter).hit(target);
    }
  }
  return null;
}
