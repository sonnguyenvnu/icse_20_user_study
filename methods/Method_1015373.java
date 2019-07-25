/** 
 * ??checkApp??????
 * @return
 */
@GetMapping("/checkApp") public ResultData search(){
  AppTakeoverModel appTakeoverModel=new AppTakeoverModel();
  Flux<MossApplication> applicationFlux=applicationFlux("");
  List<String> takeoverAppList=new ArrayList<String>();
  List<String> noTakeoverAppList=new ArrayList<String>();
  List<MossApplication> applications=applicationFlux.collectList().block();
  for (  MossApplication mossApplication : applications) {
    if (appService.checkAppAndMappingName(mossApplication.getName())) {
      takeoverAppList.add(mossApplication.getName());
    }
 else {
      noTakeoverAppList.add(mossApplication.getName());
    }
  }
  appTakeoverModel.setNoTakeoverAppList(noTakeoverAppList);
  appTakeoverModel.setTakeoverAppList(takeoverAppList);
  return ResultData.builder().data(appTakeoverModel).build();
}
