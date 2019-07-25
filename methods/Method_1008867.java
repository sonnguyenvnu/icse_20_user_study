/** 
 * Attempts to determine EmbedFontInfo from a given font file.
 * @param fontUrl font URL. Assumed to be local.
 * @param resolver font resolver used to resolve font
 * @param fontCache font cache (may be null)
 * @return an array of newly created embed font info. Generally, this arraywill have only one entry, unless the fontUrl is a TrueType Collection
 */
public EmbedFontInfo[] find(URL fontUrl,FontResolver resolver,FontCache fontCache){
  String embedUrl=null;
  embedUrl=fontUrl.toExternalForm();
  long fileLastModified=-1;
  if (fontCache != null) {
    fileLastModified=FontCache.getLastModified(fontUrl);
    if (fontCache.containsFont(embedUrl)) {
      EmbedFontInfo[] fontInfos=fontCache.getFontInfos(embedUrl,fileLastModified);
      if (fontInfos != null) {
        return fontInfos;
      }
    }
 else     if (fontCache.isFailedFont(embedUrl,fileLastModified)) {
      if (log.isDebugEnabled()) {
        log.debug("Skipping font file that failed to load previously: " + embedUrl);
      }
      return null;
    }
  }
  CustomFont customFont=null;
  if (fontUrl.toExternalForm().endsWith(".ttc")) {
    List ttcNames=null;
    String fontFileURI=fontUrl.toExternalForm().trim();
    InputStream in=null;
    try {
      in=FontLoader.openFontUri(resolver,fontFileURI);
      TTFFile ttf=new TTFFile();
      FontFileReader reader=new FontFileReader(in);
      ttcNames=ttf.getTTCnames(reader);
    }
 catch (    Exception e) {
      if (this.eventListener != null) {
        this.eventListener.fontLoadingErrorAtAutoDetection(this,fontFileURI,e);
      }
      return null;
    }
 finally {
      IOUtils.closeQuietly(in);
    }
    List embedFontInfoList=new java.util.ArrayList();
    Iterator ttcNamesIterator=ttcNames.iterator();
    while (ttcNamesIterator.hasNext()) {
      String fontName=(String)ttcNamesIterator.next();
      if (log.isDebugEnabled()) {
        log.debug("Loading " + fontName);
      }
      try {
        TTFFontLoader ttfLoader=new TTFFontLoader(fontFileURI,fontName,true,EncodingMode.AUTO,true,resolver);
        customFont=ttfLoader.getFont();
        if (this.eventListener != null) {
          customFont.setEventListener(this.eventListener);
        }
      }
 catch (      Exception e) {
        if (fontCache != null) {
          fontCache.registerFailedFont(embedUrl,fileLastModified);
        }
        if (this.eventListener != null) {
          this.eventListener.fontLoadingErrorAtAutoDetection(this,embedUrl,e);
        }
        continue;
      }
      EmbedFontInfo fi=getFontInfoFromCustomFont(fontUrl,customFont,fontCache);
      if (fi != null) {
        embedFontInfoList.add(fi);
      }
    }
    return (EmbedFontInfo[])embedFontInfoList.toArray(new EmbedFontInfo[embedFontInfoList.size()]);
  }
 else {
    try {
      customFont=FontLoader.loadFont(fontUrl,null,true,EncodingMode.AUTO,resolver);
      if (this.eventListener != null) {
        customFont.setEventListener(this.eventListener);
      }
    }
 catch (    Exception e) {
      if (fontCache != null) {
        fontCache.registerFailedFont(embedUrl,fileLastModified);
      }
      if (this.eventListener != null) {
        this.eventListener.fontLoadingErrorAtAutoDetection(this,embedUrl,e);
      }
      return null;
    }
    EmbedFontInfo fi=getFontInfoFromCustomFont(fontUrl,customFont,fontCache);
    if (fi != null) {
      return new EmbedFontInfo[]{fi};
    }
 else {
      return null;
    }
  }
}
