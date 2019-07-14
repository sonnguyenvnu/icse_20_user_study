public static void show(MsgView msgView,int num){
  if (msgView == null) {
    return;
  }
  RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams)msgView.getLayoutParams();
  DisplayMetrics dm=msgView.getResources().getDisplayMetrics();
  msgView.setVisibility(View.VISIBLE);
  if (num <= 0) {
    msgView.setStrokeWidth(0);
    msgView.setText("");
    lp.width=(int)(5 * dm.density);
    lp.height=(int)(5 * dm.density);
    msgView.setLayoutParams(lp);
  }
 else {
    lp.height=(int)(18 * dm.density);
    if (num > 0 && num < 10) {
      lp.width=(int)(18 * dm.density);
      msgView.setText(num + "");
    }
 else     if (num > 9 && num < 100) {
      lp.width=RelativeLayout.LayoutParams.WRAP_CONTENT;
      msgView.setPadding((int)(6 * dm.density),0,(int)(6 * dm.density),0);
      msgView.setText(num + "");
    }
 else {
      lp.width=RelativeLayout.LayoutParams.WRAP_CONTENT;
      msgView.setPadding((int)(6 * dm.density),0,(int)(6 * dm.density),0);
      msgView.setText("99+");
    }
    msgView.setLayoutParams(lp);
  }
}
