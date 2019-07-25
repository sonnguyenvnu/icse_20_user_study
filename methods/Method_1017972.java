@Nonnull @Override public R option(@Nonnull final String key,@Nullable final String value){
  if (key.startsWith(KyloCatalogConstants.HADOOP_CONF_PREFIX)) {
    hadoopConfiguration.set(key.substring(KyloCatalogConstants.HADOOP_CONF_PREFIX.length()),value);
  }
  options.setOption(key,value);
  return (R)this;
}
