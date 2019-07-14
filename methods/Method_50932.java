@SuppressWarnings("PMD.JumbledIncrementer") private Map<TokenEntry,Object> hash(){
  Map<TokenEntry,Object> markGroups=new HashMap<>(tokens.size());
  for (int i=code.size() - 1; i >= 0; i--) {
    TokenEntry token=code.get(i);
    if (token != TokenEntry.EOF) {
      int last=tokenAt(min,token).getIdentifier();
      lastHash=MOD * lastHash + token.getIdentifier() - lastMod * last;
      token.setHashCode(lastHash);
      Object o=markGroups.get(token);
      if (o == null) {
        markGroups.put(token,token);
      }
 else       if (o instanceof TokenEntry) {
        List<TokenEntry> l=new ArrayList<>();
        l.add((TokenEntry)o);
        l.add(token);
        markGroups.put(token,l);
      }
 else {
        @SuppressWarnings("unchecked") List<TokenEntry> l=(List<TokenEntry>)o;
        l.add(token);
      }
    }
 else {
      lastHash=0;
      for (int end=Math.max(0,i - min + 1); i > end; i--) {
        token=code.get(i - 1);
        lastHash=MOD * lastHash + token.getIdentifier();
        if (token == TokenEntry.EOF) {
          break;
        }
      }
    }
  }
  return markGroups;
}
