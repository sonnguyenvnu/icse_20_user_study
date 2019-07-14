/** 
 * Decodes  {@link OpenLocationCode} object into {@link CodeArea} object encapsulatinglatitude/longitude bounding box.
 * @return A CodeArea object.
 */
public CodeArea decode(){
  if (!isFullCode(code)) {
    throw new IllegalStateException("Method decode() could only be called on valid full codes, code was " + code + ".");
  }
  String clean=code.replace(String.valueOf(SEPARATOR),"").replace(String.valueOf(PADDING_CHARACTER),"");
  long latVal=-LATITUDE_MAX * LAT_INTEGER_MULTIPLIER;
  long lngVal=-LONGITUDE_MAX * LNG_INTEGER_MULTIPLIER;
  long latPlaceVal=LAT_MSP_VALUE;
  long lngPlaceVal=LNG_MSP_VALUE;
  for (int i=0; i < Math.min(clean.length(),PAIR_CODE_LENGTH); i+=2) {
    latPlaceVal/=ENCODING_BASE;
    lngPlaceVal/=ENCODING_BASE;
    latVal+=CODE_ALPHABET.indexOf(clean.charAt(i)) * latPlaceVal;
    lngVal+=CODE_ALPHABET.indexOf(clean.charAt(i + 1)) * lngPlaceVal;
  }
  for (int i=PAIR_CODE_LENGTH; i < Math.min(clean.length(),MAX_DIGIT_COUNT); i++) {
    latPlaceVal/=GRID_ROWS;
    lngPlaceVal/=GRID_COLUMNS;
    int digit=CODE_ALPHABET.indexOf(clean.charAt(i));
    int row=digit / GRID_COLUMNS;
    int col=digit % GRID_COLUMNS;
    latVal+=row * latPlaceVal;
    lngVal+=col * lngPlaceVal;
  }
  double latitudeLo=(double)latVal / LAT_INTEGER_MULTIPLIER;
  double longitudeLo=(double)lngVal / LNG_INTEGER_MULTIPLIER;
  double latitudeHi=(double)(latVal + latPlaceVal) / LAT_INTEGER_MULTIPLIER;
  double longitudeHi=(double)(lngVal + lngPlaceVal) / LNG_INTEGER_MULTIPLIER;
  return new CodeArea(latitudeLo,longitudeLo,latitudeHi,longitudeHi,Math.min(clean.length(),MAX_DIGIT_COUNT));
}
