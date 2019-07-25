@Memoized public String packaging(){
  return FilenameUtils.getExtension(realDependencyFile().getName());
}
