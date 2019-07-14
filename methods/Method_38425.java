/** 
 * Defines Decora tag position inside decorator content. Resets current Decora tag tracking.
 */
protected void defineDecoraTag(){
  DecoraTag decoraTag=decoraTagDefaultValueStart == 0 ? new DecoraTag(decoraTagName,decoraIdName,decoraTagStart,decoraTagEnd) : new DecoraTag(decoraTagName,decoraIdName,decoraTagStart,decoraTagEnd,decoraTagDefaultValueStart,decoraTagDefaultValueEnd - decoraTagDefaultValueStart);
  decoraTags.add(decoraTag);
  decoraTagName=null;
  decoraIdName=null;
  closingTagName=null;
  decoraTagDefaultValueStart=0;
}
