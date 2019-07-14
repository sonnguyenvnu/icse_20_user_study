/** 
 * Draws overlay with request state for easier visual inspection. 
 */
public void onDraw(final Canvas canvas){
  mPaint.setColor(0xC0000000);
  mTextRect.set(0,0,mView.getWidth(),35);
  canvas.drawRect(mTextRect,mPaint);
  mPaint.setColor(Color.WHITE);
  canvas.drawText("[" + mTag + "]",10,15,mPaint);
  String message="Not started";
switch (mState) {
case STARTED:
    message="Loading...";
  break;
case SUCCESS:
message="Loaded after " + (mFinishTime - mStartTime) + "ms";
break;
case FAILURE:
message="Failed after " + (mFinishTime - mStartTime) + "ms";
break;
case CANCELLATION:
message="Cancelled after " + (mFinishTime - mStartTime) + "ms";
break;
}
canvas.drawText(message,10,30,mPaint);
}
