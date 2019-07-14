private void registerForLongClick(LongClickableSpan longClickableSpan,View view){
  mLongClickRunnable=new LongClickRunnable(longClickableSpan,view);
  mLongClickHandler.postDelayed(mLongClickRunnable,ViewConfiguration.getLongPressTimeout());
}
