public IndexRequestBuilder index(String index,String type){
  return client.getClient().prepareIndex(index,type);
}
