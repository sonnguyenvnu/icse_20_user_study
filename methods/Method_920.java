/** 
 * Find subList original variable
 * @param image
 * @return
 */
private String getBeforeSubListVal(String image){
  return image == null ? null : image.substring(0,image.indexOf("."));
}
