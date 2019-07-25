@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute ProjectMetaQuery query) throws MyException {
  Assert.notNull(query.getProjectId());
  checkPermission(projectCache.get(query.getProjectId()),ProjectPermissionEnum.READ);
  List<ProjectMetaPO> projectMetas=projectMetaService.select(query);
  Page page=new Page(query);
  page.setAllRow(projectMetaService.count(query));
  List<ProjectMetaDTO> dto=ProjectMetaAdapter.getDto(projectMetas);
  return new JsonResult(1,dto,page);
}
