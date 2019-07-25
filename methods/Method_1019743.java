@Override public void visit(TextListLevelStyleNumberElement ele){
  Integer textLevel=ele.getTextLevelAttribute();
  if (currentListPropertiesMap == null || textLevel == null) {
    return;
  }
  StyleListProperties listProperties=new StyleListProperties();
  currentListPropertiesMap.put(textLevel,listProperties);
  String styleName=ele.getTextStyleNameAttribute();
  if (StringUtils.isNotEmpty(styleName)) {
    Style style=getStyle(OdfStyleFamily.Text.getName(),styleName,null);
    if (style != null) {
      listProperties.setTextProperties(style.getTextProperties());
    }
  }
  Integer startValue=ele.getTextStartValueAttribute();
  if (startValue != null) {
    listProperties.setStartValue(startValue);
  }
  Integer displayLevels=ele.getTextDisplayLevelsAttribute();
  if (displayLevels != null) {
    listProperties.setDisplayLevels(displayLevels);
  }
  String numPrefix=ele.getStyleNumPrefixAttribute();
  if (StringUtils.isNotEmpty(numPrefix)) {
    listProperties.setNumPrefix(numPrefix);
  }
  String numSuffix=ele.getStyleNumSuffixAttribute();
  if (StringUtils.isNotEmpty(numSuffix)) {
    listProperties.setNumSuffix(numSuffix);
  }
  String numFormat=ele.getStyleNumFormatAttribute();
  if (StringUtils.isNotEmpty(numFormat)) {
    if (StyleNumFormatAttribute.Value.a.toString().equals(numFormat)) {
      listProperties.setNumFormat(StyleNumFormat.createAlphabetical(true));
    }
 else     if (StyleNumFormatAttribute.Value.A.toString().equals(numFormat)) {
      listProperties.setNumFormat(StyleNumFormat.createAlphabetical(false));
    }
 else     if (StyleNumFormatAttribute.Value.i.toString().equals(numFormat)) {
      listProperties.setNumFormat(StyleNumFormat.createRoman(true));
    }
 else     if (StyleNumFormatAttribute.Value.I.toString().equals(numFormat)) {
      listProperties.setNumFormat(StyleNumFormat.createRoman(false));
    }
 else {
      listProperties.setNumFormat(StyleNumFormat.createNumerical());
    }
  }
  currentListProperties=listProperties;
  super.visit(ele);
  currentListProperties=null;
}
