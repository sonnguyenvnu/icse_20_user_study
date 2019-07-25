public void reset(){
  stop();
  mAnimatedSVGState.mSVGDrawable.setTranslationX(0);
  mAnimatedSVGState.mSVGDrawable.setTranslationY(0);
  mAnimatedSVGState.mSVGDrawable.setScaleX(1.0f);
  mAnimatedSVGState.mSVGDrawable.setScaleY(1.0f);
  mAnimatedSVGState.mSVGDrawable.setRotation(0);
  mAnimatedSVGState.mSVGDrawable.setAlpha(0xFF);
}
