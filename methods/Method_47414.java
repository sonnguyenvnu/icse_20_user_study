public static float clamp(float min,float max,float value){
  float minimum=Math.max(min,value);
  return Math.min(minimum,max);
}
