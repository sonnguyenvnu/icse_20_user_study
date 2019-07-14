/** 
 * ??????
 * @param path
 * @return
 */
public static List<Item> loadAsItemList(String path){
  List<Item> itemList=new LinkedList<Item>();
  try {
    BufferedReader br=new BufferedReader(new InputStreamReader(IOAdapter == null ? new FileInputStream(path) : IOAdapter.open(path),"UTF-8"));
    String line;
    while ((line=br.readLine()) != null) {
      Item item=Item.create(line);
      if (item == null) {
        logger.warning("???" + line + "???Item??");
        return null;
      }
      itemList.add(item);
    }
  }
 catch (  Exception e) {
    logger.warning("????" + path + "????" + e);
    return null;
  }
  return itemList;
}
