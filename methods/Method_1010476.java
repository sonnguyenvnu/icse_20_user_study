@NotNull @Override public SModel load(@NotNull DataSource dataSource,@NotNull ModelLoadingOption... options) throws UnsupportedDataSourceException, ModelLoadException {
  if (!supports(dataSource)) {
    throw new UnsupportedDataSourceException(dataSource);
  }
  MultiStreamDataSource source=(MultiStreamDataSource)dataSource;
  PersistenceFacility pf=new PersistenceFacility(this,source);
  SModelHeader header;
  try {
    header=pf.readHeader();
  }
 catch (  ModelReadException ignored) {
    LOG.error("Can't read model: ",ignored);
    throw new ModelLoadException("Can't read model: ",Collections.emptyList(),ignored);
  }
  if (header.getModelReference() == null) {
    throw new ModelLoadException("Could not find model reference in the model header while loading from the " + source);
  }
  LOG.debug("Getting model " + header.getModelReference() + " from " + source.getLocation());
  return new DefaultSModelDescriptor(pf,header);
}
