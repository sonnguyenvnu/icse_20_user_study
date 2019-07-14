@Override public void addAttribute(final CharSequence name,final CharSequence value){
  ensureLength();
  attrNames[attributesCount]=name;
  setAttrVal(attributesCount,name,value);
  attributesCount++;
  modified=true;
}
