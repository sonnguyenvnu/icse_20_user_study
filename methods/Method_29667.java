private TreeSet<AspectRatio> findCommonAspectRatios(List<Camera.Size> previewSizes,List<Camera.Size> pictureSizes){
  Set<AspectRatio> previewAspectRatios=new HashSet<>();
  for (  Camera.Size size : previewSizes) {
    AspectRatio deviceRatio=AspectRatio.of(CameraKit.Internal.screenHeight,CameraKit.Internal.screenWidth);
    AspectRatio previewRatio=AspectRatio.of(size.width,size.height);
    if (deviceRatio.equals(previewRatio)) {
      previewAspectRatios.add(previewRatio);
    }
  }
  Set<AspectRatio> captureAspectRatios=new HashSet<>();
  for (  Camera.Size size : pictureSizes) {
    captureAspectRatios.add(AspectRatio.of(size.width,size.height));
  }
  TreeSet<AspectRatio> output=new TreeSet<>();
  if (previewAspectRatios.size() == 0) {
    Camera.Size maxSize=previewSizes.get(0);
    AspectRatio maxPreviewAspectRatio=AspectRatio.of(maxSize.width,maxSize.height);
    for (    AspectRatio aspectRatio : captureAspectRatios) {
      if (aspectRatio.equals(maxPreviewAspectRatio)) {
        output.add(aspectRatio);
      }
    }
  }
 else {
    for (    AspectRatio aspectRatio : previewAspectRatios) {
      if (captureAspectRatios.contains(aspectRatio)) {
        output.add(aspectRatio);
      }
    }
  }
  return output;
}
