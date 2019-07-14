public void clear(long style){
  Arrays.fill(mText,' ');
  Arrays.fill(mStyle,style);
  mSpaceUsed=(short)mColumns;
  mHasNonOneWidthOrSurrogateChars=false;
}
