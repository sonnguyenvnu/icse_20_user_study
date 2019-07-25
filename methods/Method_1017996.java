/** 
 * Creates an  {@link Accumulable} shared variable with a name for display in the Spark UI.
 */
@Nonnull static <R,P1>Accumulable<R,P1> accumulable(@Nonnull final R initialValue,@Nonnull final String name,@Nonnull final AccumulableParam<R,P1> param,@Nonnull final KyloCatalogClient<Dataset<Row>> client){
  return ((KyloCatalogClientV2)client).getSparkSession().sparkContext().accumulable(initialValue,name,param);
}
