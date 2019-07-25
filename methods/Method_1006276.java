private Path relativize(Path path){
  List<Path> fileDirectories=getFileDirectoriesAsPaths();
  return FileHelper.relativize(path,fileDirectories);
}
