@Override public scala.collection.mutable.Map<String,Long> convert(final Object object) throws ConversionException {
  return JavaConverters.mapAsScalaMapConverter((FreqMap)object).asScala();
}
