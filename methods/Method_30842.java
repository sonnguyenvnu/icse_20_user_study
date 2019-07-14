private void init(AttributeSet attrs,int defStyleAttr,int defStyleRes){
  TypedArray a=getContext().obtainStyledAttributes(attrs,COM_ANDROID_INTERNAL_R_STYLEABLE_RINGTONE_PREFERENCE,defStyleAttr,defStyleRes);
  mRingtoneType=a.getInt(COM_ANDROID_INTERNAL_R_STYLEABLE_RINGTONE_PREFERENCE_RINGTONE_TYPE,mRingtoneType);
  mShowDefault=a.getBoolean(COM_ANDROID_INTERNAL_R_STYLEABLE_RINGTONE_PREFERENCE_SHOW_DEFAULT,mShowDefault);
  mShowSilent=a.getBoolean(COM_ANDROID_INTERNAL_R_STYLEABLE_RINGTONE_PREFERENCE_SHOW_SILENT,mShowSilent);
  a.recycle();
}
