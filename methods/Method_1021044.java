public static HttpResult search(HPApplication hPApplication,Context context,String keyword,String page,String pagesize){
  HttpResult httpResult=new HttpResult();
  if (!NetUtil.isNetworkAvailable(context)) {
    httpResult.setStatus(HttpResult.STATUS_NONET);
    httpResult.setErrorMsg("???????");
    return httpResult;
  }
  if (hPApplication.isWifi()) {
    if (!NetUtil.isWifi(context)) {
      httpResult.setStatus(HttpResult.STATUS_NOWIFI);
      httpResult.setErrorMsg("??????wifi");
      return httpResult;
    }
  }
  try {
    Map<String,Object> returnResult=new HashMap<String,Object>();
    String url="http://mobilecdn.kugou.com/api/v3/search/song";
    Map<String,Object> params=new HashMap<String,Object>();
    params.put("format","json");
    params.put("keyword",keyword);
    params.put("page",page);
    params.put("pagesize",pagesize);
    String result=HttpClientUtils.httpGetRequest(url,params);
    if (result != null) {
      JSONObject jsonNode=new JSONObject(result);
      int status=jsonNode.getInt("status");
      if (status == 1) {
        httpResult.setStatus(HttpResult.STATUS_SUCCESS);
        JSONObject dataJsonNode=jsonNode.getJSONObject("data");
        returnResult.put("total",dataJsonNode.getInt("total"));
        JSONArray infoJsonNode=dataJsonNode.getJSONArray("info");
        List<AudioInfo> lists=new ArrayList<AudioInfo>();
        for (int i=0; i < infoJsonNode.length(); i++) {
          JSONObject infoDataNode=infoJsonNode.getJSONObject(i);
          AudioInfo audioInfo=new AudioInfo();
          audioInfo.setDuration(infoDataNode.getInt("duration") * 1000);
          audioInfo.setDurationText(MediaUtil.parseTimeToString((int)audioInfo.getDuration()));
          audioInfo.setType(AudioInfo.NET);
          audioInfo.setStatus(AudioInfo.INIT);
          audioInfo.setFileExt(infoDataNode.getString("extname"));
          audioInfo.setFileSize(infoDataNode.getLong("filesize"));
          audioInfo.setFileSizeText(MediaUtil.getFileSize(audioInfo.getFileSize()));
          audioInfo.setHash(infoDataNode.getString("hash").toLowerCase());
          String singerName=infoDataNode.getString("singername");
          audioInfo.setSingerName(singerName.equals("") ? "??" : singerName);
          audioInfo.setSongName(infoDataNode.getString("songname"));
          SongInfoResult songInfoResult=SongInfoHttpUtil.songInfo(context,audioInfo.getHash());
          if (songInfoResult != null) {
            audioInfo.setDownloadUrl(songInfoResult.getUrl());
          }
          lists.add(audioInfo);
        }
        returnResult.put("rows",lists);
        httpResult.setResult(returnResult);
      }
 else {
        httpResult.setStatus(HttpResult.STATUS_ERROR);
        httpResult.setErrorMsg("????!");
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
    httpResult.setStatus(HttpResult.STATUS_ERROR);
    httpResult.setErrorMsg(e.getMessage());
  }
  return httpResult;
}
