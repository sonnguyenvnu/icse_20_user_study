private static Point findBestPreviewSizeValue(CharSequence previewSizeValueString,Point screenResolution){
  int bestX=0;
  int bestY=0;
  int diff=Integer.MAX_VALUE;
  for (  String previewSize : COMMA_PATTERN.split(previewSizeValueString)) {
    previewSize=previewSize.trim();
    int dimPosition=previewSize.indexOf('x');
    if (dimPosition < 0) {
      Log.w(TAG,"Bad preview-size: " + previewSize);
      continue;
    }
    int newX;
    int newY;
    try {
      newX=Integer.parseInt(previewSize.substring(0,dimPosition));
      newY=Integer.parseInt(previewSize.substring(dimPosition + 1));
    }
 catch (    NumberFormatException nfe) {
      Log.w(TAG,"Bad preview-size: " + previewSize);
      continue;
    }
    int newDiff=Math.abs(newX - screenResolution.x) + Math.abs(newY - screenResolution.y);
    if (newDiff == 0) {
      bestX=newX;
      bestY=newY;
      break;
    }
 else     if (newDiff < diff) {
      bestX=newX;
      bestY=newY;
      diff=newDiff;
    }
  }
  if (bestX > 0 && bestY > 0) {
    return new Point(bestX,bestY);
  }
  return null;
}
