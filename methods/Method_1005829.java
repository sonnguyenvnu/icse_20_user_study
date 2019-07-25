@RequestMapping("/list.do") @ResponseBody public JsonResult list(@ModelAttribute ModuleQuery query,String password,String visitCode) throws MyException {
  throwExceptionWhenIsNull(query.getProjectId(),"projectId");
  Project project=projectCache.get(query.getProjectId());
  checkFrontPermission(password,visitCode,project);
  Page page=new Page(query);
  page.setAllRow(moduleService.count(query));
  List<ModuleDto> moduleDtoList=ModuleAdapter.getDto(moduleService.query(query),null);
  return new JsonResult().data(moduleDtoList).page(page).others(Tools.getMap("crumbs",Tools.getCrumbs(project.getName(),"void"),"project",ProjectAdapter.getDto(project,null)));
}
