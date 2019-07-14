/** 
 * {@inheritDoc}
 */
@Override public <S>void register(@NonNull Class<S> type,@NonNull S service){
  Preconditions.checkArgument(type != null,"type is null");
  mServices.put(type,type.cast(service));
}
