/** 
 * ??
 */
@RequestMapping(value="/info/{id}",method=RequestMethod.GET) public Result info(@PathVariable("id") Integer id,@TokenToUser AdminUser loginUser){
  if (loginUser == null) {
    return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN,"????");
  }
  if (id < 1) {
    return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"?????");
  }
  Picture picture=pictureService.queryObject(id);
  if (picture == null) {
    return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"?????");
  }
  return ResultGenerator.genSuccessResult(picture);
}
