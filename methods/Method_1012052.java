@NotNull @Override public Navigatable create(@NotNull Object o){
  final FileWithPosition pos=(FileWithPosition)o;
  VirtualFile vf=LocalFileSystem.getInstance().findFileByIoFile(pos.getFile());
  if (vf == null) {
    return NonNavigatable.INSTANCE;
  }
  return new VirtualFileNavigatable(myProject,vf).offset(pos.getOffset());
}
