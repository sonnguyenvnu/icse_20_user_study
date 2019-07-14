@SuppressLint("RestrictedApi") private void init(AttributeSet attrs,int defStyleAttr,int defStyleRes){
  setClickable(true);
  setFocusable(true);
  onInflateChildren();
  ButterKnife.bind(this);
  TintTypedArray a=TintTypedArray.obtainStyledAttributes(getContext(),attrs,R.styleable.ImageLayout,defStyleAttr,defStyleRes);
  int fillOrientation=a.getInt(R.styleable.ImageLayout_fillOrientation,FILL_ORIENTATION_HORIZONTAL);
  a.recycle();
  LayoutParams layoutParams=(LayoutParams)mImageView.getLayoutParams();
  layoutParams.width=fillOrientation == FILL_ORIENTATION_HORIZONTAL ? LayoutParams.MATCH_PARENT : LayoutParams.WRAP_CONTENT;
  layoutParams.height=fillOrientation == FILL_ORIENTATION_HORIZONTAL ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT;
  mImageView.setLayoutParams(layoutParams);
}
