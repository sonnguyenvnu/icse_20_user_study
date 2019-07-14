public static StaticBuffer getKey(final HashLength hashPrefixLen,StaticBuffer hasPrefixedKey){
  return hasPrefixedKey.subrange(hashPrefixLen.length(),hasPrefixedKey.length() - hashPrefixLen.length());
}
