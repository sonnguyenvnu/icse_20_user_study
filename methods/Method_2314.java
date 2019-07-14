/** 
 * ???
 * @param alias
 * @param page
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value="/category/{alias}",method=RequestMethod.GET) public String category(@PathVariable("alias") String alias,@RequestParam(required=false,defaultValue="1",value="page") int page,HttpServletRequest request,Model model){
  CmsSystemExample cmsSystemExample=new CmsSystemExample();
  cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
  CmsSystem system=cmsSystemService.selectFirstByExample(cmsSystemExample);
  model.addAttribute("system",system);
  CmsCategoryExample cmsCategoryExample=new CmsCategoryExample();
  cmsCategoryExample.createCriteria().andSystemIdEqualTo(system.getSystemId()).andAliasEqualTo(alias);
  CmsCategory category=cmsCategoryService.selectFirstByExample(cmsCategoryExample);
  model.addAttribute("category",category);
  int rows=10;
  List<CmsArticle> articles=cmsArticleService.selectCmsArticlesByCategoryId(category.getCategoryId(),(page - 1) * rows,rows);
  model.addAttribute("articles",articles);
  long total=cmsArticleService.countByCategoryId(category.getCategoryId());
  Paginator paginator=new Paginator(total,page,rows,request);
  model.addAttribute("paginator",paginator);
  return thymeleaf("/news/category/index");
}
