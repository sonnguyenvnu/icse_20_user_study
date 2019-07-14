public int newUTF8(final String value){
  key.set(1,value,null,null);
  Item result=get(key);
  if (result == null) {
    pool.putByte(1).putUTF8(value);
    result=new Item(index++,key);
    put(result);
  }
  return result.index;
}
