private static int parsePositionAnchor(String s){
switch (s) {
case "start":
    return Cue.ANCHOR_TYPE_START;
case "center":
case "middle":
  return Cue.ANCHOR_TYPE_MIDDLE;
case "end":
return Cue.ANCHOR_TYPE_END;
default :
Log.w(TAG,"Invalid anchor value: " + s);
return Cue.TYPE_UNSET;
}
}
