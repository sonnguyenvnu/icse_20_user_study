/** 
 * Apply the movement operations to the given item to update its position. Only applies the operations that have not been applied yet, and stores how many operations have been applied so we know which ones to apply next time.
 */
private void updateItemPosition(ModelState item,List<UpdateOp> moveOps){
  int size=moveOps.size();
  for (int i=item.lastMoveOp; i < size; i++) {
    UpdateOp moveOp=moveOps.get(i);
    int fromPosition=moveOp.positionStart;
    int toPosition=moveOp.itemCount;
    if (item.position > fromPosition && item.position <= toPosition) {
      item.position--;
    }
 else     if (item.position < fromPosition && item.position >= toPosition) {
      item.position++;
    }
  }
  item.lastMoveOp=size;
}
