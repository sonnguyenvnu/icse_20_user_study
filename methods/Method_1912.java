private void increaseFrameTypeCount(@BitmapAnimationBackend.FrameType int frameType){
switch (frameType) {
case BitmapAnimationBackend.FRAME_TYPE_CACHED:
    mCachedCount++;
  break;
case BitmapAnimationBackend.FRAME_TYPE_REUSED:
mReusedCount++;
break;
case BitmapAnimationBackend.FRAME_TYPE_CREATED:
mCreatedCount++;
break;
case BitmapAnimationBackend.FRAME_TYPE_FALLBACK:
mFallbackCount++;
break;
case BitmapAnimationBackend.FRAME_TYPE_UNKNOWN:
default :
mUnknownCount++;
break;
}
}
