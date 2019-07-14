@SuppressLint("RestrictedApi") public void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
  mHasFrameworkForeground=(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.M) || mDelegate.getOwner() instanceof FrameLayout;
  if (mHasFrameworkForeground) {
    return;
  }
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(context,attrs,STYLEABLE,defStyleAttr,defStyleRes);
  mForegroundGravity=a.getInt(STYLEABLE_ANDROID_FOREGROUND_GRAVITY,mForegroundGravity);
  Drawable foreground=a.getDrawable(STYLEABLE_ANDROID_FOREGROUND);
  if (foreground != null) {
    setForeground(foreground);
  }
  mForegroundInPadding=a.getBoolean(STYLEABLE_ANDROID_FOREGROUND_INSIDE_PADDING,true);
  a.recycle();
}
