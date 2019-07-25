/** 
 * Sets up the fonts on a given FontInfo object. The fonts to setup are defined by an array of  {@code FontCollection} objects.
 * @param fontInfo the FontInfo object to set up
 * @param fontCollections the array of font collections/sources
 */
public void setup(FontInfo fontInfo,FontCollection[] fontCollections){
  int startNum=1;
  for (int i=0, c=fontCollections.length; i < c; i++) {
    startNum=fontCollections[i].setup(startNum,fontInfo);
  }
  getFontSubstitutions().adjustFontInfo(fontInfo);
}
