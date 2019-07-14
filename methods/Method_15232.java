@Override public void initEvent(){
  super.initEvent();
  containerView.setOnTabClickListener(onTabClickListener);
  containerView.setOnItemSelectedListener(onItemSelectedListener);
}
