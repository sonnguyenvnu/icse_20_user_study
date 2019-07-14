private static int delimiterLength(int encodingByte){
  return (encodingByte == ID3_TEXT_ENCODING_ISO_8859_1 || encodingByte == ID3_TEXT_ENCODING_UTF_8) ? 1 : 2;
}
