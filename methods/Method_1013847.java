public void release(){
  AlmaShotGroupShot.Release(mNumOfFrame);
  for (  int yuv : mYUVBufferList)   SwapHeap.FreeFromHeap(yuv);
  mYUVBufferList.clear();
  if (mPreviewBitmap != null) {
    mPreviewBitmap.recycle();
    mPreviewBitmap=null;
  }
  if (mBuffer != null) {
    mBuffer.recycle();
    mBuffer=null;
  }
  ARGBBuffer=null;
  mCrop=null;
  mFacesList=null;
  mFacesBitmapsList=null;
  mFacesBitmapsRect=null;
  mLayoutData=null;
  mChosenFaces=null;
  if (mOutNV21 != 0) {
    SwapHeap.FreeFromHeap(mOutNV21);
    mOutNV21=0;
  }
  mInstance=null;
}
