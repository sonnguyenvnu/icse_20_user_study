private String relativize(Path path){
  List<Path> fileDirectories=databaseContext.getFileDirectoriesAsPaths(filePreferences);
  return FileUtil.relativize(path,fileDirectories).toString();
}
