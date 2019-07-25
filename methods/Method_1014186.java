private double SINALT(double moonJd,int hour,double lambda,double cphi,double sphi){
  double jdo=moonJd + hour / 24.0;
  double t=(jdo - 51544.5) / 36525.0;
  double decra[]=calcMoon(t);
  double tau=15.0 * (LMST(jdo,lambda) - decra[1]);
  return sphi * SN(decra[0]) + cphi * CS(decra[0]) * CS(tau);
}
