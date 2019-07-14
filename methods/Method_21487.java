private void sendDefaultResponse(SearchHits hits,RestChannel channel){
  try {
    String json=ElasticUtils.hitsAsStringResult(hits,new MetaSearchResult());
    BytesRestResponse bytesRestResponse=new BytesRestResponse(RestStatus.OK,json);
    channel.sendResponse(bytesRestResponse);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
