/** 
 * ???????
 * @param city
 * @return
 */
private String parseName(String city){
  if (city.contains("?")) {
    String subStr[]=city.split("?");
    city=subStr[0];
  }
 else   if (city.contains("?")) {
    String subStr[]=city.split("?");
    city=subStr[0];
  }
  return city;
}
