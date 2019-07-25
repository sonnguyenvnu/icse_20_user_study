public boolean contains(Multihash multihash){
  Path path=getFilePath(multihash);
  File file=root.resolve(path).toFile();
  if (!file.exists()) {
    file=root.resolve(path.getFileName()).toFile();
  }
  return file.exists();
}
