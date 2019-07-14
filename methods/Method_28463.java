public Bitmap drawOnCanvas(List<ContributionsDay> contributionsFilter,List<ContributionsDay> contributions){
  if ((contributionsFilter == null || contributions == null) || (contributionsFilter.isEmpty() || contributions.isEmpty())) {
    return null;
  }
  if (bitmap == null) {
    int padding=getResources().getDimensionPixelSize(R.dimen.spacing_large);
    int width=point.x - padding;
    int verticalBlockNumber=7;
    int horizontalBlockNumber=getHorizontalBlockNumber(contributionsFilter.size(),verticalBlockNumber);
    float marginBlock=(1.0F - 0.1F);
    float blockWidth=width / (float)horizontalBlockNumber * marginBlock;
    float spaceWidth=width / (float)horizontalBlockNumber - blockWidth;
    float topMargin=(displayMonth) ? 7f : 0;
    float monthTextHeight=(displayMonth) ? blockWidth * 1.5F : 0;
    int height=(int)((blockWidth + spaceWidth) * 7 + topMargin + monthTextHeight);
    bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    Canvas canvas1=new Canvas(bitmap);
    blockPaint.setColor(backgroundBaseColor);
    canvas1.drawRect(0,(topMargin + monthTextHeight),width,height + monthTextHeight,blockPaint);
    monthTextPaint.setColor(textColor);
    monthTextPaint.setTextSize(monthTextHeight);
    int currentWeekDay=DatesUtils.getWeekDayFromDate(contributions.get(0).year,contributions.get(0).month,contributions.get(0).day);
    float x=0;
    float y=(currentWeekDay - 7) % 7 * (blockWidth + spaceWidth) + (topMargin + monthTextHeight);
    for (    ContributionsDay day : contributionsFilter) {
      blockPaint.setColor(ColorsUtils.calculateLevelColor(baseColor,baseEmptyColor,day.level));
      canvas1.drawRect(x,y,x + blockWidth,y + blockWidth,blockPaint);
      if (DatesUtils.isFirstDayOfWeek(day.year,day.month,day.day + 1)) {
        x+=blockWidth + spaceWidth;
        y=topMargin + monthTextHeight;
        if (DatesUtils.isFirstWeekOfMount(day.year,day.month,day.day + 1)) {
          canvas1.drawText(DatesUtils.getShortMonthName(day.year,day.month,day.day + 1),x,monthTextHeight,monthTextPaint);
        }
      }
 else {
        y+=blockWidth + spaceWidth;
      }
    }
    this.height=height;
  }
  return bitmap;
}
