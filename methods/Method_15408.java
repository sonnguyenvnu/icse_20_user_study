/** 
 * index???arr?????
 * @param index
 * @param array
 * @return
 */
public static boolean isIndexInRange(Integer index,Object[] array){
  return index != null && index >= 0 && index < count(array);
}
