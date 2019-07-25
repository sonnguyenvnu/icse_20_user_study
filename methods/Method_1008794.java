@RequestMapping(value="/save",method=RequestMethod.POST) @ResponseBody public String save(@RequestParam String params){
  JSONObject po=JSONObject.parseObject(params);
  MchInfo mchInfo=new MchInfo();
  String mchId=po.getString("mchId");
  mchInfo.setName(po.getString("name"));
  mchInfo.setType(po.getString("type"));
  mchInfo.setState((byte)("on".equalsIgnoreCase(po.getString("state")) ? 1 : 0));
  mchInfo.setReqKey(po.getString("reqKey"));
  mchInfo.setResKey(po.getString("resKey"));
  int result;
  if (StringUtils.isBlank(mchId)) {
    result=mchInfoService.addMchInfo(mchInfo);
  }
 else {
    mchInfo.setMchId(mchId);
    result=mchInfoService.updateMchInfo(mchInfo);
  }
  _log.info("??????,??:{}",result);
  return result + "";
}
