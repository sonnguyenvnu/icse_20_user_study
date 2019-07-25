@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE) @ResponseBody public Result<Boolean> delete(@PathVariable("id") Integer id){
  if (id == null || id < 1) {
    return ResultGenerator.genFailResult("????");
  }
  return ResultGenerator.genSuccessResult(userDao.delUser(id) > 0);
}
