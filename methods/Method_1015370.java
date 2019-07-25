@GetMapping("/dashboard/report") public ResultData report(){
  return ResultData.ok(appService.getUseReport()).build();
}
