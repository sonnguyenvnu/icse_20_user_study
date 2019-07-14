static GifImageViewAttributes initImageView(ImageView view,AttributeSet attrs,int defStyleAttr,int defStyleRes){
  if (attrs != null && !view.isInEditMode()) {
    final GifImageViewAttributes viewAttributes=new GifImageViewAttributes(view,attrs,defStyleAttr,defStyleRes);
    final int loopCount=viewAttributes.mLoopCount;
    if (loopCount >= 0) {
      applyLoopCount(loopCount,view.getDrawable());
      applyLoopCount(loopCount,view.getBackground());
    }
    return viewAttributes;
  }
  return new GifImageViewAttributes();
}
