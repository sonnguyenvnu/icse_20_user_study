private static boolean inside(double val,double bound0,double bound1){
  if (bound0 < bound1)   return val >= bound0 && val <= bound1;
 else   return val >= bound1 && val <= bound0;
}
