@Override public void initialize(URI uri,Configuration conf) throws IOException {
  Path path=new Path(uri);
  if (path.toString().startsWith(HOODIE_SCHEME_PREFIX)) {
    path=new Path(path.toString().replace(HOODIE_SCHEME_PREFIX,""));
  }
  this.fileSystem=FSUtils.getFs(path.toString(),conf);
  this.uri=uri;
}
