/** 
 * get the size of the ARC. this returns the sum of main and ghost cache
 * @return the complete number of entries in the ARC cache
 */
@Override public final synchronized int size(){
  return this.levelA.size() + this.levelB.size();
}
