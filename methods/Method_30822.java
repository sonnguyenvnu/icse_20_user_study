@SuppressLint("RestrictedApi") public void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(context,attrs,STYLEABLE,defStyleAttr,defStyleRes);
  mMaxWidth=a.getDimensionPixelSize(STYLEABLE_ANDROID_MAX_WIDTH,-1);
  mMaxHeight=a.getDimensionPixelSize(STYLEABLE_ANDROID_MAX_HEIGHT,-1);
  a.recycle();
}
