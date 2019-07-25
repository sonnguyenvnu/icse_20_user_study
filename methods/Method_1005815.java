@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id,String projectId) throws MyException {
  Module module;
  Project project;
  Interface templeteInterface=null;
  if (id != null) {
    module=moduleService.getById(id);
    project=projectCache.get(module.getProjectId());
    checkPermission(project,ProjectPermissionEnum.READ);
    if (module.getTemplateId() != null) {
      templeteInterface=interfaceService.getById(module.getTemplateId());
    }
  }
 else {
    project=projectCache.get(projectId);
    checkPermission(project,ProjectPermissionEnum.READ);
    module=new Module();
    module.setStatus(Byte.valueOf("1"));
    module.setProjectId(projectId);
    module.setSequence(System.currentTimeMillis());
  }
  return new JsonResult(1,ModuleAdapter.getDto(module,project,templeteInterface));
}
