/** 
 * Returns whether the bounding box specified by the Open Location Code contains provided point.
 * @param latitude Degrees.
 * @param longitude Degrees.
 * @return True if the coordinates are contained by the code.
 */
public boolean contains(double latitude,double longitude){
  CodeArea codeArea=decode();
  return codeArea.getSouthLatitude() <= latitude && latitude < codeArea.getNorthLatitude() && codeArea.getWestLongitude() <= longitude && longitude < codeArea.getEastLongitude();
}
