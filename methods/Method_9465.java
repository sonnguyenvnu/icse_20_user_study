private TextView createCaptionTextView(){
  TextView textView=new TextView(actvityContext){
    @Override public boolean onTouchEvent(    MotionEvent event){
      return bottomTouchEnabled && super.onTouchEvent(event);
    }
  }
;
  textView.setMovementMethod(new LinkMovementMethodMy());
  textView.setPadding(AndroidUtilities.dp(20),AndroidUtilities.dp(8),AndroidUtilities.dp(20),AndroidUtilities.dp(8));
  textView.setLinkTextColor(0xffffffff);
  textView.setTextColor(0xffffffff);
  textView.setHighlightColor(0x33ffffff);
  textView.setGravity(Gravity.CENTER_VERTICAL | (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT));
  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
  textView.setVisibility(View.INVISIBLE);
  textView.setOnClickListener(v -> {
    if (!needCaptionLayout) {
      return;
    }
    openCaptionEnter();
  }
);
  return textView;
}
