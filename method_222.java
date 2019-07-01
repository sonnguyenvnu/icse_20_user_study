@Override public HttpServiceResponse _XXXXX_(HttpServiceRequest request) throws Exception {
  HttpServiceResponse response=new HttpServiceResponse();
  if (HttpServer.Method.GET == request.getMethod()) {
    ClientConfiguration clientConf=new ClientConfiguration(conf);
    clientConf.setDiskWeightBasedPlacementEnabled(true);
    BookKeeper bk=new BookKeeper(clientConf);
    Map<BookieSocketAddress,BookieInfoReader.BookieInfo> map=bk.getBookieInfo();
    if (map.size() == 0) {
      bk.close();
      response.setCode(HttpServer.StatusCode.NOT_FOUND);
      response.setBody("Not found any Bookie info.");
      return response;
    }
    LinkedHashMap<String,String> output=Maps.newLinkedHashMapWithExpectedSize(map.size());
    Long totalFree=0L, total=0L;
    for (    Map.Entry<BookieSocketAddress,BookieInfoReader.BookieInfo> infoEntry : map.entrySet()) {
      BookieInfoReader.BookieInfo bInfo=infoEntry.getValue();
      output.put(infoEntry.getKey().toString(),": {Free: " + bInfo.getFreeDiskSpace() + getReadable(bInfo.getFreeDiskSpace())+ ", Total: "+ bInfo.getTotalDiskSpace()+ getReadable(bInfo.getTotalDiskSpace())+ "},");
      totalFree+=bInfo.getFreeDiskSpace();
      total+=bInfo.getTotalDiskSpace();
    }
    output.put("ClusterInfo: ","{Free: " + totalFree + getReadable(totalFree)+ ", Total: "+ total+ getReadable(total)+ "}");
    bk.close();
    String jsonResponse=JsonUtil.toJson(output);
    LOG.debug("output body:" + jsonResponse);
    response.setBody(jsonResponse);
    response.setCode(HttpServer.StatusCode.OK);
    return response;
  }
 else {
    response.setCode(HttpServer.StatusCode.NOT_FOUND);
    response.setBody("Not found method. Should be GET method");
    return response;
  }
}