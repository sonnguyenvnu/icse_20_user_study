public boolean subsumes(SliceQuery oth){
  Preconditions.checkNotNull(oth);
  if (this == oth)   return true;
  if (oth.getLimit() > getLimit())   return false;
 else   if (!hasLimit())   return sliceStart.compareTo(oth.sliceStart) <= 0 && sliceEnd.compareTo(oth.sliceEnd) >= 0;
 else   return sliceStart.compareTo(oth.sliceStart) == 0 && sliceEnd.compareTo(oth.sliceEnd) >= 0;
}
