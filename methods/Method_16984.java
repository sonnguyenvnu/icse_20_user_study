/** 
 * Returns the segment for the given index, creating it and recording in segment table (via CAS) if not already present.
 * @param k the index
 * @return the segment
 */
@SuppressWarnings("unchecked") private Segment<K,V> ensureSegment(int k){
  final Segment<K,V>[] ss=this.segments;
  long u=(k << SSHIFT) + SBASE;
  Segment<K,V> seg;
  if ((seg=(Segment<K,V>)UNSAFE.getObjectVolatile(ss,u)) == null) {
    Segment<K,V> proto=ss[0];
    int cap=proto.table.length;
    float lf=proto.loadFactor;
    int threshold=(int)(cap * lf);
    HashEntry<K,V>[] tab=new HashEntry[cap];
    if ((seg=(Segment<K,V>)UNSAFE.getObjectVolatile(ss,u)) == null) {
      Segment<K,V> s=new Segment<K,V>(lf,threshold,tab);
      while ((seg=(Segment<K,V>)UNSAFE.getObjectVolatile(ss,u)) == null) {
        if (UNSAFE.compareAndSwapObject(ss,u,null,seg=s)) {
          break;
        }
      }
    }
  }
  return seg;
}
