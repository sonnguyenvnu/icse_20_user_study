private void applyStyleRecord(ParsableByteArray parsableByteArray,SpannableStringBuilder cueText) throws SubtitleDecoderException {
  assertTrue(parsableByteArray.bytesLeft() >= SIZE_STYLE_RECORD);
  int start=parsableByteArray.readUnsignedShort();
  int end=parsableByteArray.readUnsignedShort();
  parsableByteArray.skipBytes(2);
  int fontFace=parsableByteArray.readUnsignedByte();
  parsableByteArray.skipBytes(1);
  int colorRgba=parsableByteArray.readInt();
  attachFontFace(cueText,fontFace,defaultFontFace,start,end,SPAN_PRIORITY_HIGH);
  attachColor(cueText,colorRgba,defaultColorRgba,start,end,SPAN_PRIORITY_HIGH);
}
