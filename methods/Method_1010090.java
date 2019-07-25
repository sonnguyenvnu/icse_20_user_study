public boolean contains(PositionInfo position){
  if (!(contains(position.getFileName(),position.getStartLine()))) {
    return false;
  }
  if (!(contains(position.getFileName(),position.getEndLine()))) {
    return false;
  }
  if (myStartLine == position.getStartLine()) {
    if (myStartPosition > position.getStartPosition()) {
      return false;
    }
  }
  if (myEndLine == position.getEndLine()) {
    return myEndPosition <= position.getEndPosition();
  }
  return true;
}
