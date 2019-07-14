@Secured({"ROLE_ADMIN","ROLE_USER"}) @RequestMapping(method=RequestMethod.POST) @ResponseBody public Object save(@RequestBody SysUser user){
  return userService.create(user);
}
