@RequestMapping(method={RequestMethod.GET,RequestMethod.POST}) @ApiOperation("?????") public String run(HttpServletRequest request) throws Exception {
  return new ActionEnter(request,getDownloadPath(request)).exec();
}
