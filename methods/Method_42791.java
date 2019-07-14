/** 
 * ??????
 * @param propertyName
 * @param property
 * @param isRequire
 * @param minLength
 * @param maxLength
 * @return
 */
public static String lengthValidate(String propertyName,String property,boolean isRequire,int minLength,int maxLength){
  int propertyLenght=property.length();
  if (isRequire && propertyLenght == 0) {
    return propertyName + "?????";
  }
 else   if (isRequire && minLength != 0 && propertyLenght < minLength) {
    return propertyName + "????" + minLength + "????";
  }
 else   if (maxLength != 0 && propertyLenght > maxLength) {
    return propertyName + "????" + maxLength + "????";
  }
 else {
    return "";
  }
}
