@Override public boolean getAllowsChildren(){
  if (value == null) {
    return false;
  }
  if (getType() == TYPE_STRING) {
    return false;
  }
  if (getType() == TYPE_ARRAY) {
    ArrayReference array=(ArrayReference)value;
    return array.length() > 0;
  }
  if (getType() == TYPE_OBJECT) {
    ObjectReference obj=(ObjectReference)value;
    return !obj.referenceType().visibleFields().isEmpty();
  }
  return false;
}
