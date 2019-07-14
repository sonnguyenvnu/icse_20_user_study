public GraphDatabaseConfiguration build(ReadConfiguration localConfig){
  Preconditions.checkNotNull(localConfig);
  BasicConfiguration localBasicConfiguration=new BasicConfiguration(ROOT_NS,localConfig,BasicConfiguration.Restriction.NONE);
  ModifiableConfiguration overwrite=new ModifiableConfiguration(ROOT_NS,new CommonsConfiguration(),BasicConfiguration.Restriction.NONE);
  final KeyColumnValueStoreManager storeManager=Backend.getStorageManager(localBasicConfiguration);
  final StoreFeatures storeFeatures=storeManager.getFeatures();
  final ReadConfiguration globalConfig=new ReadConfigurationBuilder().buildGlobalConfiguration(localConfig,localBasicConfiguration,overwrite,storeManager,new ModifiableConfigurationBuilder(),new KCVSConfigurationBuilder());
  ModifiableConfiguration localConfiguration=new ModifiableConfiguration(ROOT_NS,new CommonsConfiguration(),BasicConfiguration.Restriction.LOCAL);
  localConfiguration.setAll(getLocalSubset(localBasicConfiguration.getAll()));
  Configuration combinedConfig=new MixedConfiguration(ROOT_NS,globalConfig,localConfig);
  String uniqueGraphId=UniqueInstanceIdRetriever.getInstance().getOrGenerateUniqueInstanceId(combinedConfig);
  overwrite.set(UNIQUE_INSTANCE_ID,uniqueGraphId);
  checkAndOverwriteTransactionLogConfiguration(combinedConfig,overwrite,storeFeatures);
  checkAndOverwriteSystemManagementLogConfiguration(combinedConfig,overwrite);
  MergedConfiguration configuration=new MergedConfiguration(overwrite,combinedConfig);
  return new GraphDatabaseConfiguration(localConfig,localConfiguration,uniqueGraphId,configuration);
}
