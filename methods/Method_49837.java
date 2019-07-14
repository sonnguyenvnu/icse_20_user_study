private int parseRegionLength(String length,boolean horizontal){
  if (length.endsWith("px")) {
    length=length.substring(0,length.indexOf("px"));
    return Integer.parseInt(length);
  }
 else   if (length.endsWith("%")) {
    double value=0.01 * Integer.parseInt(length.substring(0,length.length() - 1));
    if (horizontal) {
      value*=((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();
    }
 else {
      value*=((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight();
    }
    return (int)Math.round(value);
  }
 else {
    return Integer.parseInt(length);
  }
}
