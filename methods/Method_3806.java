/** 
 * Returns the animation type or 0 if cannot be found.
 */
@SuppressWarnings("WeakerAccess") void endRecoverAnimation(ViewHolder viewHolder,boolean override){
  final int recoverAnimSize=mRecoverAnimations.size();
  for (int i=recoverAnimSize - 1; i >= 0; i--) {
    final RecoverAnimation anim=mRecoverAnimations.get(i);
    if (anim.mViewHolder == viewHolder) {
      anim.mOverridden|=override;
      if (!anim.mEnded) {
        anim.cancel();
      }
      mRecoverAnimations.remove(i);
      return;
    }
  }
}
