public void setParentActivity(Activity activity){
  currentAccount=UserConfig.selectedAccount;
  centerImage.setCurrentAccount(currentAccount);
  if (parentActivity == activity) {
    return;
  }
  parentActivity=activity;
  slideUpDrawable=parentActivity.getResources().getDrawable(R.drawable.preview_arrow);
  windowView=new FrameLayout(activity);
  windowView.setFocusable(true);
  windowView.setFocusableInTouchMode(true);
  if (Build.VERSION.SDK_INT >= 21) {
    windowView.setFitsSystemWindows(true);
    windowView.setOnApplyWindowInsetsListener((v,insets) -> {
      lastInsets=insets;
      return insets;
    }
);
  }
  containerView=new FrameLayoutDrawer(activity);
  containerView.setFocusable(false);
  windowView.addView(containerView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT));
  containerView.setOnTouchListener((v,event) -> {
    if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_POINTER_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
      close();
    }
    return true;
  }
);
  windowLayoutParams=new WindowManager.LayoutParams();
  windowLayoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.format=PixelFormat.TRANSLUCENT;
  windowLayoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.gravity=Gravity.TOP;
  windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
  if (Build.VERSION.SDK_INT >= 21) {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
  }
 else {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
  }
  centerImage.setAspectFit(true);
  centerImage.setInvalidateAll(true);
  centerImage.setParentView(containerView);
}
