protected void initialize() throws XmlException, IOException {
  List<CTStyle> s=styles.getStyleList();
  for (  CTStyle style : s) {
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.Enum isDefault=style.getDefault();
    org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.Enum type=style.getType();
    boolean isDefaultStyle=(isDefault != null && isDefault.intValue() == org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff.INT_X_1);
    if (isDefaultStyle) {
      if (type != null) {
switch (type.intValue()) {
case org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.INT_CHARACTER:
          defaultCharacterStyle=style;
        break;
case org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.INT_NUMBERING:
      defaultNumberingStyle=style;
    break;
case org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.INT_PARAGRAPH:
  defaultParagraphStyle=style;
break;
case org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType.INT_TABLE:
defaultTableStyle=style;
break;
}
}
}
visitStyle(style,isDefaultStyle);
stylesByStyleId.put(style.getStyleId(),style);
}
}
