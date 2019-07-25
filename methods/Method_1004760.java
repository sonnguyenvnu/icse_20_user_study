public InputStream scroll(String scrollId){
  long start=network.transportStats().netTotalTime;
  try {
    BytesArray body;
    if (clusterInfo.getMajorVersion().onOrAfter(EsMajorVersion.V_2_X)) {
      body=new BytesArray("{\"scroll_id\":\"" + scrollId + "\"}");
    }
 else {
      body=new BytesArray(scrollId);
    }
    InputStream is=execute(POST,"_search/scroll?scroll=" + scrollKeepAlive.toString(),body).body();
    stats.scrollTotal++;
    return is;
  }
  finally {
    stats.scrollTotalTime+=network.transportStats().netTotalTime - start;
  }
}
