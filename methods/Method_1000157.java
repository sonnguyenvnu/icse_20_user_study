@RequestMapping(value="/users",method=RequestMethod.PUT) @ResponseBody public Result<Boolean> update(@RequestBody User tempUser){
  if (tempUser.getId() == null || tempUser.getId() < 1 || StringUtils.isEmpty(tempUser.getName()) || StringUtils.isEmpty(tempUser.getPassword())) {
    return ResultGenerator.genFailResult("????");
  }
  User user=userDao.getUserById(tempUser.getId());
  if (user == null) {
    return ResultGenerator.genFailResult("????");
  }
  user.setName(tempUser.getName());
  user.setPassword(tempUser.getPassword());
  return ResultGenerator.genSuccessResult(userDao.updUser(user) > 0);
}
