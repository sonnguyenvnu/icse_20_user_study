@Override public Long convert(final Object object) throws ConversionException {
  return ((HyperLogLogPlus)object).cardinality();
}
