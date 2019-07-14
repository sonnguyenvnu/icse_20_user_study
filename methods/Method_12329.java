private void throwIfFrameIndexOutOfBounds(@IntRange(from=0) final int index){
  final int numberOfFrames=getNumberOfFrames(gifInfoPtr);
  if (index < 0 || index >= numberOfFrames) {
    throw new IndexOutOfBoundsException("Frame index is not in range <0;" + numberOfFrames + '>');
  }
}
