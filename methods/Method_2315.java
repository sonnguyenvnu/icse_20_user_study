/** 
 * ???
 * @param alias
 * @param page
 * @param request
 * @param model
 * @return
 */
@RequestMapping(value="/tag/{alias}",method=RequestMethod.GET) public String tag(@PathVariable("alias") String alias,@RequestParam(required=false,defaultValue="1",value="page") int page,HttpServletRequest request,Model model){
  CmsSystemExample cmsSystemExample=new CmsSystemExample();
  cmsSystemExample.createCriteria().andCodeEqualTo(CODE);
  CmsSystem system=cmsSystemService.selectFirstByExample(cmsSystemExample);
  model.addAttribute("system",system);
  CmsTagExample cmsTagExample=new CmsTagExample();
  cmsTagExample.createCriteria().andSystemIdEqualTo(system.getSystemId()).andAliasEqualTo(alias);
  CmsTag tag=cmsTagService.selectFirstByExample(cmsTagExample);
  model.addAttribute("tag",tag);
  int rows=10;
  List<CmsArticle> articles=cmsArticleService.selectCmsArticlesByTagId(tag.getTagId(),(page - 1) * rows,rows);
  model.addAttribute("articles",articles);
  long total=cmsArticleService.countByTagId(tag.getTagId());
  Paginator paginator=new Paginator(total,page,rows,request);
  model.addAttribute("paginator",paginator);
  return thymeleaf("/news/tag/index");
}
