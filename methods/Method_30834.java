@SuppressLint("RestrictedApi") private void init(AttributeSet attrs,int defStyleAttr){
  Context context=getContext();
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(context,attrs,STYLEABLE,defStyleAttr,0);
  mKey=a.getString(STYLEABLE_ANDROID_KEY);
  mHasDefaultValue=a.hasValue(STYLEABLE_ANDROID_DEFAULT_VALUE);
  mDefaultValue=a.getBoolean(STYLEABLE_ANDROID_DEFAULT_VALUE,false);
  mPersistent=a.getBoolean(STYLEABLE_ANDROID_PERSISTENT,true);
  a.recycle();
  mSharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
  dispatchSetInitialValue();
}
