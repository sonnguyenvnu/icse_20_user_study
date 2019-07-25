@Nonnull @Override public KyloCatalogReader<T> read(){
  return new DefaultKyloCatalogReader<>(this,hadoopConfiguration,resourceLoader);
}
