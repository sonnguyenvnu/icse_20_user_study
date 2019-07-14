synchronized int getFrameDuration(@IntRange(from=0) final int index){
  throwIfFrameIndexOutOfBounds(index);
  return getFrameDuration(gifInfoPtr,index);
}
