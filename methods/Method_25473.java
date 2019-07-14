private static Path buildRootPath(){
  Path root=Iterables.getFirst(FileSystems.getDefault().getRootDirectories(),null);
  if (root == null) {
    throw new RuntimeException("Can't find a root filesystem!");
  }
  return root;
}
