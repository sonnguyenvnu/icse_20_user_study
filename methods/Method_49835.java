public int getLeft(){
  try {
    return parseRegionLength(getAttribute(LEFT_ATTRIBUTE_NAME),true);
  }
 catch (  NumberFormatException e) {
    if (LOCAL_LOGV) {
      Timber.v("Left attribute is not set or incorrect.");
    }
  }
  try {
    int bbw=((SMILDocument)getOwnerDocument()).getLayout().getRootLayout().getWidth();
    int right=parseRegionLength(getAttribute(RIGHT_ATTRIBUTE_NAME),true);
    int width=parseRegionLength(getAttribute(WIDTH_ATTRIBUTE_NAME),true);
    return bbw - right - width;
  }
 catch (  NumberFormatException e) {
    if (LOCAL_LOGV) {
      Timber.v("Right or width attribute is not set or incorrect.");
    }
  }
  return 0;
}
