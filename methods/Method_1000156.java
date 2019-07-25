@RequestMapping(value="/users",method=RequestMethod.POST) @ResponseBody public Result<Boolean> insert(@RequestBody User user){
  if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
    return ResultGenerator.genFailResult("????");
  }
  return ResultGenerator.genSuccessResult(userDao.insertUser(user) > 0);
}
