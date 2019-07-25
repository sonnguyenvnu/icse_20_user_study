public TrieKey shift(int hexCnt){
  return new TrieKey(this.key,off + hexCnt,terminal);
}
