/** 
 * @see Arrays#equals(byte[],byte[]) 
 */
public boolean equals(ByteArray array,ByteArray other){
  if (array == other) {
    return true;
  }
  if (array.size() != other.size()) {
    return false;
  }
  for (long i=0; i < array.size(); i++) {
    if (array.get(i) != other.get(i)) {
      return false;
    }
  }
  return true;
}
