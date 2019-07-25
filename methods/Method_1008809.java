public void receive(String msg){
  _log.info("do notify task, msg={}",msg);
  JSONObject msgObj=JSON.parseObject(msg);
  String respUrl=msgObj.getString("url");
  String orderId=msgObj.getString("orderId");
  int count=msgObj.getInteger("count");
  if (StringUtils.isEmpty(respUrl)) {
    _log.warn("notify url is empty. respUrl={}",respUrl);
    return;
  }
  try {
    String notifyResult="";
    _log.info("==>MQ????????[orderId?{}][count?{}][time?{}]",orderId,count,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    try {
      URI uri=new URI(respUrl);
      notifyResult=restTemplate.postForObject(uri,null,String.class);
    }
 catch (    Exception e) {
      _log.error(e,"????????");
    }
    _log.info("<==MQ????????[orderId?{}][count?{}][time?{}]",orderId,count,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    _log.info("notify response , OrderID={}",orderId);
    if (notifyResult.trim().equalsIgnoreCase("success")) {
      try {
        int result=super.baseUpdateStatus4Complete(orderId);
        _log.info("??payOrderId={},?????????->{}",orderId,result == 1 ? "??" : "??");
      }
 catch (      Exception e) {
        _log.error(e,"?????????????");
      }
      try {
        int result=super.baseUpdateNotify(orderId,(byte)1);
        _log.info("??payOrderId={},????????->{}",orderId,result == 1 ? "??" : "??");
      }
 catch (      Exception e) {
        _log.error(e,"????????");
      }
      return;
    }
 else {
      int cnt=count + 1;
      _log.info("notify count={}",cnt);
      try {
        int result=super.baseUpdateNotify(orderId,(byte)cnt);
        _log.info("??payOrderId={},????????->{}",orderId,result == 1 ? "??" : "??");
      }
 catch (      Exception e) {
        _log.error(e,"????????");
      }
      if (cnt > 5) {
        _log.info("notify count>5 stop. url={}",respUrl);
        return;
      }
      msgObj.put("count",cnt);
      this.send(msgObj.toJSONString(),cnt * 60 * 1000);
    }
    _log.warn("notify failed. url:{}, response body:{}",respUrl,notifyResult.toString());
  }
 catch (  Exception e) {
    _log.info("<==MQ????????[orderId?{}][count?{}][time?{}]",orderId,count,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    _log.error(e,"notify exception. url:%s",respUrl);
  }
}
