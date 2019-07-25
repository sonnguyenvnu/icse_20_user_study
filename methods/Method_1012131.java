@Override public final void start(@NotNull CharSequence buffer,int startOffset,int endOffset,int initialState){
  myBuffer=buffer;
  myBufferArray=CharArrayUtil.fromSequenceWithoutCopying(buffer);
  myBufferIndex=startOffset;
  myBufferEndOffset=endOffset;
  myTokenType=null;
  myTokenEndOffset=startOffset;
  myFlexlexer.reset(myBuffer,startOffset,endOffset,0);
}
