@Override protected boolean drawChild(Canvas canvas,View child,long drawingTime){
  boolean clip=clipContent && (child == titleTextView || child == subtitleTextView || child == actionMode || child == menu || child == backButtonImageView);
  if (clip) {
    canvas.save();
    canvas.clipRect(0,-getTranslationY() + (occupyStatusBar ? AndroidUtilities.statusBarHeight : 0),getMeasuredWidth(),getMeasuredHeight());
  }
  boolean result=super.drawChild(canvas,child,drawingTime);
  if (supportsHolidayImage && !titleOverlayShown && !LocaleController.isRTL && child == titleTextView) {
    Drawable drawable=Theme.getCurrentHolidayDrawable();
    if (drawable != null) {
      TextPaint textPaint=titleTextView.getTextPaint();
      textPaint.getFontMetricsInt(fontMetricsInt);
      textPaint.getTextBounds((String)titleTextView.getText(),0,1,rect);
      int x=titleTextView.getTextStartX() + Theme.getCurrentHolidayDrawableXOffset() + (rect.width() - (drawable.getIntrinsicWidth() + Theme.getCurrentHolidayDrawableXOffset())) / 2;
      int y=titleTextView.getTextStartY() + Theme.getCurrentHolidayDrawableYOffset() + (int)Math.ceil((titleTextView.getTextHeight() - rect.height()) / 2.0f);
      drawable.setBounds(x,y - drawable.getIntrinsicHeight(),x + drawable.getIntrinsicWidth(),y);
      drawable.draw(canvas);
      if (Theme.canStartHolidayAnimation()) {
        if (snowflakesEffect == null) {
          snowflakesEffect=new SnowflakesEffect();
        }
      }
 else       if (!manualStart) {
        if (snowflakesEffect != null) {
          snowflakesEffect=null;
        }
      }
      if (snowflakesEffect != null) {
        snowflakesEffect.onDraw(this,canvas);
      }
 else       if (fireworksEffect != null) {
        fireworksEffect.onDraw(this,canvas);
      }
    }
  }
  if (clip) {
    canvas.restore();
  }
  return result;
}
