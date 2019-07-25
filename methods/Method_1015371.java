/** 
 * ??????
 * @param appName app??
 * @param pageSize ????
 * @param pageNum  ???
 * @param findType ????????
 * @return
 */
@GetMapping("/applications") public ResultData search(@RequestParam(value="appName",required=false) String appName,@RequestParam(value="pageNum",required=false,defaultValue="1") int pageNum,@RequestParam(value="pageSize",required=false,defaultValue="8") int pageSize,@RequestParam(value="findType",required=false) String findType){
  String userName=this.getUserNameByToken();
  Flux<MossApplication> applicationFlux=applicationFlux(appName);
  List<MossApplication> applications=applicationFlux.collectList().block();
  List<MossApplication> myCollectList=new ArrayList<MossApplication>();
  if (Constants.APP_FIND_TYPE_COLLECT.equals(findType)) {
    List<UserAppModel> userAppModels=userAppService.findCollectedByMailNickName(userName,"");
    if (null != userAppModels && userAppModels.size() > 0) {
      for (      UserAppModel userAppModel : userAppModels) {
        for (        MossApplication mossApplication : applications) {
          if (mossApplication.getName().equalsIgnoreCase(userAppModel.getAppId())) {
            mossApplication.setAttachType(Constants.ATTACH_TYPE_COLLECT);
            myCollectList.add(mossApplication);
          }
        }
      }
    }
    applications=myCollectList;
  }
  if (Constants.APP_FIND_TYPE_MY.equals(findType)) {
    List<MossApplication> myAppList=new ArrayList<MossApplication>();
    if (StringUtils.isNotEmpty(userName)) {
      List<AppModel> ownerAppList=appService.findAllByParamter("","",userName);
      if (null != ownerAppList && ownerAppList.size() > 0) {
        for (        AppModel appModel : ownerAppList) {
          for (          MossApplication mossApplication : applications) {
            if (mossApplication.getName().equalsIgnoreCase(appModel.getAppId())) {
              mossApplication.setAttachType(Constants.ATTACH_TYPE_ME);
              myAppList.add(mossApplication);
            }
          }
        }
      }
      applications=myAppList;
    }
  }
  PageResult<MossApplication> pageResult=getHaloApplicationPageResult(pageSize,pageNum,applications,findType,userName);
  return ResultData.ok(pageResult).build();
}
