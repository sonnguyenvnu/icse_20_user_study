private void drawAxis(Canvas canvas,RectF location){
  float verticalOffset=pTextHeader.getFontSpacing() * 0.4f;
  for (  String day : DateUtils.getLocaleDayNames(Calendar.SHORT)) {
    location.offset(0,columnWidth);
    canvas.drawText(day,location.left + headerTextOffset,location.centerY() + verticalOffset,pTextHeader);
  }
}
