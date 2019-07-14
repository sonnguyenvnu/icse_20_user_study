private void count(Serializable s){
  if (_counts.containsKey(s)) {
    _counts.put(s,_counts.get(s) + 1);
  }
 else {
    _counts.put(s,1);
  }
}
