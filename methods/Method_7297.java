private static int sunRiseSetHelperForYear(int year,int month,int day,double lon,double lat,double altit,int upper_limb,double[] sun){
  double[] sRA=new double[1];
  double[] sdec=new double[1];
  double[] sr=new double[1];
  double d, sradius, t, tsouth, sidtime;
  int rc=0;
  d=days_since_2000_Jan_0(year,month,day) + 0.5 - lon / 360.0;
  sidtime=revolution(GMST0(d) + 180.0 + lon);
  sun_RA_decAtDay(d,sRA,sdec,sr);
  tsouth=12.0 - rev180(sidtime - sRA[0]) / 15.0;
  sradius=0.2666 / sr[0];
  if (upper_limb != 0) {
    altit-=sradius;
  }
  double cost;
  cost=(sind(altit) - sind(lat) * sind(sdec[0])) / (cosd(lat) * cosd(sdec[0]));
  if (cost >= 1.0) {
    rc=-1;
    t=0.0;
  }
 else   if (cost <= -1.0) {
    rc=+1;
    t=12.0;
  }
 else {
    t=acosd(cost) / 15.0;
  }
  sun[0]=tsouth - t;
  sun[1]=tsouth + t;
  return rc;
}
