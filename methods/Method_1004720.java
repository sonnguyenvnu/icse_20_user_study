@SuppressWarnings("RestrictedApi") private void init(AttributeSet attrs,int defStyleAttr){
  Context context=getContext();
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.MaterialRatingBar,defStyleAttr,0);
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_progressTint)) {
    mProgressTintInfo.mProgressTintList=a.getColorStateList(R.styleable.MaterialRatingBar_mrb_progressTint);
    mProgressTintInfo.mHasProgressTintList=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_progressTintMode)) {
    mProgressTintInfo.mProgressTintMode=DrawableCompat.parseTintMode(a.getInt(R.styleable.MaterialRatingBar_mrb_progressTintMode,-1),null);
    mProgressTintInfo.mHasProgressTintMode=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_secondaryProgressTint)) {
    mProgressTintInfo.mSecondaryProgressTintList=a.getColorStateList(R.styleable.MaterialRatingBar_mrb_secondaryProgressTint);
    mProgressTintInfo.mHasSecondaryProgressTintList=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_secondaryProgressTintMode)) {
    mProgressTintInfo.mSecondaryProgressTintMode=DrawableCompat.parseTintMode(a.getInt(R.styleable.MaterialRatingBar_mrb_secondaryProgressTintMode,-1),null);
    mProgressTintInfo.mHasSecondaryProgressTintMode=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_progressBackgroundTint)) {
    mProgressTintInfo.mProgressBackgroundTintList=a.getColorStateList(R.styleable.MaterialRatingBar_mrb_progressBackgroundTint);
    mProgressTintInfo.mHasProgressBackgroundTintList=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_progressBackgroundTintMode)) {
    mProgressTintInfo.mProgressBackgroundTintMode=DrawableCompat.parseTintMode(a.getInt(R.styleable.MaterialRatingBar_mrb_progressBackgroundTintMode,-1),null);
    mProgressTintInfo.mHasProgressBackgroundTintMode=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_indeterminateTint)) {
    mProgressTintInfo.mIndeterminateTintList=a.getColorStateList(R.styleable.MaterialRatingBar_mrb_indeterminateTint);
    mProgressTintInfo.mHasIndeterminateTintList=true;
  }
  if (a.hasValue(R.styleable.MaterialRatingBar_mrb_indeterminateTintMode)) {
    mProgressTintInfo.mIndeterminateTintMode=DrawableCompat.parseTintMode(a.getInt(R.styleable.MaterialRatingBar_mrb_indeterminateTintMode,-1),null);
    mProgressTintInfo.mHasIndeterminateTintMode=true;
  }
  boolean fillBackgroundStars=a.getBoolean(R.styleable.MaterialRatingBar_mrb_fillBackgroundStars,isIndicator());
  a.recycle();
  mDrawable=new MaterialRatingDrawable(getContext(),fillBackgroundStars);
  mDrawable.setStarCount(getNumStars());
  setProgressDrawable(mDrawable);
}
