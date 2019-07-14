public void init(ArrayList<BaseFragment> stack){
  fragmentsStack=stack;
  containerViewBack=new LinearLayoutContainer(parentActivity);
  addView(containerViewBack);
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)containerViewBack.getLayoutParams();
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=LayoutHelper.MATCH_PARENT;
  layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
  containerViewBack.setLayoutParams(layoutParams);
  containerView=new LinearLayoutContainer(parentActivity);
  addView(containerView);
  layoutParams=(FrameLayout.LayoutParams)containerView.getLayoutParams();
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=LayoutHelper.MATCH_PARENT;
  layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
  containerView.setLayoutParams(layoutParams);
  for (  BaseFragment fragment : fragmentsStack) {
    fragment.setParentLayout(this);
  }
}
