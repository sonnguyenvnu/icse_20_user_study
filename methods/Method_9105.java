public void show(Activity activity,final String themeName){
  if (Instance != null) {
    Instance.destroy();
  }
  hidden=false;
  currentThemeName=themeName;
  windowView=new FrameLayout(activity){
    @Override public boolean onInterceptTouchEvent(    MotionEvent event){
      return true;
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      float x=event.getRawX();
      float y=event.getRawY();
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        startX=x;
        startY=y;
      }
 else       if (event.getAction() == MotionEvent.ACTION_MOVE && !dragging) {
        if (Math.abs(startX - x) >= AndroidUtilities.getPixelsInCM(0.3f,true) || Math.abs(startY - y) >= AndroidUtilities.getPixelsInCM(0.3f,false)) {
          dragging=true;
          startX=x;
          startY=y;
        }
      }
 else       if (event.getAction() == MotionEvent.ACTION_UP) {
        if (!dragging) {
          if (editorAlert == null) {
            LaunchActivity launchActivity=(LaunchActivity)parentActivity;
            ActionBarLayout actionBarLayout=null;
            if (AndroidUtilities.isTablet()) {
              actionBarLayout=launchActivity.getLayersActionBarLayout();
              if (actionBarLayout != null && actionBarLayout.fragmentsStack.isEmpty()) {
                actionBarLayout=null;
              }
              if (actionBarLayout == null) {
                actionBarLayout=launchActivity.getRightActionBarLayout();
                if (actionBarLayout != null && actionBarLayout.fragmentsStack.isEmpty()) {
                  actionBarLayout=null;
                }
              }
            }
            if (actionBarLayout == null) {
              actionBarLayout=launchActivity.getActionBarLayout();
            }
            if (actionBarLayout != null) {
              BaseFragment fragment;
              if (!actionBarLayout.fragmentsStack.isEmpty()) {
                fragment=actionBarLayout.fragmentsStack.get(actionBarLayout.fragmentsStack.size() - 1);
              }
 else {
                fragment=null;
              }
              if (fragment != null) {
                ThemeDescription[] items=fragment.getThemeDescriptions();
                if (items != null) {
                  editorAlert=new EditorAlert(parentActivity,items);
                  editorAlert.setOnDismissListener(dialog -> {
                  }
);
                  editorAlert.setOnDismissListener(dialog -> {
                    editorAlert=null;
                    show();
                  }
);
                  editorAlert.show();
                  hide();
                }
              }
            }
          }
        }
      }
      if (dragging) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
          float dx=(x - startX);
          float dy=(y - startY);
          windowLayoutParams.x+=dx;
          windowLayoutParams.y+=dy;
          int maxDiff=editorWidth / 2;
          if (windowLayoutParams.x < -maxDiff) {
            windowLayoutParams.x=-maxDiff;
          }
 else           if (windowLayoutParams.x > AndroidUtilities.displaySize.x - windowLayoutParams.width + maxDiff) {
            windowLayoutParams.x=AndroidUtilities.displaySize.x - windowLayoutParams.width + maxDiff;
          }
          float alpha=1.0f;
          if (windowLayoutParams.x < 0) {
            alpha=1.0f + windowLayoutParams.x / (float)maxDiff * 0.5f;
          }
 else           if (windowLayoutParams.x > AndroidUtilities.displaySize.x - windowLayoutParams.width) {
            alpha=1.0f - (windowLayoutParams.x - AndroidUtilities.displaySize.x + windowLayoutParams.width) / (float)maxDiff * 0.5f;
          }
          if (windowView.getAlpha() != alpha) {
            windowView.setAlpha(alpha);
          }
          maxDiff=0;
          if (windowLayoutParams.y < -maxDiff) {
            windowLayoutParams.y=-maxDiff;
          }
 else           if (windowLayoutParams.y > AndroidUtilities.displaySize.y - windowLayoutParams.height + maxDiff) {
            windowLayoutParams.y=AndroidUtilities.displaySize.y - windowLayoutParams.height + maxDiff;
          }
          windowManager.updateViewLayout(windowView,windowLayoutParams);
          startX=x;
          startY=y;
        }
 else         if (event.getAction() == MotionEvent.ACTION_UP) {
          dragging=false;
          animateToBoundsMaybe();
        }
      }
      return true;
    }
  }
;
  windowView.setBackgroundResource(R.drawable.theme_picker);
  windowManager=(WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
  preferences=ApplicationLoader.applicationContext.getSharedPreferences("themeconfig",Context.MODE_PRIVATE);
  int sidex=preferences.getInt("sidex",1);
  int sidey=preferences.getInt("sidey",0);
  float px=preferences.getFloat("px",0);
  float py=preferences.getFloat("py",0);
  try {
    windowLayoutParams=new WindowManager.LayoutParams();
    windowLayoutParams.width=editorWidth;
    windowLayoutParams.height=editorHeight;
    windowLayoutParams.x=getSideCoord(true,sidex,px,editorWidth);
    windowLayoutParams.y=getSideCoord(false,sidey,py,editorHeight);
    windowLayoutParams.format=PixelFormat.TRANSLUCENT;
    windowLayoutParams.gravity=Gravity.TOP | Gravity.LEFT;
    windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
    windowManager.addView(windowView,windowLayoutParams);
  }
 catch (  Exception e) {
    FileLog.e(e);
    return;
  }
  wallpaperUpdater=new WallpaperUpdater(activity,null,new WallpaperUpdater.WallpaperUpdaterDelegate(){
    @Override public void didSelectWallpaper(    File file,    Bitmap bitmap,    boolean gallery){
      Theme.setThemeWallpaper(themeName,bitmap,file);
    }
    @Override public void needOpenColorPicker(){
      for (int a=0; a < currentThemeDesription.size(); a++) {
        ThemeDescription description=currentThemeDesription.get(a);
        description.startEditing();
        if (a == 0) {
          editorAlert.colorPicker.setColor(description.getCurrentColor());
        }
      }
      editorAlert.setColorPickerVisible(true);
    }
  }
);
  Instance=this;
  parentActivity=activity;
  showWithAnimation();
}
