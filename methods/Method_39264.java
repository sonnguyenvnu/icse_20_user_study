/** 
 * Setups the system email properties.
 */
protected static void setupSystemMailProperties(){
  System.setProperty("mail.mime.encodefilename",Boolean.valueOf(Defaults.mailMimeEncodefilename).toString());
  System.setProperty("mail.mime.decodefilename",Boolean.valueOf(Defaults.mailMimeDecodefilename).toString());
}
