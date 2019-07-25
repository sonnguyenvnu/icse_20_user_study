@RequestMapping("/detail.do") @ResponseBody public JsonResult detail(String id,String moduleId,String projectId,String envId) throws MyException {
  InterfaceWithBLOBs model;
  Module module;
  if (id != null) {
    model=interfaceService.getById(id);
    module=moduleCache.get(model.getModuleId());
  }
 else {
    model=InterfaceAdapter.getInit();
    module=moduleCache.get(moduleId);
    model.setModuleId(moduleId);
    model.setProjectId(module.getProjectId() == null ? projectId : module.getProjectId());
    if (!MyString.isEmpty(module.getTemplateId())) {
      InterfaceWithBLOBs template=interfaceService.getById(module.getTemplateId());
      if (template != null) {
        BeanUtil.copyProperties(template,model,"id","projectId","interfaceName","url","fulUrl","sequence","isTemplate");
      }
    }
  }
  checkPermission(model.getProjectId(),ProjectPermissionEnum.READ);
  InterfaceDto interfaceDto=InterfaceAdapter.getDtoWithBLOBs(model,module,null,false);
  if (envId != null) {
    ProjectMetaDTO dto=ProjectMetaAdapter.getDto(projectMetaService.get(envId),null);
    if (dto != null && MyString.isNotEmpty(dto.getEnvUrl())) {
      interfaceDto.setFullUrl(interfaceDto.getFullUrl().replaceFirst(dto.getEnvUrl(),dto.getValue()));
    }
  }
  return new JsonResult(1,interfaceDto);
}
