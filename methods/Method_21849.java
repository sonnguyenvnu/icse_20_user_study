private void doChange(final long now,final CalendarDay currentMonth,boolean animate){
  title.animate().cancel();
  doTranslation(title,0);
  title.setAlpha(1);
  lastAnimTime=now;
  final CharSequence newTitle=titleFormatter.format(currentMonth);
  if (!animate) {
    title.setText(newTitle);
  }
 else {
    final int translation=translate * (previousMonth.isBefore(currentMonth) ? 1 : -1);
    final ViewPropertyAnimator viewPropertyAnimator=title.animate();
    if (orientation == MaterialCalendarView.HORIZONTAL) {
      viewPropertyAnimator.translationX(translation * -1);
    }
 else {
      viewPropertyAnimator.translationY(translation * -1);
    }
    viewPropertyAnimator.alpha(0).setDuration(animDuration).setInterpolator(interpolator).setListener(new AnimatorListener(){
      @Override public void onAnimationCancel(      Animator animator){
        doTranslation(title,0);
        title.setAlpha(1);
      }
      @Override public void onAnimationEnd(      Animator animator){
        title.setText(newTitle);
        doTranslation(title,translation);
        final ViewPropertyAnimator viewPropertyAnimator=title.animate();
        if (orientation == MaterialCalendarView.HORIZONTAL) {
          viewPropertyAnimator.translationX(0);
        }
 else {
          viewPropertyAnimator.translationY(0);
        }
        viewPropertyAnimator.alpha(1).setDuration(animDuration).setInterpolator(interpolator).setListener(new AnimatorListener()).start();
      }
    }
).start();
  }
  previousMonth=currentMonth;
}
