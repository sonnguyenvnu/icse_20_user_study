@Nonnull @Override public KyloCatalogDataSetBuilder<T> option(@Nonnull final String key,@Nullable final String value){
  if (dataSet.getOptions() == null) {
    dataSet.setOptions(new HashMap<String,String>());
  }
  dataSet.getOptions().put(key,value);
  return this;
}
