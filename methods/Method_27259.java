/** 
 * Method to replace String.join, since it was only introduced in java8
 * @param array the array to be concatenated
 * @return concatenated String
 */
private String stringJoin(String[] array,int count){
  String joined="";
  for (int i=0; i < count; i++)   joined+=array[i];
  return joined;
}
