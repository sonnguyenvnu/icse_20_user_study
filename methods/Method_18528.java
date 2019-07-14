@Override public void positionType(@Nullable YogaPositionType positionType){
  mPrivateFlags|=PFLAG_POSITION_TYPE_IS_SET;
  getOrCreateObjectProps().append(INDEX_PositionType,positionType);
}
