protected int calcTotalCount(float radius,float size){
  return Math.max(1,(int)((1f - GAP_PERCENTAGE) * Math.PI / (Math.asin(size / radius)) + 0.5f));
}
