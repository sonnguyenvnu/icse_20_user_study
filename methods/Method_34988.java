@Nullable LocalCache.Strength getValueStrength(){
  return MoreObjects.firstNonNull(valueStrength,LocalCache.Strength.STRONG);
}
