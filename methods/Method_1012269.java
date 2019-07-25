private void init(Context context,AttributeSet attrs,int defStyleAttr){
  mLayoutHelper=new XUILayoutHelper(context,attrs,defStyleAttr,this);
  setChangeAlphaWhenPress(false);
  setChangeAlphaWhenDisable(false);
}
