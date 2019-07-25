/** 
 * @since 6.0.3
 */
@Override public void end(){
  if (mSideEffectLastFlag && mNeededForNext < mSideEffectLastEpsilon) {
    add(mSideEffectLastX,mSideEffectLastY,mSideEffectLastOrientation);
  }
  super.end();
}
