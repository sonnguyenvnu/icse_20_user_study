/** 
 * Adds a single entry (key-value pair) into this ValuesTable at the specified index.
 * @param index entry index
 * @param key   entry key
 * @param value entry value
 */
public void put(int index,final K key,final V value){
  int existingKeyIndex=-1;
  if (containsKey(key)) {
    existingKeyIndex=indexOfKey(key);
  }
  int existingValueIndex=-1;
  if (containsValue(value)) {
    existingValueIndex=indexOfValue(value);
  }
  if (existingKeyIndex != -1 || existingValueIndex != -1) {
    if (existingKeyIndex > existingValueIndex) {
      if (existingKeyIndex != -1) {
        removeExistingEntry(existingKeyIndex);
      }
      if (existingValueIndex != -1) {
        removeExistingEntry(existingValueIndex);
      }
    }
 else {
      if (existingValueIndex != -1) {
        removeExistingEntry(existingValueIndex);
      }
      if (existingKeyIndex != -1) {
        removeExistingEntry(existingKeyIndex);
      }
    }
  }
  if (existingKeyIndex != -1 && existingKeyIndex < index && existingValueIndex != -1 && existingValueIndex < index) {
    index-=2;
  }
 else   if (existingKeyIndex != -1 && existingKeyIndex < index || existingValueIndex != -1 && existingValueIndex < index) {
    index--;
  }
  addEntry(index,key,value);
}
