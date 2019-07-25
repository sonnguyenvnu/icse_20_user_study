@RequestMapping("/list") @ResponseBody public String list(@ModelAttribute MchInfo mchInfo,Integer pageIndex,Integer pageSize){
  PageModel pageModel=new PageModel();
  int count=mchInfoService.count(mchInfo);
  if (count <= 0)   return JSON.toJSONString(pageModel);
  List<MchInfo> mchInfoList=mchInfoService.getMchInfoList((pageIndex - 1) * pageSize,pageSize,mchInfo);
  if (!CollectionUtils.isEmpty(mchInfoList)) {
    JSONArray array=new JSONArray();
    for (    MchInfo mi : mchInfoList) {
      JSONObject object=(JSONObject)JSONObject.toJSON(mi);
      object.put("createTime",DateUtil.date2Str(mi.getCreateTime()));
      array.add(object);
    }
    pageModel.setList(array);
  }
  pageModel.setCount(count);
  pageModel.setMsg("ok");
  pageModel.setRel(true);
  return JSON.toJSONString(pageModel);
}
