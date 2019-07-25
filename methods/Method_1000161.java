/** 
 * ??
 */
@RequestMapping(value="/info/{id}",method=RequestMethod.GET) public Result info(@PathVariable("id") Integer id){
  Article article=articleService.queryObject(id);
  return ResultGenerator.genSuccessResult(article);
}
