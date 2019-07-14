/** 
 * Used for an item inserted into the new list when we need to track moves that effect the inserted item in the old list.
 */
void pairWithSelf(){
  if (pair != null) {
    throw new IllegalStateException("Already paired.");
  }
  pair=new ModelState();
  pair.lastMoveOp=0;
  pair.id=id;
  pair.position=position;
  pair.hashCode=hashCode;
  pair.pair=this;
  pair.model=model;
}
