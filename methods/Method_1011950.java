@Override public boolean accept(@NotNull Project project,@NotNull VirtualFile file){
  return file instanceof MPSNodeVirtualFile || file.getFileType() == MPSFileTypeFactory.MPS_ROOT_FILE_TYPE;
}
