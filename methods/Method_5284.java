@Override protected DashManifest getManifest(DataSource dataSource,DataSpec dataSpec) throws IOException {
  return ParsingLoadable.load(dataSource,new DashManifestParser(),dataSpec,C.DATA_TYPE_MANIFEST);
}
