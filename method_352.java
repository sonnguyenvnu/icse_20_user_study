static final <K>long _XXXXX_(K key){
  long hash=key.hashCode() * HashMixer;
  hash^=hash >>> R;
  hash*=HashMixer;
  return hash;
}