protected void lightSpot(int num,float angle,float exponent){
  lightSpotParameters[2 * num + 0]=Math.max(0,PApplet.cos(angle));
  lightSpotParameters[2 * num + 1]=exponent;
}
