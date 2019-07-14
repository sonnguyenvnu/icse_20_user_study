public float getRatingBarRating(){
  return (float)Math.round(value / max * 10) / 2;
}
