/** 
 * ????????????????
 * @param itemList
 * @return ??????
 */
public static List<Item> normalizeFrequency(List<Item> itemList){
  for (  Item item : itemList) {
    ArrayList<Map.Entry<String,Integer>> entryArray=new ArrayList<Map.Entry<String,Integer>>(item.labelMap.entrySet());
    Collections.sort(entryArray,new Comparator<Map.Entry<String,Integer>>(){
      @Override public int compare(      Map.Entry<String,Integer> o1,      Map.Entry<String,Integer> o2){
        return o1.getValue().compareTo(o2.getValue());
      }
    }
);
    int index=1;
    for (    Map.Entry<String,Integer> pair : entryArray) {
      item.labelMap.put(pair.getKey(),index);
      ++index;
    }
  }
  return itemList;
}
