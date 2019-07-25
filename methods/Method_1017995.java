/** 
 * Gets the current Kylo Catalog client.
 */
@Nonnull private static KyloCatalogClient<Dataset<Row>> client(){
  return builder().build();
}
