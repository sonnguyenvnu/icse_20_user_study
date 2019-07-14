private MongoClientFactoryBean buildMongoClientFactoryBean(final HmilyMongoConfig hmilyMongoConfig){
  MongoClientFactoryBean clientFactoryBean=new MongoClientFactoryBean();
  MongoCredential credential=MongoCredential.createScramSha1Credential(hmilyMongoConfig.getMongoUserName(),hmilyMongoConfig.getMongoDbName(),hmilyMongoConfig.getMongoUserPwd().toCharArray());
  clientFactoryBean.setCredentials(new MongoCredential[]{credential});
  List<String> urls=Splitter.on(",").trimResults().splitToList(hmilyMongoConfig.getMongoDbUrl());
  ServerAddress[] sds=new ServerAddress[urls.size()];
  for (int i=0; i < sds.length; i++) {
    List<String> adds=Splitter.on(":").trimResults().splitToList(urls.get(i));
    InetSocketAddress address=new InetSocketAddress(adds.get(0),Integer.parseInt(adds.get(1)));
    sds[i]=new ServerAddress(address);
  }
  clientFactoryBean.setReplicaSetSeeds(sds);
  return clientFactoryBean;
}
