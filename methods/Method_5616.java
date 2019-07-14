@Override protected Subtitle decode(byte[] bytes,int length,boolean reset) throws SubtitleDecoderException {
  parsableByteArray.reset(bytes,length);
  String cueTextString=readSubtitleText(parsableByteArray);
  if (cueTextString.isEmpty()) {
    return Tx3gSubtitle.EMPTY;
  }
  SpannableStringBuilder cueText=new SpannableStringBuilder(cueTextString);
  attachFontFace(cueText,defaultFontFace,DEFAULT_FONT_FACE,0,cueText.length(),SPAN_PRIORITY_LOW);
  attachColor(cueText,defaultColorRgba,DEFAULT_COLOR,0,cueText.length(),SPAN_PRIORITY_LOW);
  attachFontFamily(cueText,defaultFontFamily,DEFAULT_FONT_FAMILY,0,cueText.length(),SPAN_PRIORITY_LOW);
  float verticalPlacement=defaultVerticalPlacement;
  while (parsableByteArray.bytesLeft() >= SIZE_ATOM_HEADER) {
    int position=parsableByteArray.getPosition();
    int atomSize=parsableByteArray.readInt();
    int atomType=parsableByteArray.readInt();
    if (atomType == TYPE_STYL) {
      assertTrue(parsableByteArray.bytesLeft() >= SIZE_SHORT);
      int styleRecordCount=parsableByteArray.readUnsignedShort();
      for (int i=0; i < styleRecordCount; i++) {
        applyStyleRecord(parsableByteArray,cueText);
      }
    }
 else     if (atomType == TYPE_TBOX && customVerticalPlacement) {
      assertTrue(parsableByteArray.bytesLeft() >= SIZE_SHORT);
      int requestedVerticalPlacement=parsableByteArray.readUnsignedShort();
      verticalPlacement=(float)requestedVerticalPlacement / calculatedVideoTrackHeight;
      verticalPlacement=Util.constrainValue(verticalPlacement,0.0f,0.95f);
    }
    parsableByteArray.setPosition(position + atomSize);
  }
  return new Tx3gSubtitle(new Cue(cueText,null,verticalPlacement,Cue.LINE_TYPE_FRACTION,Cue.ANCHOR_TYPE_START,Cue.DIMEN_UNSET,Cue.TYPE_UNSET,Cue.DIMEN_UNSET));
}
