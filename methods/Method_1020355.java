/** 
 * Parses an  {@code Exceptions} attribute.
 */
private Attribute exceptions(DirectClassFile cf,int offset,int length,ParseObserver observer){
  if (length < 2) {
    return throwSeverelyTruncated();
  }
  ByteArray bytes=cf.getBytes();
  int count=bytes.getUnsignedShort(offset);
  if (observer != null) {
    observer.parsed(bytes,offset,2,"number_of_exceptions: " + Hex.u2(count));
  }
  offset+=2;
  length-=2;
  if (length != (count * 2)) {
    throwBadLength((count * 2) + 2);
  }
  TypeList list=cf.makeTypeList(offset,count);
  return new AttExceptions(list);
}
