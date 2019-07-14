@Bean @ConditionalOnMissingBean public StatusUpdater statusUpdater(InstanceRepository instanceRepository,InstanceWebClient.Builder instanceWebClientBulder){
  return new StatusUpdater(instanceRepository,instanceWebClientBulder.build());
}
