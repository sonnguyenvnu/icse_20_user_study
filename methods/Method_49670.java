/** 
 * @param searchStr An OLC code (full or short) followed by an optional locality.
 * @param latitude  A latitude to use to complete the code if it was short and no locality wasprovided.
 * @param longitude A longitude to use to complete the code if it was short and no locality wasprovided. Only localities we can look up can be used.
 */
@Nullable public static OpenLocationCode getCodeForSearchString(String searchStr,double latitude,double longitude){
  try {
    String code=null;
    String locality=null;
    if (!searchStr.matches(".*\\s+.*")) {
      code=searchStr;
    }
 else {
      Matcher matcher=SPLITTER_PATTERN.matcher(searchStr);
      if (matcher.find()) {
        code=matcher.group(1);
        locality=matcher.group(2);
      }
    }
    if (code == null) {
      return null;
    }
    OpenLocationCode searchCode=new OpenLocationCode(code);
    if (searchCode.isFull()) {
      Log.i(TAG,"Code is full, we're done");
      return searchCode;
    }
    if (locality != null && !Locality.getLocalityCode(locality).isEmpty()) {
      OpenLocationCode localityCode=new OpenLocationCode(Locality.getLocalityCode(locality));
      Log.i(TAG,String.format("Got locality %s: locality code: %s",locality,localityCode.getCode()));
      OpenLocationCode.CodeArea localityArea=localityCode.decode();
      return searchCode.recover(localityArea.getCenterLatitude(),localityArea.getCenterLongitude());
    }
    Log.i(TAG,"Using passed location to complete code");
    return searchCode.recover(latitude,longitude);
  }
 catch (  IllegalArgumentException e) {
    return null;
  }
}
