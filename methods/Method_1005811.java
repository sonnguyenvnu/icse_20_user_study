@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id,String projectId) throws MyException {
  Error model;
  if (id != null) {
    model=errorService.getById(id);
    checkPermission(projectCache.get(model.getProjectId()),ProjectPermissionEnum.READ);
  }
 else {
    model=new Error();
    model.setProjectId(projectId);
    model.setSequence(System.currentTimeMillis());
    checkPermission(projectCache.get(projectId),ProjectPermissionEnum.READ);
  }
  return new JsonResult(1,ErrorAdapter.getDto(model));
}
