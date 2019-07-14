private static HashMap<String,Integer> getThemeFileValues(File file,String assetName){
  FileInputStream stream=null;
  HashMap<String,Integer> stringMap=new HashMap<>();
  try {
    byte[] bytes=new byte[1024];
    int currentPosition=0;
    if (assetName != null) {
      file=getAssetFile(assetName);
    }
    stream=new FileInputStream(file);
    int idx;
    int read;
    boolean finished=false;
    themedWallpaperFileOffset=-1;
    while ((read=stream.read(bytes)) != -1) {
      int previousPosition=currentPosition;
      int start=0;
      for (int a=0; a < read; a++) {
        if (bytes[a] == '\n') {
          int len=a - start + 1;
          String line=new String(bytes,start,len - 1);
          if (line.startsWith("WPS")) {
            themedWallpaperFileOffset=currentPosition + len;
            finished=true;
            break;
          }
 else {
            if ((idx=line.indexOf('=')) != -1) {
              String key=line.substring(0,idx);
              String param=line.substring(idx + 1);
              int value;
              if (param.length() > 0 && param.charAt(0) == '#') {
                try {
                  value=Color.parseColor(param);
                }
 catch (                Exception ignore) {
                  value=Utilities.parseInt(param);
                }
              }
 else {
                value=Utilities.parseInt(param);
              }
              stringMap.put(key,value);
            }
          }
          start+=len;
          currentPosition+=len;
        }
      }
      if (previousPosition == currentPosition) {
        break;
      }
      stream.getChannel().position(currentPosition);
      if (finished) {
        break;
      }
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (stream != null) {
        stream.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  return stringMap;
}
