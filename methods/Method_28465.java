private void drawPlaceholder(Canvas canvas){
  if (!isInEditMode())   return;
  canvas.getClipBounds(rect);
  int width=rect.width();
  int verticalBlockNumber=7;
  int horizontalBlockNumber=getHorizontalBlockNumber(lastWeeks * 7,verticalBlockNumber);
  float marginBlock=(1.0F - 0.1F);
  float blockWidth=width / (float)horizontalBlockNumber * marginBlock;
  float spaceWidth=width / (float)horizontalBlockNumber - blockWidth;
  float monthTextHeight=(displayMonth) ? blockWidth * 1.5F : 0;
  float topMargin=(displayMonth) ? 7f : 0;
  monthTextPaint.setTextSize(monthTextHeight);
  int height=(int)((blockWidth + spaceWidth) * 7 + topMargin + monthTextHeight);
  blockPaint.setColor(backgroundBaseColor);
  canvas.drawRect(0,(topMargin + monthTextHeight),width,height + monthTextHeight,blockPaint);
  float x=0;
  float y=0 * (blockWidth + spaceWidth) + (topMargin + monthTextHeight);
  for (int i=1; i < (lastWeeks * 7) + 1; i++) {
    blockPaint.setColor(ColorsUtils.calculateLevelColor(baseColor,baseEmptyColor,0));
    canvas.drawRect(x,y,x + blockWidth,y + blockWidth,blockPaint);
    if (i % 7 == 0) {
      x+=blockWidth + spaceWidth;
      y=topMargin + monthTextHeight;
    }
 else {
      y+=blockWidth + spaceWidth;
    }
  }
  ViewGroup.LayoutParams ll=getLayoutParams();
  ll.height=height;
  setLayoutParams(ll);
}
