@Override protected void collectPictureSizes(SizeMap sizes,StreamConfigurationMap map){
  android.util.Size[] outputSizes=map.getHighResolutionOutputSizes(ImageFormat.JPEG);
  if (outputSizes != null) {
    for (    android.util.Size size : map.getHighResolutionOutputSizes(ImageFormat.JPEG)) {
      sizes.add(new Size(size.getWidth(),size.getHeight()));
    }
  }
  if (sizes.isEmpty()) {
    super.collectPictureSizes(sizes,map);
  }
}
