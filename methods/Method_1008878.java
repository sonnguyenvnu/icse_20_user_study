/** 
 * Returns a list of matching font triplet found in a given font info
 * @param fontInfo the font info
 * @return a list of matching font triplets
 */
protected List match(FontInfo fontInfo){
  AttributeValue fontFamilyValue=getFontFamily();
  AttributeValue weightValue=getFontWeight();
  AttributeValue styleValue=getFontStyle();
  List matchingTriplets=new java.util.ArrayList();
  for (Iterator attrIt=fontFamilyValue.iterator(); attrIt.hasNext(); ) {
    String fontFamilyString=(String)attrIt.next();
    Map triplets=(Map)fontInfo.getFontTriplets();
    if (triplets != null) {
      Set tripletSet=triplets.keySet();
      for (Iterator tripletIt=tripletSet.iterator(); tripletIt.hasNext(); ) {
        FontTriplet triplet=(FontTriplet)tripletIt.next();
        String fontName=triplet.getName();
        if (fontFamilyString.toLowerCase().equals(fontName.toLowerCase())) {
          boolean weightMatched=false;
          int fontWeight=triplet.getWeight();
          for (Iterator weightIt=weightValue.iterator(); weightIt.hasNext(); ) {
            Object weightObj=weightIt.next();
            if (weightObj instanceof FontWeightRange) {
              FontWeightRange intRange=(FontWeightRange)weightObj;
              if (intRange.isWithinRange(fontWeight)) {
                weightMatched=true;
              }
            }
 else             if (weightObj instanceof String) {
              String fontWeightString=(String)weightObj;
              int fontWeightValue=FontUtil.parseCSS2FontWeight(fontWeightString);
              if (fontWeightValue == fontWeight) {
                weightMatched=true;
              }
            }
 else             if (weightObj instanceof Integer) {
              Integer fontWeightInteger=(Integer)weightObj;
              int fontWeightValue=fontWeightInteger.intValue();
              if (fontWeightValue == fontWeight) {
                weightMatched=true;
              }
            }
          }
          boolean styleMatched=false;
          String fontStyleString=triplet.getStyle();
          for (Iterator styleIt=styleValue.iterator(); styleIt.hasNext(); ) {
            String style=(String)styleIt.next();
            if (fontStyleString.equals(style)) {
              styleMatched=true;
            }
          }
          if (weightMatched && styleMatched) {
            matchingTriplets.add(triplet);
          }
        }
      }
    }
  }
  return matchingTriplets;
}
