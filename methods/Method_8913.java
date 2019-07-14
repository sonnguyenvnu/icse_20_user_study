private FrameLayout buttonForText(final boolean stroke,String text,boolean selected){
  FrameLayout button=new FrameLayout(getContext()){
    @Override public boolean onInterceptTouchEvent(    MotionEvent ev){
      return true;
    }
  }
;
  button.setBackgroundDrawable(Theme.getSelectorDrawable(false));
  button.setOnClickListener(v -> {
    setStroke(stroke);
    if (popupWindow != null && popupWindow.isShowing()) {
      popupWindow.dismiss(true);
    }
  }
);
  EditTextOutline textView=new EditTextOutline(getContext());
  textView.setBackgroundColor(Color.TRANSPARENT);
  textView.setEnabled(false);
  textView.setStrokeWidth(AndroidUtilities.dp(3));
  textView.setTextColor(stroke ? Color.WHITE : Color.BLACK);
  textView.setStrokeColor(stroke ? Color.BLACK : Color.TRANSPARENT);
  textView.setPadding(AndroidUtilities.dp(2),0,AndroidUtilities.dp(2),0);
  textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
  textView.setTypeface(null,Typeface.BOLD);
  textView.setTag(stroke);
  textView.setText(text);
  button.addView(textView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.CENTER_VERTICAL,46,0,16,0));
  if (selected) {
    ImageView check=new ImageView(getContext());
    check.setImageResource(R.drawable.ic_ab_done);
    check.setScaleType(ImageView.ScaleType.CENTER);
    button.addView(check,LayoutHelper.createFrame(50,LayoutHelper.MATCH_PARENT));
  }
  return button;
}
