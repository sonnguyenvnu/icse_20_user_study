private int hashString(String key){
  int hash=SEED * random;
  for (int i=0; i < key.length(); i++)   hash=(hash * KEY) ^ key.charAt(i);
  return (hash ^ (hash >> 1)) & M_MASK;
}
