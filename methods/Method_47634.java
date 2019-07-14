private void drawFooter(Canvas canvas,RectF rect,GregorianCalendar date){
  Date time=date.getTime();
  canvas.drawText(dfMonth.format(time),rect.centerX(),rect.centerY() - 0.1f * em,pText);
  if (date.get(Calendar.MONTH) == 1)   canvas.drawText(dfYear.format(time),rect.centerX(),rect.centerY() + 0.9f * em,pText);
}
