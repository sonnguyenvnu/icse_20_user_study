public void mount(CharSequence text,Layout layout,float layoutTranslationY,boolean clipToBounds,ColorStateList colorStateList,int userColor,int highlightColor,ClickableSpan[] clickableSpans,ImageSpan[] imageSpans,ClickableSpanListener spanListener,TextOffsetOnTouchListener textOffsetOnTouchListener,int highlightStartOffset,int highlightEndOffset,float clickableSpanExpandedOffset,String contextLogTag){
  mLayout=layout;
  mLayoutTranslationY=layoutTranslationY;
  mClipToBounds=clipToBounds;
  mText=text;
  mClickableSpans=clickableSpans;
  if (mLongClickHandler == null && containsLongClickableSpan(clickableSpans)) {
    mLongClickHandler=new Handler();
  }
  mSpanListener=spanListener;
  mTextOffsetOnTouchListener=textOffsetOnTouchListener;
  mShouldHandleTouch=(clickableSpans != null && clickableSpans.length > 0);
  mHighlightColor=highlightColor;
  mClickableSpanExpandedOffset=clickableSpanExpandedOffset;
  if (userColor != 0) {
    mColorStateList=null;
    mUserColor=userColor;
  }
 else {
    mColorStateList=colorStateList != null ? colorStateList : TextSpec.textColorStateList;
    mUserColor=mColorStateList.getDefaultColor();
    if (mLayout != null) {
      mLayout.getPaint().setColor(mColorStateList.getColorForState(getState(),mUserColor));
    }
  }
  if (highlightOffsetsValid(text,highlightStartOffset,highlightEndOffset)) {
    setSelection(highlightStartOffset,highlightEndOffset);
  }
 else {
    clearSelection();
  }
  if (imageSpans != null) {
    for (int i=0, size=imageSpans.length; i < size; i++) {
      Drawable drawable=imageSpans[i].getDrawable();
      drawable.setCallback(this);
      drawable.setVisible(true,false);
    }
  }
  mImageSpans=imageSpans;
  mContextLogTag=contextLogTag;
  invalidateSelf();
}
