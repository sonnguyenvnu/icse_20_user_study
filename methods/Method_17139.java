/** 
 * Returns a  {@link Caffeine} builder configured according to this specification.
 * @return a builder configured to the specification
 */
Caffeine<Object,Object> toBuilder(){
  Caffeine<Object,Object> builder=Caffeine.newBuilder();
  if (initialCapacity != UNSET_INT) {
    builder.initialCapacity(initialCapacity);
  }
  if (maximumSize != UNSET_INT) {
    builder.maximumSize(maximumSize);
  }
  if (maximumWeight != UNSET_INT) {
    builder.maximumWeight(maximumWeight);
  }
  if (keyStrength != null) {
    requireState(keyStrength == Strength.WEAK);
    builder.weakKeys();
  }
  if (valueStrength != null) {
    if (valueStrength == Strength.WEAK) {
      builder.weakValues();
    }
 else     if (valueStrength == Strength.SOFT) {
      builder.softValues();
    }
 else {
      throw new IllegalStateException();
    }
  }
  if (expireAfterAccessTimeUnit != null) {
    builder.expireAfterAccess(expireAfterAccessDuration,expireAfterAccessTimeUnit);
  }
  if (expireAfterWriteTimeUnit != null) {
    builder.expireAfterWrite(expireAfterWriteDuration,expireAfterWriteTimeUnit);
  }
  if (refreshAfterWriteTimeUnit != null) {
    builder.refreshAfterWrite(refreshAfterWriteDuration,refreshAfterWriteTimeUnit);
  }
  if (recordStats) {
    builder.recordStats();
  }
  return builder;
}
