@Override public boolean isEquivalentTo(Component other){
  if (this == other) {
    return true;
  }
  if (other == null || getClass() != other.getClass()) {
    return false;
  }
  Column column=(Column)other;
  if (this.getId() == column.getId()) {
    return true;
  }
  if (children != null) {
    if (column.children == null || children.size() != column.children.size()) {
      return false;
    }
    for (int i=0, size=children.size(); i < size; i++) {
      if (!children.get(i).isEquivalentTo(column.children.get(i))) {
        return false;
      }
    }
  }
 else   if (column.children != null) {
    return false;
  }
  if (alignItems != null ? !alignItems.equals(column.alignItems) : column.alignItems != null) {
    return false;
  }
  if (alignContent != null ? !alignContent.equals(column.alignContent) : column.alignContent != null) {
    return false;
  }
  if (justifyContent != null ? !justifyContent.equals(column.justifyContent) : column.justifyContent != null) {
    return false;
  }
  if (reverse != column.reverse) {
    return false;
  }
  return true;
}
