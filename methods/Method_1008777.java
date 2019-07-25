private void init(Context context){
  setWillNotDraw(false);
  setVisibility(INVISIBLE);
  lineStroke=Utils.dpToPx(4);
  isReady=false;
  isRevealAnimationEnabled=true;
  dismissOnTouch=false;
  isPerformClick=false;
  enableDismissAfterShown=false;
  dismissOnBackPress=false;
  handler=new Handler();
  preferencesManager=new PreferencesManager(context);
  eraser=new Paint();
  eraser.setColor(0xFFFFFFFF);
  eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
  eraser.setFlags(Paint.ANTI_ALIAS_FLAG);
}
