/** 
 * Returns a new empty Union object with the current configuration of this Builder.
 * @return a Union object
 */
public DoublesUnion build(){
  return DoublesUnionImpl.heapInstance(bMaxK);
}
