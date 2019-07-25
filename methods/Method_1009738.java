@Override public Point init(Context context,Uri uri) throws Exception {
  decoder=BitmapDecoder.from(context,uri);
  decoder.useBuiltInDecoder(true);
  return new Point(decoder.sourceWidth(),decoder.sourceHeight());
}
