protected int calculateTimeForScrolling(int dx){
  return (int)Math.ceil(Math.abs(dx) * MILLISECONDS_PER_PX);
}
