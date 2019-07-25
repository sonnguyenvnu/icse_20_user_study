@Override protected void sql(@Nonnull final KyloCatalogClient<DataFrame> client,@Nonnull final String query){
  ((KyloCatalogClientV1)client).getSQLContext().sql(query);
}
