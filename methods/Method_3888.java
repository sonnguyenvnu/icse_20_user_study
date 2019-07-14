protected int calculateTimeForDeceleration(int dx){
  return (int)Math.ceil(calculateTimeForScrolling(dx) / .3356);
}
