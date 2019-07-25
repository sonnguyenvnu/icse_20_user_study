/** 
 * Convert the list in a way that optimizes storage in the case that all elements are either integers or  {@link String}s/ {@link BytesRef}s. This is useful to help garbage collections for use-cases that involve sending very large terms queries to Elasticsearch. If the list does not only contain integers or  {@link String}s, then a list is returned where all {@link String}s have been replaced with  {@link BytesRef}s.
 */
static List<?> convert(List<?> list){
  if (list.isEmpty()) {
    return Collections.emptyList();
  }
  final boolean allNumbers=list.stream().allMatch(o -> o != null && INTEGER_TYPES.contains(o.getClass()));
  if (allNumbers) {
    final long[] elements=list.stream().mapToLong(o -> ((Number)o).longValue()).toArray();
    return new AbstractList<Object>(){
      @Override public Object get(      int index){
        return elements[index];
      }
      @Override public int size(){
        return elements.length;
      }
    }
;
  }
  final boolean allStrings=list.stream().allMatch(o -> o != null && STRING_TYPES.contains(o.getClass()));
  if (allStrings) {
    final BytesRefBuilder builder=new BytesRefBuilder();
    try (BytesStreamOutput bytesOut=new BytesStreamOutput()){
      final int[] endOffsets=new int[list.size()];
      int i=0;
      for (      Object o : list) {
        BytesRef b;
        if (o instanceof BytesRef) {
          b=(BytesRef)o;
        }
 else {
          builder.copyChars(o.toString());
          b=builder.get();
        }
        bytesOut.writeBytes(b.bytes,b.offset,b.length);
        if (i == 0) {
          endOffsets[0]=b.length;
        }
 else {
          endOffsets[i]=Math.addExact(endOffsets[i - 1],b.length);
        }
        ++i;
      }
      final BytesReference bytes=bytesOut.bytes();
      return new AbstractList<Object>(){
        @Override public Object get(        int i){
          final int startOffset=i == 0 ? 0 : endOffsets[i - 1];
          final int endOffset=endOffsets[i];
          return bytes.slice(startOffset,endOffset - startOffset).toBytesRef();
        }
        @Override public int size(){
          return endOffsets.length;
        }
      }
;
    }
   }
  return list.stream().map(o -> o instanceof String ? new BytesRef(o.toString()) : o).collect(Collectors.toList());
}
