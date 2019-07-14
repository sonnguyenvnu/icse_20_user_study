/** 
 * Update the tracked position. Will notify listeners if line number has changed.
 */
protected synchronized void updatePosition(){
  if (doc != null && pos != null) {
    int offset=pos.getOffset();
    int oldLineIdx=lineIdx;
    lineIdx=doc.getDefaultRootElement().getElementIndex(offset);
    if (lineIdx != oldLineIdx) {
      for (      LineHighlight l : listeners) {
        if (l != null) {
          l.lineChanged(this,oldLineIdx,lineIdx);
        }
 else {
          listeners.remove(l);
        }
      }
    }
  }
}
