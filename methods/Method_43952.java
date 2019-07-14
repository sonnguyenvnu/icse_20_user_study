@Override public Long getOffset(){
  if (offset != null || pageLength == null || pageNumber == null)   return offset;
 else   return (long)pageLength * pageNumber;
}
