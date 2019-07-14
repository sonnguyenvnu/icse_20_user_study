public boolean containsPoint(T other){
  Preconditions.checkNotNull(other);
  if (isPoints())   return start.equals(other);
 else {
    if (start != null) {
      int cmp=((Comparable)start).compareTo(other);
      if (cmp > 0 || (cmp == 0 && !startInclusive))       return false;
    }
    if (end != null) {
      int cmp=((Comparable)end).compareTo(other);
      return cmp >= 0 && (cmp != 0 || endInclusive);
    }
    return true;
  }
}
