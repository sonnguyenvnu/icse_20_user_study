public void setAnimTime(long animDuration){
  setFactory(this);
  Animation in=new TranslateAnimation(0,0,animDuration,0);
  in.setDuration(animDuration);
  in.setInterpolator(new AccelerateInterpolator());
  Animation out=new TranslateAnimation(0,0,0,-animDuration);
  out.setDuration(animDuration);
  out.setInterpolator(new AccelerateInterpolator());
  setInAnimation(in);
  setOutAnimation(out);
}
