/** 
 * ????
 */
@RequestMapping(value="/categories/list",method=RequestMethod.GET) @ResponseBody public Result list(@RequestParam Map<String,Object> params){
  if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
    return ResultGenerator.genFailResult("?????");
  }
  PageQueryUtil pageUtil=new PageQueryUtil(params);
  return ResultGenerator.genSuccessResult(categoryService.getCategoryPage(pageUtil));
}
