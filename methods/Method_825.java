public static long fnv1a_64(String key){
  long hashCode=0xcbf29ce484222325L;
  for (int i=0; i < key.length(); ++i) {
    char ch=key.charAt(i);
    hashCode^=ch;
    hashCode*=0x100000001b3L;
  }
  return hashCode;
}
