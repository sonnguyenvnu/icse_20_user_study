private String maybeWrap(String filename,String line){
  if (StringUtils.isBlank(linkPrefix)) {
    return filename;
  }
  String newFileName=filename;
  int index=filename.lastIndexOf('.');
  if (index >= 0) {
    newFileName=filename.substring(0,index).replace('\\','/');
  }
  return "<a href=\"" + linkPrefix + newFileName + ".html#" + line + "\">" + newFileName + "</a>";
}
