@Override public Iterable<? extends Row> apply(final Dataset<Row> dataset){
  return dataset::toLocalIterator;
}
