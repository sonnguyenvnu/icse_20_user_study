public static Item create(String param[]){
  if (param.length % 2 == 0)   return null;
  Item item=new Item(param[0]);
  int natureCount=(param.length - 1) / 2;
  for (int i=0; i < natureCount; ++i) {
    item.labelMap.put(param[1 + 2 * i],Integer.parseInt(param[2 + 2 * i]));
  }
  return item;
}
