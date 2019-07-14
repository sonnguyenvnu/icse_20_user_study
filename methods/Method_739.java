/** 
 * Like addEntry except that this version is used when creating entries as part of Map construction or "pseudo-construction" (cloning, deserialization). This version needn't worry about resizing the table. Subclass overrides this to alter the behavior of SafelyHashMap(Map), clone, and readObject.
 */
void createEntry(int hash,K key,V value,int bucketIndex){
  Entry<K,V> e=table[bucketIndex];
  table[bucketIndex]=new Entry<K,V>(hash,key,value,e);
  size++;
}
