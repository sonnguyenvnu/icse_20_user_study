@Override public float getDur(){
  float dur=super.getDur();
  if (dur == 0) {
    dur=getImplicitDuration();
  }
  return dur;
}
