@Override public CharSequence getAttributeValue(final int index){
  if (index >= attributesCount) {
    throw new IndexOutOfBoundsException();
  }
  return attrValues[index];
}
