@RequestMapping("/detail.do") @ResponseBody public JsonResult detail(@ModelAttribute Log log) throws Exception {
  Log model;
  if (!log.getId().equals(IConst.NULL_ID)) {
    model=logService.getById(log.getId());
    checkPermission(logService.getProjectIdByLog(model),ProjectPermissionEnum.MY_DATE);
  }
 else {
    model=new Log();
  }
  return new JsonResult(1,LogAdapter.getDto(model));
}
