@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id,@ModelAttribute ProjectMetaQuery query) throws MyException {
  ProjectMetaPO projectMeta;
  Project project;
  Module module=null;
  if (id != null) {
    projectMeta=projectMetaService.get(id);
    project=projectCache.get(projectMeta.getProjectId());
    module=moduleCache.get(projectMeta.getModuleId());
  }
 else {
    project=projectCache.get(query.getProjectId());
    projectMeta=new ProjectMetaPO();
    projectMeta.setProjectId(query.getProjectId());
    projectMeta.setType(query.getType());
    projectMeta.setSequence(System.currentTimeMillis());
  }
  checkPermission(project,ProjectPermissionEnum.READ);
  ProjectMetaDTO projectMetaDto=ProjectMetaAdapter.getDto(projectMeta,module);
  return JsonResult.of().success().data(projectMetaDto);
}
