@Override public boolean isEquivalentTo(Component other){
  if (this == other) {
    return true;
  }
  if (other == null || getClass() != other.getClass()) {
    return false;
  }
  Row row=(Row)other;
  if (this.getId() == row.getId()) {
    return true;
  }
  if (children != null) {
    if (row.children == null || children.size() != row.children.size()) {
      return false;
    }
    for (int i=0, size=children.size(); i < size; i++) {
      if (!children.get(i).isEquivalentTo(row.children.get(i))) {
        return false;
      }
    }
  }
 else   if (row.children != null) {
    return false;
  }
  if (alignItems != null ? !alignItems.equals(row.alignItems) : row.alignItems != null) {
    return false;
  }
  if (alignContent != null ? !alignContent.equals(row.alignContent) : row.alignContent != null) {
    return false;
  }
  if (justifyContent != null ? !justifyContent.equals(row.justifyContent) : row.justifyContent != null) {
    return false;
  }
  if (reverse != row.reverse) {
    return false;
  }
  return true;
}
