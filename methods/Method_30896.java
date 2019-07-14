@Override public void setBounds(int left,int top,int right,int bottom){
  super.setBounds(left,top,right,bottom);
  int paddingHorizontal=(right - left) * mIntrinsicPadding / mIntrinsicSize;
  int paddingVertical=(bottom - top) * mIntrinsicPadding / mIntrinsicSize;
  mProgressDrawable.setBounds(left + paddingHorizontal,top + paddingVertical,right - paddingHorizontal,bottom - paddingVertical);
}
