@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id) throws MyException {
  if (MyString.isEmpty(id)) {
    Project projectPO=new Project();
    projectPO.setType(ProjectType.PRIVATE.getByteType());
    projectPO.setStatus(ProjectStatus.COMMON.getStatus());
    projectPO.setLuceneSearch(CommonEnum.FALSE.getByteValue());
    projectPO.setSequence(System.currentTimeMillis());
    return new JsonResult(1,ProjectAdapter.getDto(projectPO,null));
  }
  Project projectPO=projectService.getById(id);
  ProjectDto dto=ProjectAdapter.getDto(projectPO,userService.getById(projectPO.getUserId()));
  dto.setInviteUrl(projectService.getInviteUrl(dto));
  LoginInfoDto user=LoginUserHelper.getUser();
  if (Tools.isSuperAdmin(user.getAuthStr())) {
    return new JsonResult(1,dto);
  }
  if (user.getId().equals(projectPO.getUserId())) {
    dto.setProjectPermission(ProjectPermissionEnum.MY_DATE.getValue());
    return new JsonResult(1,dto);
  }
  List<ProjectUserPO> projectUserPOList=projectUserService.select(new ProjectUserQuery().setUserId(user.getId()).setProjectId(projectPO.getId()));
  if (CollectionUtils.isEmpty(projectUserPOList)) {
    throw new MyException(MyError.E000022);
  }
  dto.setProjectPermission(projectUserPOList.get(0).getPermission());
  return new JsonResult(1,dto);
}
