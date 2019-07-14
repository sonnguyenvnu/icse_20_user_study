/** 
 * Determines the encoding of the given bytes, assuming this is a XML document, which specifies the encoding in the first 1024 bytes.
 * @param bytes the input bytes, might be more or less than 1024 bytes
 * @return the determined encoding, falls back to the default UTF-8 encoding
 */
String determineEncoding(byte[] bytes){
  String firstBytes=new String(bytes,0,bytes.length > 1024 ? 1024 : bytes.length,Charset.forName("ISO-8859-1"));
  Matcher matcher=ENCODING_PATTERN.matcher(firstBytes);
  String encoding=Charset.forName("UTF-8").name();
  if (matcher.find()) {
    encoding=matcher.group(1);
  }
  return encoding;
}
