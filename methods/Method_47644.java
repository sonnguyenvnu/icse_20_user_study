private void drawColumnHeader(Canvas canvas,RectF location,GregorianCalendar date){
  String month=dfMonth.format(date.getTime());
  String year=dfYear.format(date.getTime());
  String text=null;
  if (!month.equals(previousMonth))   text=previousMonth=month;
 else   if (!year.equals(previousYear))   text=previousYear=year;
  if (text != null) {
    canvas.drawText(text,location.left + headerOverflow,location.bottom - headerTextOffset,pTextHeader);
    headerOverflow+=pTextHeader.measureText(text) + columnWidth * 0.2f;
  }
  headerOverflow=Math.max(0,headerOverflow - columnWidth);
}
