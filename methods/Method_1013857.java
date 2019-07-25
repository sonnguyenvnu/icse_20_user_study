public void reorder(boolean toTop,boolean isFromGUI){
  if (isFromGUI)   locationTop=toTop;
  toTheTop=toTop;
  LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)this.findViewById(R.id.panelHandle).getLayoutParams();
  float d=MainScreen.getMainContext().getResources().getDisplayMetrics().density;
  if (toTheTop) {
    if (!isFromGUI && (moving != 0) && !locationTop && handle) {
      moving=moving + 5;
      int margin=(int)(downSpace - moving);
      if (margin < 0)       margin=0;
      lp.topMargin=margin;
    }
 else     lp.topMargin=0;
  }
 else {
    lp.topMargin=(int)(downSpace);
  }
  mHandle.setLayoutParams(lp);
}
