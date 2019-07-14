public TextureView show(Activity activity,PhotoViewer viewer,EmbedBottomSheet sheet,View controls,float aspectRatio,int rotation,WebView webview){
  parentSheet=sheet;
  parentActivity=activity;
  photoViewer=viewer;
  windowView=new FrameLayout(activity){
    @Override public boolean onInterceptTouchEvent(    MotionEvent event){
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
          if (controlsView != null) {
            ((ViewParent)controlsView).requestDisallowInterceptTouchEvent(true);
          }
          return true;
        }
      }
      return super.onInterceptTouchEvent(event);
    }
    @Override public void requestDisallowInterceptTouchEvent(    boolean disallowIntercept){
      super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      if (!dragging) {
        return false;
      }
      float x=event.getRawX();
      float y=event.getRawY();
      if (event.getAction() == MotionEvent.ACTION_MOVE) {
        float dx=(x - startX);
        float dy=(y - startY);
        windowLayoutParams.x+=dx;
        windowLayoutParams.y+=dy;
        int maxDiff=videoWidth / 2;
        if (windowLayoutParams.x < -maxDiff) {
          windowLayoutParams.x=-maxDiff;
        }
 else         if (windowLayoutParams.x > AndroidUtilities.displaySize.x - windowLayoutParams.width + maxDiff) {
          windowLayoutParams.x=AndroidUtilities.displaySize.x - windowLayoutParams.width + maxDiff;
        }
        float alpha=1.0f;
        if (windowLayoutParams.x < 0) {
          alpha=1.0f + windowLayoutParams.x / (float)maxDiff * 0.5f;
        }
 else         if (windowLayoutParams.x > AndroidUtilities.displaySize.x - windowLayoutParams.width) {
          alpha=1.0f - (windowLayoutParams.x - AndroidUtilities.displaySize.x + windowLayoutParams.width) / (float)maxDiff * 0.5f;
        }
        if (windowView.getAlpha() != alpha) {
          windowView.setAlpha(alpha);
        }
        maxDiff=0;
        if (windowLayoutParams.y < -maxDiff) {
          windowLayoutParams.y=-maxDiff;
        }
 else         if (windowLayoutParams.y > AndroidUtilities.displaySize.y - windowLayoutParams.height + maxDiff) {
          windowLayoutParams.y=AndroidUtilities.displaySize.y - windowLayoutParams.height + maxDiff;
        }
        windowManager.updateViewLayout(windowView,windowLayoutParams);
        startX=x;
        startY=y;
      }
 else       if (event.getAction() == MotionEvent.ACTION_UP) {
        dragging=false;
        animateToBoundsMaybe();
      }
      return true;
    }
  }
;
  if (aspectRatio > 1) {
    videoWidth=AndroidUtilities.dp(192);
    videoHeight=(int)(videoWidth / aspectRatio);
  }
 else {
    videoHeight=AndroidUtilities.dp(192);
    videoWidth=(int)(videoHeight * aspectRatio);
  }
  AspectRatioFrameLayout aspectRatioFrameLayout=new AspectRatioFrameLayout(activity);
  aspectRatioFrameLayout.setAspectRatio(aspectRatio,rotation);
  windowView.addView(aspectRatioFrameLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  TextureView textureView;
  if (webview != null) {
    ViewGroup parent=(ViewGroup)webview.getParent();
    if (parent != null) {
      parent.removeView(webview);
    }
    aspectRatioFrameLayout.addView(webview,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    textureView=null;
  }
 else {
    textureView=new TextureView(activity);
    aspectRatioFrameLayout.addView(textureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  }
  if (controls == null) {
    controlsView=new MiniControlsView(activity,viewer != null);
  }
 else {
    controlsView=controls;
  }
  windowView.addView(controlsView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  windowManager=(WindowManager)ApplicationLoader.applicationContext.getSystemService(Context.WINDOW_SERVICE);
  preferences=ApplicationLoader.applicationContext.getSharedPreferences("pipconfig",Context.MODE_PRIVATE);
  int sidex=preferences.getInt("sidex",1);
  int sidey=preferences.getInt("sidey",0);
  float px=preferences.getFloat("px",0);
  float py=preferences.getFloat("py",0);
  try {
    windowLayoutParams=new WindowManager.LayoutParams();
    windowLayoutParams.width=videoWidth;
    windowLayoutParams.height=videoHeight;
    windowLayoutParams.x=getSideCoord(true,sidex,px,videoWidth);
    windowLayoutParams.y=getSideCoord(false,sidey,py,videoHeight);
    windowLayoutParams.format=PixelFormat.TRANSLUCENT;
    windowLayoutParams.gravity=Gravity.TOP | Gravity.LEFT;
    if (Build.VERSION.SDK_INT >= 26) {
      windowLayoutParams.type=WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
    }
 else {
      windowLayoutParams.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
    }
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
    windowManager.addView(windowView,windowLayoutParams);
  }
 catch (  Exception e) {
    FileLog.e(e);
    return null;
  }
  return textureView;
}
