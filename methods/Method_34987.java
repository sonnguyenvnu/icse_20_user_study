@Nullable @SuppressWarnings("unchecked") <K1 extends K,V1 extends V>Weigher<K1,V1> getWeigher(){
  return (Weigher<K1,V1>)MoreObjects.firstNonNull(weigher,OneWeigher.INSTANCE);
}
