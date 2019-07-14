public int getTop(){
  try {
    return parseRegionLength(getAttribute(TOP_ATTRIBUTE_NAME),false);
  }
 catch (  NumberFormatException e) {
    if (LOCAL_LOGV) {
      Timber.v("Top attribute is not set or incorrect.");
    }
  }
  try {
    int bbh=((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getHeight();
    int bottom=parseRegionLength(getAttribute(BOTTOM_ATTRIBUTE_NAME),false);
    int height=parseRegionLength(getAttribute(HEIGHT_ATTRIBUTE_NAME),false);
    return bbh - bottom - height;
  }
 catch (  NumberFormatException e) {
    if (LOCAL_LOGV) {
      Timber.v("Bottom or height attribute is not set or incorrect.");
    }
  }
  return 0;
}
