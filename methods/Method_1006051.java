private Path relativize(Path path){
  List<Path> fileDirectories=getFileDirectoriesAsPaths();
  return FileUtil.relativize(path,fileDirectories);
}
