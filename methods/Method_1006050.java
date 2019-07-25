private String relativize(Path filePath){
  List<Path> fileDirectories=database.getFileDirectoriesAsPaths(preferences.getFilePreferences());
  return FileUtil.relativize(filePath,fileDirectories).toString();
}
