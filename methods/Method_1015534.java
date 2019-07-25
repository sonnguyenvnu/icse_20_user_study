protected static boolean same(final List<Digest> digests){
  if (digests == null)   return false;
  Digest first=digests.get(0);
  for (int i=1; i < digests.size(); i++) {
    Digest current=digests.get(i);
    if (!first.equals(current))     return false;
  }
  return true;
}
