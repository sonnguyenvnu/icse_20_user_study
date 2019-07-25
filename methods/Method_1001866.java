public void text(String text,double x,double y,String fontFamily,int fontSize,String fontWeight,String fontStyle,String textDecoration,double textLength,Map<String,String> attributes,String textBackColor){
  if (hidden == false) {
    final Element elt=(Element)document.createElement("text");
    elt.setAttribute("x",format(x));
    elt.setAttribute("y",format(y));
    elt.setAttribute("fill",fill);
    elt.setAttribute("font-size",format(fontSize));
    elt.setAttribute("lengthAdjust","spacingAndGlyphs");
    elt.setAttribute("textLength",format(textLength));
    if (fontWeight != null) {
      elt.setAttribute("font-weight",fontWeight);
    }
    if (fontStyle != null) {
      elt.setAttribute("font-style",fontStyle);
    }
    if (textDecoration != null) {
      elt.setAttribute("text-decoration",textDecoration);
    }
    if (fontFamily != null) {
      if ("monospaced".equalsIgnoreCase(fontFamily)) {
        fontFamily="monospace";
      }
      elt.setAttribute("font-family",fontFamily);
      if (fontFamily.equalsIgnoreCase("monospace") || fontFamily.equalsIgnoreCase("courier")) {
        text=text.replace(' ',(char)160);
      }
    }
    if (textBackColor != null) {
      final String backFilterId=getFilterBackColor(textBackColor);
      elt.setAttribute("filter","url(#" + backFilterId + ")");
    }
    for (    Map.Entry<String,String> ent : attributes.entrySet()) {
      elt.setAttribute(ent.getKey(),ent.getValue());
    }
    elt.setTextContent(text);
    getG().appendChild(elt);
  }
  ensureVisible(x,y);
  ensureVisible(x + textLength,y);
}
