/** 
 * Returns if entries may be assigned different weights. 
 */
protected boolean isWeighted(){
  return (weigher != Weigher.singletonWeigher());
}
