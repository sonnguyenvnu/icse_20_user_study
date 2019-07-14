@Override protected HlsPlaylist getManifest(DataSource dataSource,DataSpec dataSpec) throws IOException {
  return loadManifest(dataSource,dataSpec);
}
