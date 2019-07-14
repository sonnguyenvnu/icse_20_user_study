private void initDisplayedBitmap(CreateOptions createOptions){
  final CloseableReference<Bitmap> oldDisplayedReference=mDisplayedBitmap;
  final int originalW=mOriginalBitmap.get().getWidth();
  final int originalH=mOriginalBitmap.get().getHeight();
switch (createOptions) {
case BASIC:
    mDisplayedBitmap=mPlatformBitmapFactory.createBitmap(mOriginalBitmap.get());
  break;
case CROPPED:
mDisplayedBitmap=mPlatformBitmapFactory.createBitmap(mOriginalBitmap.get(),0,0,originalW / 2,originalH / 2);
break;
case SCALED:
mDisplayedBitmap=mPlatformBitmapFactory.createScaledBitmap(mOriginalBitmap.get(),4,4,true);
break;
case TRANSFORMED:
mDisplayedBitmap=mPlatformBitmapFactory.createBitmap(mOriginalBitmap.get(),0,0,originalW,originalH,getMatrix(2f,3f,60.0f),true);
break;
}
mImageView.setImageBitmap(mDisplayedBitmap.get());
CloseableReference.closeSafely(oldDisplayedReference);
}
