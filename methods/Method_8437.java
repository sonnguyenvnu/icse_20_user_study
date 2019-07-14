@Override protected void onDraw(Canvas canvas){
  float d=AndroidUtilities.dp(5);
  if (colorKey != null) {
    paint.setColor((Theme.getColor(colorKey) & 0x00ffffff) | 0xb4000000);
  }
 else {
    paint.setColor(0xffbbbbbb);
  }
  int x;
  currentPage=viewPager.getCurrentItem();
  for (int a=0; a < pagesCount; a++) {
    if (a == currentPage) {
      continue;
    }
    x=a * AndroidUtilities.dp(11);
    rect.set(x,0,x + AndroidUtilities.dp(5),AndroidUtilities.dp(5));
    canvas.drawRoundRect(rect,AndroidUtilities.dp(2.5f),AndroidUtilities.dp(2.5f),paint);
  }
  if (selectedColorKey != null) {
    paint.setColor(Theme.getColor(selectedColorKey));
  }
 else {
    paint.setColor(0xff2ca5e0);
  }
  x=currentPage * AndroidUtilities.dp(11);
  if (progress != 0) {
    if (scrollPosition >= currentPage) {
      rect.set(x,0,x + AndroidUtilities.dp(5) + AndroidUtilities.dp(11) * progress,AndroidUtilities.dp(5));
    }
 else {
      rect.set(x - AndroidUtilities.dp(11) * (1.0f - progress),0,x + AndroidUtilities.dp(5),AndroidUtilities.dp(5));
    }
  }
 else {
    rect.set(x,0,x + AndroidUtilities.dp(5),AndroidUtilities.dp(5));
  }
  canvas.drawRoundRect(rect,AndroidUtilities.dp(2.5f),AndroidUtilities.dp(2.5f),paint);
}
