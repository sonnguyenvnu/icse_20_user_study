@Nonnull @Override public KyloCatalogWriter<T> write(@Nonnull final T df){
  return new DefaultKyloCatalogWriter<>(this,hadoopConfiguration,resourceLoader,df);
}
