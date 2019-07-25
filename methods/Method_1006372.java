/** 
 * Prepares a bitmap for iteration
 * @param r bitmap to be iterated over
 */
public void wrap(ImmutableRoaringBitmap r){
  this.hs=0;
  this.pos=0;
  this.roaringBitmap=r;
  this.nextContainer();
}
