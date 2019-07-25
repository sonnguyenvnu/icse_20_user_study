@Override public void hash(final Hasher hasher){
  hasher.putInt(MetricType.EVENT.ordinal());
  for (  final String k : KEY_ORDER.sortedCopy(payload.keySet())) {
    hasher.putString(k,Charsets.UTF_8).putString(payload.get(k),Charsets.UTF_8);
  }
}
