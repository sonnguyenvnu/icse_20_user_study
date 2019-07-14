private void drawSquare(Canvas canvas,RectF location,GregorianCalendar date,int checkmarkOffset){
  if (checkmarkOffset >= checkmarks.length)   pSquareBg.setColor(colors[0]);
 else {
    int checkmark=checkmarks[checkmarkOffset];
    if (checkmark == 0)     pSquareBg.setColor(colors[0]);
 else     if (checkmark < target) {
      pSquareBg.setColor(isNumerical ? textColor : colors[1]);
    }
 else     pSquareBg.setColor(colors[2]);
  }
  pSquareFg.setColor(reverseTextColor);
  canvas.drawRect(location,pSquareBg);
  String text=Integer.toString(date.get(Calendar.DAY_OF_MONTH));
  canvas.drawText(text,location.centerX(),location.centerY() + squareTextOffset,pSquareFg);
}
