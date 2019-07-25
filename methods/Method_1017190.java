/** 
 * Setup the ObjectMapper used to deserialize configuration files.
 * @return
 */
public static ObjectMapper config(){
  final ObjectMapper m=new ObjectMapper(new YAMLFactory());
  m.addMixIn(ClusterDiscoveryModule.class,TypeNameMixin.class);
  m.addMixIn(RpcProtocolModule.class,TypeNameMixin.class);
  m.addMixIn(ConsumerModule.Builder.class,TypeNameMixin.class);
  m.addMixIn(MetadataModule.class,TypeNameMixin.class);
  m.addMixIn(SuggestModule.class,TypeNameMixin.class);
  m.addMixIn(MetricModule.class,TypeNameMixin.class);
  m.addMixIn(MetricGeneratorModule.class,TypeNameMixin.class);
  m.addMixIn(MetadataGenerator.class,TypeNameMixin.class);
  m.addMixIn(AnalyticsModule.Builder.class,TypeNameMixin.class);
  m.addMixIn(StatisticsModule.class,TypeNameMixin.class);
  m.addMixIn(QueryLoggingModule.class,TypeNameMixin.class);
  m.addMixIn(CacheModule.Builder.class,TypeNameMixin.class);
  m.addMixIn(RequestCondition.class,TypeNameMixin.class);
  m.addMixIn(ConditionalFeatures.class,TypeNameMixin.class);
  m.registerModule(commonSerializers());
  m.registerModule(new Jdk8Module());
  m.registerModule(new KotlinModule());
  return m;
}
