private String compute(OdfStyleBase style,StringBuilder classNames,boolean first,String prefix){
  if (style == null) {
    return null;
  }
  String familyName=style.getFamilyName();
  String styleName=null;
  if (style instanceof StyleStyleElement) {
    styleName=((StyleStyleElement)style).getStyleNameAttribute();
  }
 else   if (style instanceof StylePageLayoutElement) {
    styleName=((StylePageLayoutElement)style).getStyleNameAttribute();
  }
  String className=getClassName(familyName,styleName);
  if (prefix != null) {
    className=className + prefix;
  }
  if (first) {
    classNames.append(className);
  }
 else {
    classNames.insert(0,' ');
    classNames.insert(0,className);
  }
  style=style.getParentStyle();
  if (style != null) {
    compute(style,classNames,false,prefix);
  }
  return className;
}
