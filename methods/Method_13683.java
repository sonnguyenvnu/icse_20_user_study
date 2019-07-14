@Override public Resource createRelative(String relativePath) throws IOException {
  return new OssStorageResource(this.oss,this.location.resolve(relativePath).toString());
}
