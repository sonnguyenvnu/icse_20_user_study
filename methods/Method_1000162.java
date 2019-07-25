/** 
 * ??
 */
@RequestMapping(value="/list",method=RequestMethod.GET) public Result list(@RequestParam Map<String,Object> params){
  if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
    return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR,"?????");
  }
  PageUtil pageUtil=new PageUtil(params);
  return ResultGenerator.genSuccessResult(pictureService.getPicturePage(pageUtil));
}
