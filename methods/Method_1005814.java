@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute ModuleQuery query) throws MyException {
  throwExceptionWhenIsNull(query.getProjectId(),"projectId");
  Page page=new Page(query);
  Project project=projectCache.get(query.getProjectId());
  checkPermission(project,ProjectPermissionEnum.READ);
  List<ModuleDto> moduleDtos=ModuleAdapter.getDto(moduleService.query(query),project);
  page.setAllRow(moduleService.count(query));
  return new JsonResult().data(moduleDtos).page(page);
}
