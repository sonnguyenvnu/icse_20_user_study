@PreBuild void preBuild() throws ACRAConfigurationException {
  configurations=new ArrayList<>();
  final List<ConfigurationBuilder> builders=configurationBuilders();
  if (ACRA.DEV_LOGGING)   ACRA.log.d(LOG_TAG,"Found ConfigurationBuilders : " + builders);
  for (  ConfigurationBuilder builder : builders) {
    configurations.add(builder.build());
  }
}
