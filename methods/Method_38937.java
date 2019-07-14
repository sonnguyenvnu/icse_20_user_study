private void _addAttribute(final CharSequence attrName,final CharSequence attrValue){
  if (tag.getType() == TagType.END) {
    _error("Ignored end tag attribute");
  }
 else {
    if (tag.hasAttribute(attrName)) {
      _error("Ignored duplicated attribute: " + attrName);
    }
 else {
      tag.addAttribute(attrName,attrValue);
    }
  }
  attrStartNdx=-1;
  attrEndNdx=-1;
  textLen=0;
}
