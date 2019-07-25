@Nonnull @Override public KyloCatalogWriter<T> write(@Nonnull final T source,@Nonnull final String targetId){
  final DataSetTemplate dataSet=(dataSets != null) ? dataSets.get(targetId) : null;
  if (dataSet != null) {
    final DefaultKyloCatalogWriter<T> writer=new DefaultKyloCatalogWriter<>(this,hadoopConfiguration,resourceLoader,source);
    writer.dataSet(dataSet);
    return writer;
  }
 else {
    throw new KyloCatalogException("Data set does not exist: " + targetId);
  }
}
