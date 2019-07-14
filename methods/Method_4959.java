/** 
 * Maps encoding byte from ID3v2 frame to a Charset.
 * @param encodingByte The value of encoding byte from ID3v2 frame.
 * @return Charset name.
 */
private static String getCharsetName(int encodingByte){
switch (encodingByte) {
case ID3_TEXT_ENCODING_UTF_16:
    return "UTF-16";
case ID3_TEXT_ENCODING_UTF_16BE:
  return "UTF-16BE";
case ID3_TEXT_ENCODING_UTF_8:
return "UTF-8";
case ID3_TEXT_ENCODING_ISO_8859_1:
default :
return "ISO-8859-1";
}
}
