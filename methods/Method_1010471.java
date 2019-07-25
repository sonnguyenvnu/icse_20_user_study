@Nullable @Override public DataSourceFactoryFromName spawn(@NotNull DataSourceType dataSourceType){
  if (dataSourceType instanceof FileExtensionDataSourceType) {
    return new RegularFileDataSourceFactory((FileExtensionDataSourceType)dataSourceType);
  }
  return null;
}
