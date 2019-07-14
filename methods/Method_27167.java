@UiThread public static void startBeatsAnimation(@NonNull View view){
  view.clearAnimation();
  if (view.getAnimation() != null) {
    view.getAnimation().cancel();
  }
  List<ObjectAnimator> animators=getBeats(view);
  for (  ObjectAnimator anim : animators) {
    anim.setDuration(300).start();
    anim.setInterpolator(interpolator);
  }
}
