/** 
 * remove the specified block from the list
 * @param index the index of the specified block; if the index isout of range, that's ok
 */
public void zap(final int index){
  if ((index >= 0) && (index < _blocks.length)) {
    _blocks[index]=null;
  }
}
