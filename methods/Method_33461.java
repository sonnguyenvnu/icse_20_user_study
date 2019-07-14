@Override public void setContentView(@LayoutRes int layoutResID){
  mBaseBinding=DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.activity_base,null,false);
  bindingView=DataBindingUtil.inflate(getLayoutInflater(),layoutResID,null,false);
  RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
  bindingView.getRoot().setLayoutParams(params);
  RelativeLayout mContainer=(RelativeLayout)mBaseBinding.getRoot().findViewById(R.id.container);
  mContainer.addView(bindingView.getRoot());
  getWindow().setContentView(mBaseBinding.getRoot());
  StatusBarUtil.setColor(this,CommonUtils.getColor(R.color.colorTheme),0);
  loadingView=((ViewStub)findViewById(R.id.vs_loading)).inflate();
  ImageView img=loadingView.findViewById(R.id.img_progress);
  mAnimationDrawable=(AnimationDrawable)img.getDrawable();
  if (!mAnimationDrawable.isRunning()) {
    mAnimationDrawable.start();
  }
  setToolBar();
  bindingView.getRoot().setVisibility(View.GONE);
  initViewModel();
}
