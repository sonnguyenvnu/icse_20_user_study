/** 
 * Remove extra quotes, slashes, and garbage from the Windows PATH.
 */
protected void checkPath(){
  String path=System.getProperty("java.library.path");
  String[] pieces=PApplet.split(path,File.pathSeparatorChar);
  String[] legit=new String[pieces.length];
  int legitCount=0;
  for (  String item : pieces) {
    if (item.startsWith("\"")) {
      item=item.substring(1);
    }
    if (item.endsWith("\"")) {
      item=item.substring(0,item.length() - 1);
    }
    if (item.endsWith(File.separator)) {
      item=item.substring(0,item.length() - File.separator.length());
    }
    File directory=new File(item);
    if (!directory.exists()) {
      continue;
    }
    if (item.trim().length() == 0) {
      continue;
    }
    legit[legitCount++]=item;
  }
  legit=PApplet.subset(legit,0,legitCount);
  String newPath=PApplet.join(legit,File.pathSeparator);
  if (!newPath.equals(path)) {
    System.setProperty("java.library.path",newPath);
  }
}
