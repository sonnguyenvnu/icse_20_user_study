/** 
 * Calculates page number that contains some item.
 */
public static int calcPageOfItem(final int itemIndex,final int pageSize){
  return itemIndex / pageSize + 1;
}
