@ReadOperation public Map<String,Object> invoke(){
  Map<String,Object> result=new HashMap<>();
  Map<String,OSSClient> ossClientMap=applicationContext.getBeansOfType(OSSClient.class);
  int size=ossClientMap.size();
  List<Object> ossClientList=new ArrayList<>();
  ossClientMap.keySet().forEach(beanName -> {
    Map<String,Object> ossProperties=new HashMap<>();
    OSSClient client=ossClientMap.get(beanName);
    ossProperties.put("beanName",beanName);
    ossProperties.put("endpoint",client.getEndpoint().toString());
    ossProperties.put("clientConfiguration",client.getClientConfiguration());
    ossProperties.put("credentials",client.getCredentialsProvider().getCredentials());
    ossProperties.put("bucketList",client.listBuckets().stream().map(bucket -> bucket.getName()).toArray());
    ossClientList.add(ossProperties);
  }
);
  result.put("size",size);
  result.put("info",ossClientList);
  return result;
}
