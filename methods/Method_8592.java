private void animateRemoveImpl(final ViewHolder holder){
  final View view=holder.itemView;
  mRemoveAnimations.add(holder);
  if (view instanceof DialogCell) {
    DialogCell dialogCell=(DialogCell)view;
    removingDialog=dialogCell;
    if (topClip != Integer.MAX_VALUE) {
      bottomClip=removingDialog.getMeasuredHeight() - topClip;
      removingDialog.setTopClip(topClip);
      removingDialog.setBottomClip(bottomClip);
    }
 else     if (bottomClip != Integer.MAX_VALUE) {
      topClip=removingDialog.getMeasuredHeight() - bottomClip;
      removingDialog.setTopClip(topClip);
      removingDialog.setBottomClip(bottomClip);
    }
    if (Build.VERSION.SDK_INT >= 21) {
      dialogCell.setElevation(-1);
      dialogCell.setOutlineProvider(null);
    }
    final ObjectAnimator animator=ObjectAnimator.ofFloat(dialogCell,AnimationProperties.CLIP_DIALOG_CELL_PROGRESS,1.0f).setDuration(deleteDuration);
    animator.setInterpolator(sDefaultInterpolator);
    animator.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationStart(      Animator animator){
        dispatchRemoveStarting(holder);
      }
      @Override public void onAnimationEnd(      Animator animator){
        animator.removeAllListeners();
        dialogCell.setClipProgress(0.0f);
        if (Build.VERSION.SDK_INT >= 21) {
          dialogCell.setElevation(0);
        }
        dispatchRemoveFinished(holder);
        mRemoveAnimations.remove(holder);
        dispatchFinishedWhenDone();
      }
    }
);
    animator.start();
  }
 else {
    final ViewPropertyAnimator animation=view.animate();
    animation.setDuration(deleteDuration).alpha(0).setListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationStart(      Animator animator){
        dispatchRemoveStarting(holder);
      }
      @Override public void onAnimationEnd(      Animator animator){
        animation.setListener(null);
        view.setAlpha(1);
        dispatchRemoveFinished(holder);
        mRemoveAnimations.remove(holder);
        dispatchFinishedWhenDone();
      }
    }
).start();
  }
}
