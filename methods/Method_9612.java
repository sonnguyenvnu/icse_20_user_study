public void setParentActivity(Activity activity){
  currentAccount=UserConfig.selectedAccount;
  centerImage.setCurrentAccount(currentAccount);
  if (parentActivity == activity) {
    return;
  }
  parentActivity=activity;
  scroller=new Scroller(activity);
  windowView=new FrameLayout(activity){
    @Override protected void onMeasure(    int widthMeasureSpec,    int heightMeasureSpec){
      int widthSize=MeasureSpec.getSize(widthMeasureSpec);
      int heightSize=MeasureSpec.getSize(heightMeasureSpec);
      if (Build.VERSION.SDK_INT >= 21 && lastInsets != null) {
        WindowInsets insets=(WindowInsets)lastInsets;
        if (AndroidUtilities.incorrectDisplaySizeFix) {
          if (heightSize > AndroidUtilities.displaySize.y) {
            heightSize=AndroidUtilities.displaySize.y;
          }
          heightSize+=AndroidUtilities.statusBarHeight;
        }
        heightSize-=insets.getSystemWindowInsetBottom();
        widthSize-=insets.getSystemWindowInsetRight();
      }
 else {
        if (heightSize > AndroidUtilities.displaySize.y) {
          heightSize=AndroidUtilities.displaySize.y;
        }
      }
      setMeasuredDimension(widthSize,heightSize);
      if (Build.VERSION.SDK_INT >= 21 && lastInsets != null) {
        widthSize-=((WindowInsets)lastInsets).getSystemWindowInsetLeft();
      }
      containerView.measure(MeasureSpec.makeMeasureSpec(widthSize,MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.EXACTLY));
    }
    @SuppressWarnings("DrawAllocation") @Override protected void onLayout(    boolean changed,    int left,    int top,    int right,    int bottom){
      int x=0;
      if (Build.VERSION.SDK_INT >= 21 && lastInsets != null) {
        x+=((WindowInsets)lastInsets).getSystemWindowInsetLeft();
      }
      containerView.layout(x,0,x + containerView.getMeasuredWidth(),containerView.getMeasuredHeight());
      if (changed) {
        if (imageMoveAnimation == null) {
          scale=1;
          translationX=0;
          translationY=0;
        }
        updateMinMax(scale);
      }
    }
  }
;
  windowView.setBackgroundDrawable(photoBackgroundDrawable);
  windowView.setFocusable(true);
  windowView.setFocusableInTouchMode(true);
  containerView=new FrameLayoutDrawer(activity){
    @Override protected void onLayout(    boolean changed,    int left,    int top,    int right,    int bottom){
      super.onLayout(changed,left,top,right,bottom);
      if (secretDeleteTimer != null) {
        int y=(ActionBar.getCurrentActionBarHeight() - secretDeleteTimer.getMeasuredHeight()) / 2 + (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
        secretDeleteTimer.layout(secretDeleteTimer.getLeft(),y,secretDeleteTimer.getRight(),y + secretDeleteTimer.getMeasuredHeight());
      }
    }
  }
;
  containerView.setFocusable(false);
  windowView.addView(containerView);
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)containerView.getLayoutParams();
  layoutParams.width=LayoutHelper.MATCH_PARENT;
  layoutParams.height=LayoutHelper.MATCH_PARENT;
  layoutParams.gravity=Gravity.TOP | Gravity.LEFT;
  containerView.setLayoutParams(layoutParams);
  if (Build.VERSION.SDK_INT >= 21) {
    containerView.setFitsSystemWindows(true);
    containerView.setOnApplyWindowInsetsListener((v,insets) -> {
      WindowInsets oldInsets=(WindowInsets)lastInsets;
      lastInsets=insets;
      if (oldInsets == null || !oldInsets.toString().equals(insets.toString())) {
        windowView.requestLayout();
      }
      return insets.consumeSystemWindowInsets();
    }
);
    containerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
  }
  gestureDetector=new GestureDetector(containerView.getContext(),this);
  gestureDetector.setOnDoubleTapListener(this);
  actionBar=new ActionBar(activity);
  actionBar.setTitleColor(0xffffffff);
  actionBar.setSubtitleColor(0xffffffff);
  actionBar.setBackgroundColor(Theme.ACTION_BAR_PHOTO_VIEWER_COLOR);
  actionBar.setOccupyStatusBar(Build.VERSION.SDK_INT >= 21);
  actionBar.setItemsBackgroundColor(Theme.ACTION_BAR_WHITE_SELECTOR_COLOR,false);
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setTitleRightMargin(AndroidUtilities.dp(70));
  containerView.addView(actionBar,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        closePhoto(true,false);
      }
    }
  }
);
  secretDeleteTimer=new SecretDeleteTimer(activity);
  containerView.addView(secretDeleteTimer,LayoutHelper.createFrame(119,48,Gravity.TOP | Gravity.RIGHT,0,0,0,0));
  windowLayoutParams=new WindowManager.LayoutParams();
  windowLayoutParams.height=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.format=PixelFormat.TRANSLUCENT;
  windowLayoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
  windowLayoutParams.gravity=Gravity.TOP;
  windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
  if (Build.VERSION.SDK_INT >= 21) {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
  }
 else {
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
  }
  windowLayoutParams.flags|=WindowManager.LayoutParams.FLAG_SECURE;
  centerImage.setParentView(containerView);
  centerImage.setForceCrossfade(true);
}
