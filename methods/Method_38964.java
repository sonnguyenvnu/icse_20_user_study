private void setAttrVal(final int index,final CharSequence name,final CharSequence value){
  if (idNdx == -1) {
    if (CharSequenceUtil.equalsToLowercase(name,ATTR_NAME_ID)) {
      idNdx=index;
    }
  }
  attrValues[index]=value;
}
