@Override public void start(@NotNull CharSequence buffer,int startOffset,int endOffset,int initialState){
  this.buffer=buffer;
  this.lexemeStart=this.lexemeEnd=this.currentOffset=this.startOffset=startOffset;
  this.endOffset=endOffset;
  advance();
}
