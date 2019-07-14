private void drawFooter(Canvas canvas,RectF rect,Timestamp currentDate){
  String yearText=dfYear.format(currentDate.toJavaDate());
  String monthText=dfMonth.format(currentDate.toJavaDate());
  String dayText=dfDay.format(currentDate.toJavaDate());
  GregorianCalendar calendar=currentDate.toCalendar();
  String text;
  int year=calendar.get(Calendar.YEAR);
  boolean shouldPrintYear=true;
  if (yearText.equals(previousYearText))   shouldPrintYear=false;
  if (bucketSize >= 365 && (year % 2) != 0)   shouldPrintYear=false;
  if (skipYear > 0) {
    skipYear--;
    shouldPrintYear=false;
  }
  if (shouldPrintYear) {
    previousYearText=yearText;
    previousMonthText="";
    pText.setTextAlign(Paint.Align.CENTER);
    canvas.drawText(yearText,rect.centerX(),rect.bottom + em * 2.2f,pText);
    skipYear=1;
  }
  if (bucketSize < 365) {
    if (!monthText.equals(previousMonthText)) {
      previousMonthText=monthText;
      text=monthText;
    }
 else {
      text=dayText;
    }
    pText.setTextAlign(Paint.Align.CENTER);
    canvas.drawText(text,rect.centerX(),rect.bottom + em * 1.2f,pText);
  }
}
