@SuppressWarnings("unchecked") @Nullable Expiry<K,V> getExpiry(boolean isAsync){
  return isAsync && (expiry != null) ? (Expiry<K,V>)new AsyncExpiry<>(expiry) : (Expiry<K,V>)expiry;
}
