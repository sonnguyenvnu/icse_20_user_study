@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(BugDTO dto) throws MyException {
  throwExceptionWhenIsNull(dto.getProjectId(),"projectId ????");
  String id=dto.getId();
  dto.setProjectId(getProjectId(dto.getProjectId(),dto.getModuleId()));
  checkPermission(dto.getProjectId(),ProjectPermissionEnum.READ);
  Project project=projectCache.get(dto.getProjectId());
  Module module=moduleCache.get(dto.getModuleId());
  if (id != null) {
    BugPO bugPO=bugService.get(id);
    module=moduleCache.get(bugPO.getModuleId());
    return new JsonResult(1,BugAdapter.getDto(bugPO,module,project));
  }
  BugDTO bugDTO=BugAdapter.getDTO(project,module);
  return new JsonResult(1,bugDTO);
}
