@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(@ModelAttribute Source source) throws MyException {
  Source model;
  Module module;
  Project project;
  if (!MyString.isEmpty(source.getId())) {
    model=sourceService.getById(source.getId());
    module=moduleCache.get(model.getModuleId());
    checkPermission(model.getProjectId(),ProjectPermissionEnum.READ);
  }
 else {
    model=new Source();
    model.setModuleId(source.getModuleId());
    module=moduleCache.get(source.getModuleId());
    model.setSequence(System.currentTimeMillis());
    if (module == null || module.getId() == null) {
      module=null;
      project=projectCache.get(source.getProjectId());
      model.setProjectId(project.getId());
    }
  }
  return new JsonResult(1,SourceAdapter.getDto(model,module));
}
