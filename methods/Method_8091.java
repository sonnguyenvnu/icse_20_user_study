public void setTextAndValue(CharSequence text,String[] value,boolean faq,boolean divider){
  LayoutParams layoutParams=(LayoutParams)textView.getLayoutParams();
  if (faq) {
    valueTextView.setText(text);
    SpannableStringBuilder builder=new SpannableStringBuilder();
    for (int a=0; a < value.length; a++) {
      if (a != 0) {
        builder.append(" > ");
        Drawable drawable=getContext().getResources().getDrawable(R.drawable.settings_arrow).mutate();
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText),PorterDuff.Mode.MULTIPLY));
        builder.setSpan(new VerticalImageSpan(drawable),builder.length() - 2,builder.length() - 1,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      builder.append(value[a]);
    }
    textView.setText(builder);
    valueTextView.setVisibility(VISIBLE);
    layoutParams.topMargin=AndroidUtilities.dp(10);
  }
 else {
    textView.setText(text);
    if (value != null) {
      SpannableStringBuilder builder=new SpannableStringBuilder();
      for (int a=0; a < value.length; a++) {
        if (a != 0) {
          builder.append(" > ");
          Drawable drawable=getContext().getResources().getDrawable(R.drawable.settings_arrow).mutate();
          drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
          drawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2),PorterDuff.Mode.MULTIPLY));
          builder.setSpan(new VerticalImageSpan(drawable),builder.length() - 2,builder.length() - 1,SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        builder.append(value[a]);
      }
      valueTextView.setText(builder);
      valueTextView.setVisibility(VISIBLE);
      layoutParams.topMargin=AndroidUtilities.dp(10);
    }
 else {
      layoutParams.topMargin=AndroidUtilities.dp(21);
      valueTextView.setVisibility(GONE);
    }
  }
  layoutParams.leftMargin=layoutParams.rightMargin=AndroidUtilities.dp(16);
  layoutParams=(LayoutParams)valueTextView.getLayoutParams();
  layoutParams.leftMargin=layoutParams.rightMargin=AndroidUtilities.dp(16);
  imageView.setVisibility(GONE);
  needDivider=divider;
  setWillNotDraw(!needDivider);
  left=16;
}
