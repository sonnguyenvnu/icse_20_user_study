private void drawLetters(Canvas canvas){
  RectF rectF=new RectF();
  rectF.left=mPosX - mTextSize;
  rectF.right=mPosX + mTextSize;
  rectF.top=mTextSize / 2;
  rectF.bottom=mHeight - mTextSize / 2;
  mLettersPaint.reset();
  mLettersPaint.setStyle(Paint.Style.FILL);
  mLettersPaint.setColor(Color.parseColor("#F9F9F9"));
  mLettersPaint.setAntiAlias(true);
  canvas.drawRoundRect(rectF,mTextSize,mTextSize,mLettersPaint);
  mLettersPaint.reset();
  mLettersPaint.setStyle(Paint.Style.STROKE);
  mLettersPaint.setColor(mTextColor);
  mLettersPaint.setAntiAlias(true);
  canvas.drawRoundRect(rectF,mTextSize,mTextSize,mLettersPaint);
  for (int i=0; i < mLetters.size(); i++) {
    mLettersPaint.reset();
    mLettersPaint.setColor(mTextColor);
    mLettersPaint.setAntiAlias(true);
    mLettersPaint.setTextSize(mTextSize);
    mLettersPaint.setTextAlign(Paint.Align.CENTER);
    Paint.FontMetrics fontMetrics=mLettersPaint.getFontMetrics();
    float baseline=Math.abs(-fontMetrics.bottom - fontMetrics.top);
    float posY=mItemHeight * i + baseline / 2 + mPadding;
    if (i == mChoose) {
      mPosY=posY;
    }
 else {
      canvas.drawText(mLetters.get(i),mPosX,posY,mLettersPaint);
    }
  }
}
