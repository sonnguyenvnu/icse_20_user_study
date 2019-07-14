private void drawColumn(Canvas canvas,RectF location,GregorianCalendar date,int column){
  drawColumnHeader(canvas,location,date);
  location.offset(0,columnWidth);
  for (int j=0; j < 7; j++) {
    if (!(column == nColumns - 2 && getDataOffset() == 0 && j > todayPositionInColumn)) {
      int checkmarkOffset=getDataOffset() * 7 + nDays - 7 * (column + 1) + todayPositionInColumn - j;
      drawSquare(canvas,location,date,checkmarkOffset);
    }
    date.add(Calendar.DAY_OF_MONTH,1);
    location.offset(0,columnWidth);
  }
}
