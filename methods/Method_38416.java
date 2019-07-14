/** 
 * Parses target page and extracts Decora regions for replacements.
 */
protected void parsePage(final char[] pageContent,final DecoraTag[] decoraTags){
  LagartoParser lagartoParser=new LagartoParser(pageContent);
  PageRegionExtractor writer=new PageRegionExtractor(decoraTags);
  lagartoParser.parse(writer);
}
