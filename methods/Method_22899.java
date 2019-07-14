void endTextEditHistory(){
  if (endUndoEvent != null) {
    endUndoEvent.cancel();
    endUndoEvent=null;
  }
  stopCompoundEdit();
}
