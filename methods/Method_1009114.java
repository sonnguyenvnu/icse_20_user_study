/** 
 * Used by HTML and iText based PDF conversions
 * @param renderer
 * @param fontName
 * @param fm
 */
protected void embed(org.xhtmlrenderer.pdf.ITextRenderer renderer,PhysicalFont pf){
  try {
    if (pf.getEmbeddedFile().endsWith(".pfb")) {
      String afm=FontUtils.pathFromURL(pf.getEmbeddedFile());
      afm=afm.substring(0,afm.length() - 4) + ".afm";
      File f=new File(afm);
      if (f.exists()) {
        try {
          renderer.getFontResolver().addFont(afm,BaseFont.IDENTITY_H,true,FontUtils.pathFromURL(pf.getEmbeddedFile()));
        }
 catch (        java.io.UnsupportedEncodingException uee) {
          uee.printStackTrace();
          log.error(pf.getName() + " does not support UTF encoding");
        }
      }
 else {
        String pfm=FontUtils.pathFromURL(pf.getEmbeddedFile());
        pfm=pfm.substring(0,pfm.length() - 4) + ".pfm";
        log.info("Looking for: " + pfm);
        f=new File(pfm);
        if (f.exists()) {
          try {
            renderer.getFontResolver().addFont(pfm,BaseFont.IDENTITY_H,true,FontUtils.pathFromURL(pf.getEmbeddedFile()));
          }
 catch (          java.io.UnsupportedEncodingException uee) {
            uee.printStackTrace();
            log.error(pf.getName() + " does not support UTF encoding");
          }
        }
 else {
          log.error("Couldn't find afm or pfm corresponding to " + pf.getEmbeddedFile());
        }
      }
    }
 else {
      renderer.getFontResolver().addFont(FontUtils.pathFromURL(pf.getEmbeddedFile()),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
    }
  }
 catch (  java.io.IOException e) {
    e.printStackTrace();
    log.warn("Shouldn't happen - should have been detected upstream ... " + e.getMessage() + ": " + pf.getEmbeddedFile());
  }
catch (  Exception e) {
    e.printStackTrace();
    log.error("Shouldn't happen - should have been detected upstream ... " + e.getMessage());
  }
}
