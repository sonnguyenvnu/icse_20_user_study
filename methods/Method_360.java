public Item newClassItem(final String value){
  key2.set(7,value,null,null);
  Item result=get(key2);
  if (result == null) {
    pool.put12(7,newUTF8(value));
    result=new Item(index++,key2);
    put(result);
  }
  return result;
}
