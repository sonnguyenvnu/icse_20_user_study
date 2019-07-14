private void initStatusView(){
  ViewGroup.LayoutParams layoutParams=mBinding.include.viewStatus.getLayoutParams();
  layoutParams.height=StatusBarUtil.getStatusBarHeight(this);
  mBinding.include.viewStatus.setLayoutParams(layoutParams);
}
