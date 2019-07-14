public void refresh(){
  if (backgroundPaint == null || frame == null || ring == null)   return;
  StyledResources res=new StyledResources(getContext());
  String text;
  int bgColor;
  int fgColor;
switch (checkmarkValue) {
case Checkmark.CHECKED_EXPLICITLY:
    text=getResources().getString(R.string.fa_check);
  bgColor=activeColor;
fgColor=res.getColor(R.attr.highContrastReverseTextColor);
setShadowAlpha(0x4f);
rebuildBackground();
backgroundPaint.setColor(bgColor);
frame.setBackgroundDrawable(background);
break;
case Checkmark.CHECKED_IMPLICITLY:
text=getResources().getString(R.string.fa_check);
bgColor=res.getColor(R.attr.cardBackgroundColor);
fgColor=res.getColor(R.attr.mediumContrastTextColor);
setShadowAlpha(0x00);
rebuildBackground();
break;
case Checkmark.UNCHECKED:
default :
text=getResources().getString(R.string.fa_times);
bgColor=res.getColor(R.attr.cardBackgroundColor);
fgColor=res.getColor(R.attr.mediumContrastTextColor);
setShadowAlpha(0x00);
rebuildBackground();
break;
}
ring.setPercentage(percentage);
ring.setColor(fgColor);
ring.setBackgroundColor(bgColor);
ring.setText(text);
label.setText(name);
label.setTextColor(fgColor);
requestLayout();
postInvalidate();
}
