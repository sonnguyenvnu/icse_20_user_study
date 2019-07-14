@SuppressWarnings("WeakerAccess") void postDispatchSwipe(final RecoverAnimation anim,final int swipeDir){
  mRecyclerView.post(new Runnable(){
    @Override public void run(){
      if (mRecyclerView != null && mRecyclerView.isAttachedToWindow() && !anim.mOverridden && anim.mViewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
        final RecyclerView.ItemAnimator animator=mRecyclerView.getItemAnimator();
        if ((animator == null || !animator.isRunning(null)) && !hasRunningRecoverAnim()) {
          mCallback.onSwiped(anim.mViewHolder,swipeDir);
        }
 else {
          mRecyclerView.post(this);
        }
      }
    }
  }
);
}
