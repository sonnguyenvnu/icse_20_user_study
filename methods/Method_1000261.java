public void snapshot(){
  if (mStub.getChildCount() <= 0)   return;
  if (mSnapshot.getParent() != null)   return;
  BoostFlutterView flutterView=(BoostFlutterView)mStub.getChildAt(0);
  mBitmap=flutterView.getBitmap();
  if (mBitmap != null && !mBitmap.isRecycled()) {
    mSnapshot.setImageBitmap(mBitmap);
    addView(mSnapshot);
  }
}
