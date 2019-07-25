/** 
 * ??
 */
@RequestMapping(value="/categories/info/{id}",method=RequestMethod.GET) @ResponseBody public Result info(@PathVariable("id") Long id){
  NewsCategory newsCategory=categoryService.queryById(id);
  return ResultGenerator.genSuccessResult(newsCategory);
}
