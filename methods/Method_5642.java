private static Alignment parseTextAlignment(String s){
switch (s) {
case "start":
case "left":
    return Alignment.ALIGN_NORMAL;
case "center":
case "middle":
  return Alignment.ALIGN_CENTER;
case "end":
case "right":
return Alignment.ALIGN_OPPOSITE;
default :
Log.w(TAG,"Invalid alignment value: " + s);
return null;
}
}
