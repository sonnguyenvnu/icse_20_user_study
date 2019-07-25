@Override public boolean accept(@NotNull Project project,@NotNull VirtualFile file){
  return file instanceof MPSLanguageVirtualFile;
}
