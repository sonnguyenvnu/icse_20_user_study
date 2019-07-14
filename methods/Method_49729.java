private static double clipLatitude(double latitude){
  return Math.min(Math.max(latitude,-LATITUDE_MAX),LATITUDE_MAX);
}
