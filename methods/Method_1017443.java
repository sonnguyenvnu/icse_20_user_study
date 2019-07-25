/** 
 * ????;????????
 * @param latitude
 * @param longitude
 * @return
 */
public SemQuery location(float latitude,float longitude){
  jsonObj.put("latitude",latitude);
  jsonObj.put("longitude",longitude);
  return this;
}
