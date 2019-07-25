@Override public boolean accept(@NotNull Project project,@NotNull VirtualFile file){
  return file.getFileType() == MPSFileTypeFactory.MPS_FILE_TYPE;
}
