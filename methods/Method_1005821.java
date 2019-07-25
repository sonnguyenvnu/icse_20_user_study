@RequestMapping("/quit.do") public String quit(@RequestParam String projectId,HttpServletRequest request) throws Exception {
  LoginInfoDto loginInfoDto=LoginUserHelper.getUser();
  String userId=loginInfoDto.getId();
  List<ProjectUserPO> projectUser=projectUserService.select(new ProjectUserQuery().setUserId(userId).setProjectId(projectId));
  if (CollectionUtils.isEmpty(projectUser)) {
    request.setAttribute("title","????");
    request.setAttribute("result","????");
    return ERROR_VIEW;
  }
  projectUserService.delete(projectUser.get(0).getId());
  request.setAttribute("title","????");
  request.setAttribute("result","????");
  return ERROR_VIEW;
}
