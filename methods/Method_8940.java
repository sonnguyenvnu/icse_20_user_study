public void show(Activity activity,Runnable closeRunnable){
  if (activity == null) {
    return;
  }
  instance=this;
  onCloseRunnable=closeRunnable;
  windowView=new FrameLayout(activity){
    @Override public boolean onInterceptTouchEvent(    MotionEvent event){
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        startX=event.getRawX();
        startY=event.getRawY();
        startDragging=true;
      }
      return true;
    }
    @Override public void requestDisallowInterceptTouchEvent(    boolean disallowIntercept){
      super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
    @Override public boolean onTouchEvent(    MotionEvent event){
      if (!startDragging && !dragging) {
        return false;
      }
      float x=event.getRawX();
      float y=event.getRawY();
      if (event.getAction() == MotionEvent.ACTION_MOVE) {
        float dx=(x - startX);
        float dy=(y - startY);
        if (startDragging) {
          if (Math.abs(dx) >= AndroidUtilities.getPixelsInCM(0.3f,true) || Math.abs(dy) >= AndroidUtilities.getPixelsInCM(0.3f,false)) {
            dragging=true;
            startDragging=false;
          }
        }
 else         if (dragging) {
          windowLayoutParams.x+=dx;
          windowLayoutParams.y+=dy;
          int maxDiff=videoWidth / 2;
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
      }
 else       if (event.getAction() == MotionEvent.ACTION_UP) {
        if (startDragging && !dragging) {
          MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
          if (messageObject != null) {
            if (MediaController.getInstance().isMessagePaused()) {
              MediaController.getInstance().playMessage(messageObject);
            }
 else {
              MediaController.getInstance().pauseMessage(messageObject);
            }
          }
        }
        dragging=false;
        startDragging=false;
        animateToBoundsMaybe();
      }
      return true;
    }
    @Override protected void onDraw(    Canvas canvas){
      if (Theme.chat_roundVideoShadow != null) {
        Theme.chat_roundVideoShadow.setAlpha((int)(getAlpha() * 255));
        Theme.chat_roundVideoShadow.setBounds(AndroidUtilities.dp(1),AndroidUtilities.dp(2),AndroidUtilities.dp(125),AndroidUtilities.dp(125));
        Theme.chat_roundVideoShadow.draw(canvas);
        Theme.chat_docBackPaint.setColor(Theme.getColor(Theme.key_chat_inBubble));
        Theme.chat_docBackPaint.setAlpha((int)(getAlpha() * 255));
        canvas.drawCircle(AndroidUtilities.dp(3 + 60),AndroidUtilities.dp(3 + 60),AndroidUtilities.dp(59.5f),Theme.chat_docBackPaint);
      }
    }
  }
;
  windowView.setWillNotDraw(false);
  videoWidth=AndroidUtilities.dp(120 + 6);
  videoHeight=AndroidUtilities.dp(120 + 6);
  if (Build.VERSION.SDK_INT >= 21) {
    aspectRatioFrameLayout=new AspectRatioFrameLayout(activity){
      @Override protected boolean drawChild(      Canvas canvas,      View child,      long drawingTime){
        boolean result=super.drawChild(canvas,child,drawingTime);
        if (child == textureView) {
          MessageObject currentMessageObject=MediaController.getInstance().getPlayingMessageObject();
          if (currentMessageObject != null) {
            rect.set(AndroidUtilities.dpf2(1.5f),AndroidUtilities.dpf2(1.5f),getMeasuredWidth() - AndroidUtilities.dpf2(1.5f),getMeasuredHeight() - AndroidUtilities.dpf2(1.5f));
            canvas.drawArc(rect,-90,360 * currentMessageObject.audioProgress,false,Theme.chat_radialProgressPaint);
          }
        }
        return result;
      }
    }
;
    aspectRatioFrameLayout.setOutlineProvider(new ViewOutlineProvider(){
      @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void getOutline(      View view,      Outline outline){
        outline.setOval(0,0,AndroidUtilities.dp(120),AndroidUtilities.dp(120));
      }
    }
);
    aspectRatioFrameLayout.setClipToOutline(true);
  }
 else {
    final Paint aspectPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    aspectPaint.setColor(0xff000000);
    aspectPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    aspectRatioFrameLayout=new AspectRatioFrameLayout(activity){
      @Override protected void onSizeChanged(      int w,      int h,      int oldw,      int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        aspectPath.reset();
        aspectPath.addCircle(w / 2,h / 2,w / 2,Path.Direction.CW);
        aspectPath.toggleInverseFillType();
      }
      @Override protected void dispatchDraw(      Canvas canvas){
        super.dispatchDraw(canvas);
        canvas.drawPath(aspectPath,aspectPaint);
      }
      @Override protected boolean drawChild(      Canvas canvas,      View child,      long drawingTime){
        boolean result;
        try {
          result=super.drawChild(canvas,child,drawingTime);
        }
 catch (        Throwable ignore) {
          result=false;
        }
        if (child == textureView) {
          MessageObject currentMessageObject=MediaController.getInstance().getPlayingMessageObject();
          if (currentMessageObject != null) {
            rect.set(AndroidUtilities.dpf2(1.5f),AndroidUtilities.dpf2(1.5f),getMeasuredWidth() - AndroidUtilities.dpf2(1.5f),getMeasuredHeight() - AndroidUtilities.dpf2(1.5f));
            canvas.drawArc(rect,-90,360 * currentMessageObject.audioProgress,false,Theme.chat_radialProgressPaint);
          }
        }
        return result;
      }
    }
;
    aspectRatioFrameLayout.setLayerType(View.LAYER_TYPE_HARDWARE,null);
  }
  aspectRatioFrameLayout.setAspectRatio(1.0f,0);
  windowView.addView(aspectRatioFrameLayout,LayoutHelper.createFrame(120,120,Gravity.LEFT | Gravity.TOP,3,3,0,0));
  windowView.setAlpha(1.0f);
  windowView.setScaleX(0.8f);
  windowView.setScaleY(0.8f);
  textureView=new TextureView(activity);
  aspectRatioFrameLayout.addView(textureView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  imageView=new ImageView(activity);
  aspectRatioFrameLayout.addView(imageView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  imageView.setVisibility(View.INVISIBLE);
  windowManager=(WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
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
    windowLayoutParams.type=WindowManager.LayoutParams.LAST_APPLICATION_WINDOW;
    windowLayoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
    windowManager.addView(windowView,windowLayoutParams);
  }
 catch (  Exception e) {
    FileLog.e(e);
    return;
  }
  parentActivity=activity;
  currentAccount=UserConfig.selectedAccount;
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.messagePlayingProgressDidChanged);
  runShowHideAnimation(true);
}
