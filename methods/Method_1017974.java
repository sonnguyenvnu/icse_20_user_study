@Nonnull @Override public KyloCatalogReader<T> read(@Nonnull final String id){
  final DataSetTemplate dataSet=(dataSets != null) ? dataSets.get(id) : null;
  if (dataSet != null) {
    final DefaultKyloCatalogReader<T> reader=new DefaultKyloCatalogReader<>(this,hadoopConfiguration,resourceLoader);
    reader.dataSet(dataSet);
    return reader;
  }
 else {
    throw new KyloCatalogException("Data set does not exist: " + id);
  }
}
