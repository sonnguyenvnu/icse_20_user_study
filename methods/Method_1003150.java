/** 
 * Remove the key and value (or child) at the given index.
 * @param index the index
 */
public void remove(int index){
  int keyCount=getKeyCount();
  DataType keyType=map.getKeyType();
  if (index == keyCount) {
    --index;
  }
  if (isPersistent()) {
    Object old=getKey(index);
    addMemory(-MEMORY_POINTER - keyType.getMemory(old));
  }
  Object[] newKeys=createKeyStorage(keyCount - 1);
  DataUtils.copyExcept(keys,newKeys,keyCount,index);
  keys=newKeys;
}
