@Override public int getAttributeIndex(final CharSequence name){
  for (int i=0; i < attributesCount; i++) {
    final CharSequence current=attrNames[i];
    if (caseSensitive ? current.equals(name) : CharSequenceUtil.equalsIgnoreCase(current,name)) {
      return i;
    }
  }
  return -1;
}
