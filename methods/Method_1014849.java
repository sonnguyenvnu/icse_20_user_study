@Override public MatchedElement match(String dataKey,WalkedPath walkedPath){
  if (getRawKey().equals(dataKey)) {
    return new MatchedElement(getRawKey());
  }
  return null;
}
