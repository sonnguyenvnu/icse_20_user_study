public static SimpleItem create(String param[]){
  if (param.length % 2 == 1)   return null;
  SimpleItem item=new SimpleItem();
  int natureCount=(param.length) / 2;
  for (int i=0; i < natureCount; ++i) {
    item.labelMap.put(param[2 * i],Integer.parseInt(param[1 + 2 * i]));
  }
  return item;
}
