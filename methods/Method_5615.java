private void decodeInitializationData(List<byte[]> initializationData){
  if (initializationData != null && initializationData.size() == 1 && (initializationData.get(0).length == 48 || initializationData.get(0).length == 53)) {
    byte[] initializationBytes=initializationData.get(0);
    defaultFontFace=initializationBytes[24];
    defaultColorRgba=((initializationBytes[26] & 0xFF) << 24) | ((initializationBytes[27] & 0xFF) << 16) | ((initializationBytes[28] & 0xFF) << 8) | (initializationBytes[29] & 0xFF);
    String fontFamily=Util.fromUtf8Bytes(initializationBytes,43,initializationBytes.length - 43);
    defaultFontFamily=TX3G_SERIF.equals(fontFamily) ? C.SERIF_NAME : C.SANS_SERIF_NAME;
    calculatedVideoTrackHeight=20 * initializationBytes[25];
    customVerticalPlacement=(initializationBytes[0] & 0x20) != 0;
    if (customVerticalPlacement) {
      int requestedVerticalPlacement=((initializationBytes[10] & 0xFF) << 8) | (initializationBytes[11] & 0xFF);
      defaultVerticalPlacement=(float)requestedVerticalPlacement / calculatedVideoTrackHeight;
      defaultVerticalPlacement=Util.constrainValue(defaultVerticalPlacement,0.0f,0.95f);
    }
 else {
      defaultVerticalPlacement=DEFAULT_VERTICAL_PLACEMENT;
    }
  }
 else {
    defaultFontFace=DEFAULT_FONT_FACE;
    defaultColorRgba=DEFAULT_COLOR;
    defaultFontFamily=DEFAULT_FONT_FAMILY;
    customVerticalPlacement=false;
    defaultVerticalPlacement=DEFAULT_VERTICAL_PLACEMENT;
  }
}
