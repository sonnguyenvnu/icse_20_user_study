private void animateDismiss(final View view,final boolean byUser){
  RxAnimationTool.popout(view,mAnimationDuration,new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      super.onAnimationEnd(animation);
      if (mListener != null) {
        mListener.onTipDismissed(view,(Integer)view.getTag(),byUser);
      }
    }
  }
).start();
}
