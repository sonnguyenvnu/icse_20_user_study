static public double calcTick(int scr,double range){
  int aprox_x_ticks=scr / 100;
  int type=1;
  double best=Math.log10(range / (aprox_x_ticks));
  double n=Math.log10(range / (aprox_x_ticks * 2));
  if (frac(n) < frac(best)) {
    best=n;
    type=2;
  }
  n=Math.log10(range / (aprox_x_ticks * 5));
  if (frac(n) < frac(best)) {
    best=n;
    type=5;
  }
  return type * Math.pow(10,Math.floor(best));
}
