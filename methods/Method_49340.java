@Override public boolean isEmpty(){
  if (start == null || end == null)   return false;
  if (isPoints())   return false;
  int cmp=((Comparable)start).compareTo(end);
  return cmp > 0 || (cmp == 0 && (!startInclusive || !endInclusive));
}
