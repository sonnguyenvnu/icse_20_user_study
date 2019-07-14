@Override protected String realRelativeDirectory(String dir){
  if (dir.endsWith(SEPARATOR))   dir=dir.substring(0,dir.length() - 1);
  return dir.replace(SEPARATOR.toCharArray()[0],'\\');
}
