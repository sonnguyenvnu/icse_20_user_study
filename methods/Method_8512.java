@Keep @SuppressLint("NewApi") protected void setRevealRadius(float radius){
  revealRadius=radius;
  if (Build.VERSION.SDK_INT <= 19) {
    listView.invalidate();
  }
  if (!isDismissed()) {
    for (int a=0; a < innerAnimators.size(); a++) {
      InnerAnimator innerAnimator=innerAnimators.get(a);
      if (innerAnimator.startRadius > radius) {
        continue;
      }
      innerAnimator.animatorSet.start();
      innerAnimators.remove(a);
      a--;
    }
  }
}
