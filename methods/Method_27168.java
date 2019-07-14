@UiThread public static void revealDialog(@NonNull Dialog dialog,int animDuration){
  if (dialog.getWindow() != null) {
    View view=dialog.getWindow().getDecorView();
    if (view != null) {
      view.post(() -> {
        if (ViewCompat.isAttachedToWindow(view)) {
          int centerX=view.getWidth() / 2;
          int centerY=view.getHeight() / 2;
          Animator animator=ViewAnimationUtils.createCircularReveal(view,centerX,centerY,20,view.getHeight());
          animator.setDuration(animDuration);
          animator.start();
        }
      }
);
    }
  }
}
