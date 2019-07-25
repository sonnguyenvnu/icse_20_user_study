/** 
 * Converts an array of String ids to and Item[].
 * @param ids the ids to convert
 * @return the new items array
 * @deprecated construct the items array externally and use it in the constructor / setter
 */
@Deprecated public static Item[] ids(String... ids){
  Item[] items=new Item[ids.length];
  for (int i=0; i < items.length; i++) {
    items[i]=new Item(null,null,ids[i]);
  }
  return items;
}
