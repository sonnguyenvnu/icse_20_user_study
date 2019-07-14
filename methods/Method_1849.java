public static ImageTranscoderFactory getNativeImageTranscoderFactory(final int maxBitmapSize,final boolean useDownSamplingRatio){
  try {
    return (ImageTranscoderFactory)Class.forName("com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory").getConstructor(Integer.TYPE,Boolean.TYPE).newInstance(maxBitmapSize,useDownSamplingRatio);
  }
 catch (  NoSuchMethodException|SecurityException|InstantiationException|InvocationTargetException|IllegalAccessException|IllegalArgumentException|ClassNotFoundException e) {
    throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.",e);
  }
}
