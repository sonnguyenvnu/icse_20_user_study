private boolean isExpired(final KeySliceQuery query){
  Long until=expiredKeys.get(query.getKey());
  if (until == null)   return false;
  if (isBeyondExpirationTime(until)) {
    expiredKeys.remove(query.getKey(),until);
    return false;
  }
  penaltyCountdown.countDown();
  return true;
}
