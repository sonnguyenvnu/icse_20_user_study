@Nonnull @Override protected <R,P1>Accumulable<R,P1> accumulable(@Nonnull final R initialValue,@Nonnull final String name,@Nonnull final AccumulableParam<R,P1> param,@Nonnull final KyloCatalogClient<DataFrame> client){
  return DataSetProviderUtilV1.accumulable(initialValue,name,param,client);
}
