private static double normalizeLongitude(double longitude){
  while (longitude < -LONGITUDE_MAX) {
    longitude=longitude + LONGITUDE_MAX * 2;
  }
  while (longitude >= LONGITUDE_MAX) {
    longitude=longitude - LONGITUDE_MAX * 2;
  }
  return longitude;
}
