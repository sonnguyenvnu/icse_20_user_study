/** 
 * Writes region to output, but extracts all inner regions.
 */
protected void writeRegion(final Writer out,final char[] pageContent,final DecoraTag decoraTag,final DecoraTag[] decoraTags) throws IOException {
  int regionStart=decoraTag.getRegionStart();
  int regionLen=decoraTag.getRegionLength();
  int regionEnd=regionStart + regionLen;
  for (  DecoraTag innerDecoraTag : decoraTags) {
    if (decoraTag == innerDecoraTag) {
      continue;
    }
    if (decoraTag.isRegionUndefined()) {
      continue;
    }
    if (innerDecoraTag.isInsideOtherTagRegion(decoraTag)) {
      out.write(pageContent,regionStart,innerDecoraTag.getRegionTagStart() - regionStart);
      regionStart=innerDecoraTag.getRegionTagEnd();
    }
  }
  out.write(pageContent,regionStart,regionEnd - regionStart);
}
