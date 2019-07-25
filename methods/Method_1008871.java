/** 
 * {@inheritDoc} 
 */
public int setup(int num,FontInfo fontInfo){
  if (embedFontInfoList == null) {
    return num;
  }
  String internalName=null;
  for (int i=0; i < embedFontInfoList.size(); i++) {
    EmbedFontInfo embedFontInfo=(EmbedFontInfo)embedFontInfoList.get(i);
    internalName="F" + num;
    num++;
    LazyFont font=new LazyFont(embedFontInfo,this.fontResolver);
    fontInfo.addMetrics(internalName,font);
    List triplets=embedFontInfo.getFontTriplets();
    for (int tripletIndex=0; tripletIndex < triplets.size(); tripletIndex++) {
      FontTriplet triplet=(FontTriplet)triplets.get(tripletIndex);
      fontInfo.addFontProperties(internalName,triplet);
    }
  }
  return num;
}
