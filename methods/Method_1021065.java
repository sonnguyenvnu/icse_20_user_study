public static void main(final String[] args) throws Exception {
  final Aruha771MigrationHelper helper=new Aruha771MigrationHelper(new HashGenerator(),new JsonConfig().jacksonObjectMapper());
  helper.fillSubscriptionsHashes();
}
