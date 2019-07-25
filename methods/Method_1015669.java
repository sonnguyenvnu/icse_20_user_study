public byte[] remove(byte[] key){
  if (keys == null || key == null)   return null;
  for (int i=0; i < keys.length; i++) {
    byte[] k=keys[i];
    if (k != null && Arrays.equals(k,key)) {
      byte[] old_val=values[i];
      keys[i]=values[i]=null;
      return old_val;
    }
  }
  return null;
}
