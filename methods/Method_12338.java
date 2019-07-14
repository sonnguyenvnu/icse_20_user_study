private void init(AttributeSet attrs,int defStyle,int defStyleRes){
  if (attrs != null) {
    Drawable left=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableLeft",0));
    Drawable top=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableTop",0));
    Drawable right=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableRight",0));
    Drawable bottom=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableBottom",0));
    Drawable start=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableStart",0));
    Drawable end=getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"drawableEnd",0));
    if (getLayoutDirection() == LAYOUT_DIRECTION_LTR) {
      if (start == null) {
        start=left;
      }
      if (end == null) {
        end=right;
      }
    }
 else {
      if (start == null) {
        start=right;
      }
      if (end == null) {
        end=left;
      }
    }
    setCompoundDrawablesRelativeWithIntrinsicBounds(start,top,end,bottom);
    setBackground(getGifOrDefaultDrawable(attrs.getAttributeResourceValue(GifViewUtils.ANDROID_NS,"background",0)));
    viewAttributes=new GifViewUtils.GifViewAttributes(this,attrs,defStyle,defStyleRes);
    applyGifViewAttributes();
  }
  viewAttributes=new GifViewUtils.GifViewAttributes();
}
