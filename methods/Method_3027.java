/** 
 * ?dat????
 * @param byteArray
 * @return
 */
protected boolean loadDat(ByteArray byteArray){
  V[] valueArray=loadValueArray(byteArray);
  if (valueArray == null) {
    return false;
  }
  return trie.load(byteArray.getBytes(),byteArray.getOffset(),valueArray);
}
