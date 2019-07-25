@Override public int mkdir(String s,@mode_t long l){
  ensureNotClosed();
  Optional<PeergosStat> current=getByPath(s);
  if (current.isPresent())   return -ErrorCodes.ENOENT();
  Path path=Paths.get(s);
  String parentPath=path.getParent().toString();
  Optional<PeergosStat> parentOpt=getByPath(parentPath);
  String name=path.getFileName().toString();
  if (!parentOpt.isPresent())   return -ErrorCodes.ENOENT();
  PeergosStat parent=parentOpt.get();
  return mkdir(name,parent.treeNode).isPresent() ? 0 : -ErrorCodes.ENOENT();
}
