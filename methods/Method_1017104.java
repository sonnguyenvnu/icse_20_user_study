@Override public Exposed setup(final ConsumerSchema.Depends depends){
  return DaggerSpotify100_C.builder().depends(depends).build();
}
