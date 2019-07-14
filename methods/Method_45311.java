private static Path searchPath(final Path path,final int globIndex){
  Path root=path.getRoot();
  Path subpath=path.subpath(0,globIndex);
  if (root == null) {
    return subpath;
  }
  return Paths.get(root.toString(),subpath.toString());
}
