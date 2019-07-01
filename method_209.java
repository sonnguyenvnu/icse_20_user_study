@Override public int _XXXXX_(final InputStream is) throws IOException {
  int red=readSample(is,bytesPerSample);
  int green=readSample(is,bytesPerSample);
  int blue=readSample(is,bytesPerSample);
  red=scaleSample(red,scale,max);
  green=scaleSample(green,scale,max);
  blue=scaleSample(blue,scale,max);
  final int alpha=0xff;
  return ((0xff & alpha) << 24) | ((0xff & red) << 16) | ((0xff & green) << 8)| ((0xff & blue) << 0);
}