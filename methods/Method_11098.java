@Override public float getInterpolation(float input){
  return RxEasingProvider.get(this.ease,input);
}
