/** 
 * Appends chunk to previous one and maintains the double-linked list of the previous chunk. Current surrounding connections of this chunk will be cut-off.
 */
public void insertChunkAfter(final SqlChunk previous){
  SqlChunk next=previous.nextChunk;
  previous.nextChunk=this;
  this.previousChunk=previous;
  if (next != null) {
    next.previousChunk=this;
    this.nextChunk=next;
  }
}
