public boolean parseString(String string){
  if (string.length() < 6) {
    return false;
  }
  try {
    String args[]=string.split("_");
    if (args.length >= 10) {
      startTime=Long.parseLong(args[1]);
      endTime=Long.parseLong(args[2]);
      rotationValue=Integer.parseInt(args[3]);
      originalWidth=Integer.parseInt(args[4]);
      originalHeight=Integer.parseInt(args[5]);
      bitrate=Integer.parseInt(args[6]);
      resultWidth=Integer.parseInt(args[7]);
      resultHeight=Integer.parseInt(args[8]);
      int pathStart;
      if (args.length >= 11) {
        try {
          framerate=Integer.parseInt(args[9]);
        }
 catch (        Exception ignore) {
        }
      }
      if (framerate <= 0 || framerate > 400) {
        pathStart=9;
        framerate=25;
      }
 else {
        pathStart=10;
      }
      for (int a=pathStart; a < args.length; a++) {
        if (originalPath == null) {
          originalPath=args[a];
        }
 else {
          originalPath+="_" + args[a];
        }
      }
    }
    return true;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return false;
}
