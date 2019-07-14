private static AnimatedDrawableFrameInfo.DisposalMethod fromGifDisposalMethod(int disposalMode){
  if (disposalMode == 0) {
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
  }
 else   if (disposalMode == 1) {
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
  }
 else   if (disposalMode == 2) {
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND;
  }
 else   if (disposalMode == 3) {
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS;
  }
 else {
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
  }
}
