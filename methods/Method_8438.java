@Override protected void onDraw(Canvas canvas){
  int top=topView != null && topView.getVisibility() == VISIBLE ? (int)topView.getTranslationY() : 0;
  int bottom=top + Theme.chat_composeShadowDrawable.getIntrinsicHeight();
  Theme.chat_composeShadowDrawable.setBounds(0,top,getMeasuredWidth(),bottom);
  Theme.chat_composeShadowDrawable.draw(canvas);
  canvas.drawRect(0,bottom,getWidth(),getHeight(),Theme.chat_composeBackgroundPaint);
}
