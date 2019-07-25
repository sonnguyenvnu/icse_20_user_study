private static boolean keyeq(Object K,Object key,int[] hashes,int hash,int fullhash){
  return K == key || ((hashes[hash] == 0 || hashes[hash] == fullhash) && K != TOMBSTONE && key.equals(K));
}
