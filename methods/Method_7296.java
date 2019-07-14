private static void sun_RA_decAtDay(double d,double[] RA,double[] dec,double[] r){
  double[] lon=new double[1];
  double obl_ecl;
  double xs, ys;
  double xe, ye, ze;
  sunposAtDay(d,lon,r);
  xs=r[0] * cosd(lon[0]);
  ys=r[0] * sind(lon[0]);
  obl_ecl=23.4393 - 3.563E-7 * d;
  xe=xs;
  ye=ys * cosd(obl_ecl);
  ze=ys * sind(obl_ecl);
  RA[0]=atan2d(ye,xe);
  dec[0]=atan2d(ze,Math.sqrt(xe * xe + ye * ye));
}
