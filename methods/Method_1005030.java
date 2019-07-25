@Override public Double convert(final Object object) throws ConversionException {
  return ((Union)object).getResult().getEstimate();
}
