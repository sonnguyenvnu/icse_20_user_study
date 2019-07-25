@Override public void upgrade(@NotNull DataSource dataSource) throws IOException {
  if (!supports(dataSource)) {
    throw new UnsupportedDataSourceException(dataSource);
  }
  MultiStreamDataSource source=(MultiStreamDataSource)dataSource;
  try {
    ModelLoadResult model=FilePerRootFormatUtil.readModel(null,source,ModelLoadingState.FULLY_LOADED);
    FilePerRootFormatUtil.saveModel(model.getModel(),source,ModelPersistence.LAST_VERSION);
  }
 catch (  ModelReadException ex) {
    throw new IOException(ex.getMessage(),ex);
  }
}
