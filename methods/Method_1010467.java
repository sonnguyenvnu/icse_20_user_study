@Override public void save(@NotNull SModel model,@NotNull DataSource dataSource) throws IOException {
  if (!(dataSource instanceof StreamDataSource)) {
    throw new UnsupportedDataSourceException(dataSource);
  }
  BinaryPersistence.writeModel(((SModelBase)model).getSModel(),(StreamDataSource)dataSource);
}
