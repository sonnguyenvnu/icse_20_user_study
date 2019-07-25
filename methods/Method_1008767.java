public void initialize(Resources res,String[] texts,String[] innerTexts,boolean is24HourMode,boolean disappearsOut){
  if (mIsInitialized) {
    Log.e(TAG,"This RadialTextsView may only be initialized once.");
    return;
  }
  int numbersTextColor=res.getColor(R.color.mdtp_numbers_text_color);
  mPaint.setColor(numbersTextColor);
  String typefaceFamily=res.getString(R.string.mdtp_radial_numbers_typeface);
  mTypefaceLight=Typeface.create(typefaceFamily,Typeface.NORMAL);
  String typefaceFamilyRegular=res.getString(R.string.mdtp_sans_serif);
  mTypefaceRegular=Typeface.create(typefaceFamilyRegular,Typeface.NORMAL);
  mPaint.setAntiAlias(true);
  mPaint.setTextAlign(Align.CENTER);
  int selectedTextColor=res.getColor(R.color.mdtp_white);
  mSelectedPaint.setColor(selectedTextColor);
  mSelectedPaint.setAntiAlias(true);
  mSelectedPaint.setTextAlign(Align.CENTER);
  mTexts=texts;
  mInnerTexts=innerTexts;
  mIs24HourMode=is24HourMode;
  mHasInnerCircle=(innerTexts != null);
  if (is24HourMode) {
    mCircleRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_circle_radius_multiplier_24HourMode));
  }
 else {
    mCircleRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_circle_radius_multiplier));
    mAmPmCircleRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_ampm_circle_radius_multiplier));
  }
  mTextGridHeights=new float[7];
  mTextGridWidths=new float[7];
  if (mHasInnerCircle) {
    mNumbersRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_numbers_radius_multiplier_outer));
    mTextSizeMultiplier=Float.parseFloat(res.getString(R.string.mdtp_text_size_multiplier_outer));
    mInnerNumbersRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_numbers_radius_multiplier_inner));
    mInnerTextSizeMultiplier=Float.parseFloat(res.getString(R.string.mdtp_text_size_multiplier_inner));
    mInnerTextGridHeights=new float[7];
    mInnerTextGridWidths=new float[7];
  }
 else {
    mNumbersRadiusMultiplier=Float.parseFloat(res.getString(R.string.mdtp_numbers_radius_multiplier_normal));
    mTextSizeMultiplier=Float.parseFloat(res.getString(R.string.mdtp_text_size_multiplier_normal));
  }
  mAnimationRadiusMultiplier=1;
  mTransitionMidRadiusMultiplier=1f + (0.05f * (disappearsOut ? -1 : 1));
  mTransitionEndRadiusMultiplier=1f + (0.3f * (disappearsOut ? 1 : -1));
  mInvalidateUpdateListener=new InvalidateUpdateListener();
  mTextGridValuesDirty=true;
  mIsInitialized=true;
}
