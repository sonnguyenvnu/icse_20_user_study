/** 
 * Writes decorated content.
 */
protected void writeDecoratedPage(final Writer out,final char[] decoratorContent,final char[] pageContent,final DecoraTag[] decoraTags) throws IOException {
  int ndx=0;
  for (  DecoraTag decoraTag : decoraTags) {
    int decoratorLen=decoraTag.getStartIndex() - ndx;
    if (decoratorLen <= 0) {
      continue;
    }
    out.write(decoratorContent,ndx,decoratorLen);
    ndx=decoraTag.getEndIndex();
    int regionLen=decoraTag.getRegionLength();
    if (regionLen == 0) {
      if (decoraTag.hasDefaultValue()) {
        out.write(decoratorContent,decoraTag.getDefaultValueStart(),decoraTag.getDefaultValueLength());
      }
    }
 else {
      writeRegion(out,pageContent,decoraTag,decoraTags);
    }
  }
  out.write(decoratorContent,ndx,decoratorContent.length - ndx);
}
