@UiThread public static void dismissDialog(@NonNull DialogFragment dialogFragment,int duration,AnimatorListenerAdapter listenerAdapter){
  Dialog dialog=dialogFragment.getDialog();
  if (dialog != null) {
    if (dialog.getWindow() != null) {
      View view=dialog.getWindow().getDecorView();
      if (view != null) {
        int centerX=view.getWidth() / 2;
        int centerY=view.getHeight() / 2;
        float radius=(float)Math.sqrt(view.getWidth() * view.getWidth() / 4 + view.getHeight() * view.getHeight() / 4);
        view.post(() -> {
          if (ViewCompat.isAttachedToWindow(view)) {
            Animator animator=ViewAnimationUtils.createCircularReveal(view,centerX,centerY,radius,0);
            animator.setDuration(duration);
            animator.addListener(listenerAdapter);
            animator.start();
          }
 else {
            listenerAdapter.onAnimationEnd(null);
          }
        }
);
      }
    }
  }
 else {
    listenerAdapter.onAnimationEnd(null);
  }
}
