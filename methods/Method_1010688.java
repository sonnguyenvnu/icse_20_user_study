@Override public boolean move(@NotNull IFile newParent){
  return renameOrMove(new File(new File(newParent.getPath()),myFile.getName()));
}
