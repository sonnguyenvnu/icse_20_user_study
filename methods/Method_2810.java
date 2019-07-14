public static String baseName(String path){
  if (path == null || path.length() == 0)   return "";
  path=path.replaceAll("[/\\\\]+","/");
  int len=path.length(), upCount=0;
  while (len > 0) {
    if (path.charAt(len - 1) == '/') {
      len--;
      if (len == 0)       return "";
    }
    int lastInd=path.lastIndexOf('/',len - 1);
    String fileName=path.substring(lastInd + 1,len);
    if (fileName.equals(".")) {
      len--;
    }
 else     if (fileName.equals("..")) {
      len-=2;
      upCount++;
    }
 else {
      if (upCount == 0)       return fileName;
      upCount--;
      len-=fileName.length();
    }
  }
  return "";
}
