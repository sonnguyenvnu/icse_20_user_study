protected void collectPictureSizes(SizeMap sizes,StreamConfigurationMap map){
  for (  android.util.Size size : map.getOutputSizes(ImageFormat.JPEG)) {
    mPictureSizes.add(new Size(size.getWidth(),size.getHeight()));
  }
}
