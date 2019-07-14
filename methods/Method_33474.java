@Override public void setContentView(@LayoutRes int layoutResID){
  View ll=getLayoutInflater().inflate(R.layout.activity_header_base,null);
  bindingContentView=DataBindingUtil.inflate(getLayoutInflater(),layoutResID,null,false);
  bindingHeaderView=DataBindingUtil.inflate(getLayoutInflater(),setHeaderLayout(),null,false);
  bindingTitleView=DataBindingUtil.inflate(getLayoutInflater(),R.layout.base_header_title_bar,null,false);
  RelativeLayout.LayoutParams titleParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  bindingTitleView.getRoot().setLayoutParams(titleParams);
  RelativeLayout mTitleContainer=(RelativeLayout)ll.findViewById(R.id.title_container);
  mTitleContainer.addView(bindingTitleView.getRoot());
  RelativeLayout.LayoutParams headerParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
  bindingHeaderView.getRoot().setLayoutParams(headerParams);
  RelativeLayout mHeaderContainer=(RelativeLayout)ll.findViewById(R.id.header_container);
  mHeaderContainer.addView(bindingHeaderView.getRoot());
  RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
  bindingContentView.getRoot().setLayoutParams(params);
  RelativeLayout mContainer=ll.findViewById(R.id.container);
  mContainer.addView(bindingContentView.getRoot());
  getWindow().setContentView(ll);
  loadingView=((ViewStub)getView(R.id.vs_loading)).inflate();
  setToolBar();
  ImageView img=loadingView.findViewById(R.id.img_progress);
  mAnimationDrawable=(AnimationDrawable)img.getDrawable();
  if (!mAnimationDrawable.isRunning()) {
    mAnimationDrawable.start();
  }
  bindingContentView.getRoot().setVisibility(View.GONE);
}
