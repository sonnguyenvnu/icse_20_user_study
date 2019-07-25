public boolean exists(@NotNull Project project){
  if (!FileUtil.isAbsolute(this.path)) {
    return VfsUtil.findRelativeFile(this.path,project.getBaseDir()) != null;
  }
  return new File(this.path).exists();
}
