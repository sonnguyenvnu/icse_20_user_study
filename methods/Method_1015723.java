public double saturation(){
  int space=spaceUsed();
  return space == 0 ? 0.0 : space / (double)capacity();
}
