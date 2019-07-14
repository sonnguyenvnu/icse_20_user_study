private TextView createTextView(T marqueeItem){
  TextView textView=(TextView)getChildAt((getDisplayedChild() + 1) % 3);
  if (textView == null) {
    textView=new TextView(getContext());
    textView.setGravity(gravity | Gravity.CENTER_VERTICAL);
    textView.setTextColor(textColor);
    textView.setTextSize(textSize);
    textView.setIncludeFontPadding(true);
    textView.setSingleLine(singleLine);
    if (singleLine) {
      textView.setMaxLines(1);
      textView.setEllipsize(TextUtils.TruncateAt.END);
    }
    if (typeface != null) {
      textView.setTypeface(typeface);
    }
    textView.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        if (onItemClickListener != null) {
          onItemClickListener.onItemClick(getPosition(),(TextView)v);
        }
      }
    }
);
  }
  CharSequence message="";
  if (marqueeItem instanceof CharSequence) {
    message=(CharSequence)marqueeItem;
  }
 else   if (marqueeItem instanceof IMarqueeItem) {
    message=((IMarqueeItem)marqueeItem).marqueeMessage();
  }
  textView.setText(message);
  textView.setTag(position);
  return textView;
}
