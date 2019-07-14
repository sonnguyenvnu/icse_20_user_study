public static void ScaleUpDowm(View view){
  ScaleAnimation animation=new ScaleAnimation(1.0f,1.0f,0.0f,1.0f);
  animation.setRepeatCount(-1);
  animation.setRepeatMode(Animation.RESTART);
  animation.setInterpolator(new LinearInterpolator());
  animation.setDuration(1200);
  view.startAnimation(animation);
}
