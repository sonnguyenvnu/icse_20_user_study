@Override public String format(String field){
  if (field == null) {
    return "";
  }
  StringBuilder sb=new StringBuilder();
  List<LinkedFile> fileList=FileFieldParser.parse(field);
  int piv=1;
  for (  LinkedFile flEntry : fileList) {
    if ((fileType == null) || flEntry.getFileType().equalsIgnoreCase(fileType)) {
      for (      FormatEntry entry : format) {
switch (entry.getType()) {
case STRING:
          sb.append(entry.getString());
        break;
case ITERATION_COUNT:
      sb.append(piv);
    break;
case FILE_PATH:
  List<String> dirs;
if ((prefs.getFileDirForDatabase() == null) || prefs.getFileDirForDatabase().isEmpty()) {
  dirs=prefs.getGeneratedDirForDatabase();
}
 else {
  dirs=prefs.getFileDirForDatabase();
}
String pathString=flEntry.findIn(dirs.stream().map(Paths::get).collect(Collectors.toList())).map(path -> path.toAbsolutePath().toString()).orElse(flEntry.getLink());
sb.append(replaceStrings(pathString));
break;
case RELATIVE_FILE_PATH:
sb.append(replaceStrings(flEntry.getLink()));
break;
case FILE_EXTENSION:
FileHelper.getFileExtension(flEntry.getLink()).ifPresent(extension -> sb.append(replaceStrings(extension)));
break;
case FILE_TYPE:
sb.append(replaceStrings(flEntry.getFileType()));
break;
case FILE_DESCRIPTION:
sb.append(replaceStrings(flEntry.getDescription()));
break;
default :
break;
}
}
piv++;
}
}
return sb.toString();
}
