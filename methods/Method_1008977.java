/** 
 * deObfuscate this font, and save it using fontName
 * @param fontNameAsInTablePart - the name to save the font as. Wecould read the font name from the deObfuscated data, but FontLoader can't readily load from a byte array. 
 * @param fontKey
 */
public PhysicalFont extract(String fontNameAsInTablePart,String fontFileName,String fontKey,String filenamePrefix){
  byte[] fontData=this.getBytes();
  log.debug("bytes: " + fontData.length);
  log.info("deObfuscating '" + fontFileName + "' with fontkey: " + fontKey);
  String tmpString=fontKey.substring(1,fontKey.length() - 1);
  log.debug(tmpString);
  String guidString=tmpString.replace(target,replacement);
  log.debug(guidString);
  byte[] guidByteArray=new byte[16];
  for (int i=0; i < guidByteArray.length; i++) {
    guidByteArray[i]=fromHexString(guidString.substring(i * 2,(i * 2) + 2));
  }
  for (int j=0; j < 2; j++) {
    for (int i=0; i < 16; i++) {
      fontData[(j * 16) + i]^=guidByteArray[15 - i];
    }
  }
  setF(new File(getTmpFontDir(),filenamePrefix + "-" + fontFileName + ".ttf"));
  getF().deleteOnExit();
  String path=null;
  java.io.FileOutputStream fos=null;
  try {
    path=getF().getCanonicalPath();
    fos=new java.io.FileOutputStream(getF());
    fos.write(fontData);
    log.debug("wrote: " + fontData.length);
    fos.close();
  }
 catch (  IOException e) {
    log.error("Problem with " + path);
    log.error(e.getMessage(),e);
  }
  FontResolver fontResolver=FontSetup.createMinimalFontResolver();
  if (log.isDebugEnabled()) {
    CustomFont customFont=null;
    try {
      log.debug("Loading from: " + path);
      String subFontName=null;
      boolean embedded=true;
      boolean useKerning=true;
      customFont=FontLoader.loadFont("file:" + path,subFontName,embedded,EncodingMode.AUTO,useKerning,fontResolver);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
    if (customFont != null) {
      log.info("Successfully reloaded " + customFont.getFontName());
      if (customFont.isEmbeddable()) {
        log.debug("confirmed embeddable");
      }
 else {
        log.error("this embedded font claims it is not embeddable!");
      }
    }
  }
  try {
    List<PhysicalFont> fonts=PhysicalFonts.getPhysicalFont(fontNameAsInTablePart,new java.net.URL("file:" + path));
    return (fonts == null || fonts.isEmpty()) ? null : fonts.iterator().next();
  }
 catch (  MalformedURLException e) {
    e.printStackTrace();
  }
  return null;
}
