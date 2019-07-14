boolean isBounded(){
  return (maximumSize != UNSET_INT) || (maximumWeight != UNSET_INT) || (expireAfterAccessNanos != UNSET_INT) || (expireAfterWriteNanos != UNSET_INT) || (expiry != null) || (keyStrength != null) || (valueStrength != null);
}
