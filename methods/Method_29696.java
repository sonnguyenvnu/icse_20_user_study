public void performPinch(float dsx,float dsy){
  float ds=(float)Math.sqrt((dsx * dsx) + (dsy * dsy));
  onPinch(ds,dsx,dsy);
}
