private static AnimatedDrawableFrameInfo.DisposalMethod translateFrameDisposal(int raw){
switch (raw) {
case 2:
    return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_BACKGROUND;
case 3:
  return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_TO_PREVIOUS;
case 1:
default :
return AnimatedDrawableFrameInfo.DisposalMethod.DISPOSE_DO_NOT;
}
}
