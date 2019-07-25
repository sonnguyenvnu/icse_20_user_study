@Override public String format(String field){
  if (field == null) {
    return "";
  }
  List<LinkedFile> fileList=FileFieldParser.parse(field);
  LinkedFile link=null;
  if (fileType == null) {
    if (!(fileList.isEmpty())) {
      link=fileList.get(0);
    }
  }
 else {
    for (    LinkedFile flEntry : fileList) {
      if (flEntry.getFileType().equalsIgnoreCase(fileType)) {
        link=flEntry;
        break;
      }
    }
  }
  if (link == null) {
    return "";
  }
  List<String> dirs;
  if (prefs.getFileDirForDatabase() == null) {
    dirs=prefs.getGeneratedDirForDatabase();
  }
 else {
    dirs=prefs.getFileDirForDatabase();
  }
  return link.findIn(dirs.stream().map(Paths::get).collect(Collectors.toList())).map(path -> path.normalize().toString()).orElse(link.getLink());
}
