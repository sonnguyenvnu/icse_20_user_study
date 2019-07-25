@NotNull @Override public Navigatable create(@NotNull Object o){
  final FileWithLogicalPosition pos=(FileWithLogicalPosition)o;
  final VirtualFile vf=VirtualFileUtils.getProjectVirtualFile(pos.getFile());
  if (vf == null) {
    return NonNavigatable.INSTANCE;
  }
  return new VirtualFileNavigatable(myProject,vf).at(pos.getLine(),pos.getColumn());
}
