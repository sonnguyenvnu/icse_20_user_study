@SuppressLint("RestrictedApi") private void init(@Nullable AttributeSet attrs,int defStyleAttr,int defStyleRes){
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(getContext(),attrs,STYLEABLE,defStyleAttr,defStyleRes);
  mMaxWidth=a.getDimensionPixelSize(STYLEABLE_ANDROID_MAX_WIDTH,mMaxWidth);
  a.recycle();
}
