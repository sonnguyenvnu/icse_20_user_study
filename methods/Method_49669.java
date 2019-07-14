public static OpenLocationCode createOpenLocationCode(double latitude,double longitude){
  return new OpenLocationCode(latitude,longitude,CODE_LENGTH_TO_GENERATE);
}
