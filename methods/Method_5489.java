@Override protected SsManifest loadManifest(Uri uri) throws IOException {
  DataSource dataSource=manifestDataSourceFactory.createDataSource();
  Uri fixedUri=SsUtil.fixManifestUri(uri);
  return ParsingLoadable.load(dataSource,new SsManifestParser(),fixedUri,C.DATA_TYPE_MANIFEST);
}
