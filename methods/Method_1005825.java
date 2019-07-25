/** 
 * ???
 * @throws Exception 
 * @throws UnsupportedEncodingException 
 */
@RequestMapping("/staticize.do") @ResponseBody public JsonResult staticize(HttpServletRequest req,@RequestParam String projectId,String needStaticizes) throws UnsupportedEncodingException, Exception {
  if (MyString.isEmpty(needStaticizes)) {
    needStaticizes=",article,";
  }
 else {
    needStaticizes=",article," + needStaticizes + ",";
  }
  String secretKey=settingCache.get(S_SECRETKEY).getValue();
  Project project=projectCache.get(projectId);
  checkPermission(project);
  String path=Tools.getStaticPath(project);
  Tools.createFile(path);
  if (project.getType() != ProjectType.PUBLIC.getType()) {
    Tools.deleteFile(path);
    throw new MyException(MyError.E000044);
  }
  int pageSize=15;
  int totalPage=0;
  if (needStaticizes.indexOf(",error,") >= 0) {
    int errorSize=errorService.count(new ErrorQuery().setProjectId(projectId).setPageSize(IConst.ALL_PAGE_SIZE));
    totalPage=(errorSize + pageSize - 1) / pageSize;
    if (totalPage == 0) {
      totalPage=1;
    }
    for (int i=1; i <= totalPage; i++) {
      String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/errorList.do?projectId=" + projectId + "&currentPage=" + i + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
      Tools.staticize(html,path + "/errorList-" + i + ".html");
    }
  }
  Map<String,Object> map=new HashMap<>();
  for (  Module module : moduleService.query(new ModuleQuery().setProjectId(projectId).setPageSize(IConst.ALL_PAGE_SIZE))) {
    if (needStaticizes.indexOf(",article,") >= 0) {
      List<String> categorys=moduleService.queryCategoryByModuleId(module.getId());
      for (      String category : categorys) {
        if (MyString.isEmpty(category)) {
          continue;
        }
        ArticleQuery articleQuery=new ArticleQuery();
        articleQuery.setModuleId(module.getId()).setType(ArticleType.ARTICLE.name()).setCategory(category);
        int articleSize=articleService.count(articleQuery);
        totalPage=(articleSize + pageSize - 1) / pageSize;
        if (totalPage == 0) {
          totalPage=1;
        }
        for (int i=1; i <= totalPage; i++) {
          String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/articleList.do?moduleId=" + module.getId() + "&category=" + category + "&currentPage=" + i + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
          Tools.staticize(html,path + "/" + module.getId() + "-articleList-" + MD5.encrytMD5(category,"").substring(0,10) + "-" + i + ".html");
        }
      }
      ArticleQuery articleQuery=new ArticleQuery();
      articleQuery.setModuleId(module.getId()).setType(ArticleType.ARTICLE.name());
      int articleSize=articleService.count(articleQuery);
      totalPage=(articleSize + pageSize - 1) / pageSize;
      if (totalPage == 0) {
        totalPage=1;
      }
      for (int i=1; i <= totalPage; i++) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/articleList.do?moduleId=" + module.getId() + "&category=" + IConst.ALL + "&currentPage=" + i + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + module.getId() + "-articleList--" + i + ".html");
      }
      articleQuery.setPageSize(ALL_PAGE_SIZE);
      for (      Article article : articleService.query(articleQuery)) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/articleDetail.do?articleId=" + article.getId() + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + article.getId() + ".html");
      }
    }
    if (needStaticizes.indexOf(",dictionary,") >= 0) {
      ArticleQuery articleQuery=new ArticleQuery().setModuleId(module.getId()).setType(ArticleType.DICTIONARY.name()).setPageSize(IConst.ALL_PAGE_SIZE);
      int articleSize=articleService.count(articleQuery);
      totalPage=(articleSize + pageSize - 1) / pageSize;
      if (totalPage == 0) {
        totalPage=1;
      }
      for (int i=1; i <= totalPage; i++) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/articleList.do?moduleId=" + module.getId() + "&category=" + IConst.ALL + "&currentPage=" + i + "&type=DICTIONARY" + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + module.getId() + "-dictionaryList-" + i + ".html");
      }
      articleQuery.setPageSize(ALL_PAGE_SIZE);
      for (      Article article : articleService.query(articleQuery)) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/articleDetail.do?articleId=" + article.getId() + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + article.getId() + ".html");
      }
    }
    if (needStaticizes.indexOf("interface") >= 0) {
      InterfaceQuery interfaceQuery=new InterfaceQuery().setModuleId(module.getId()).setPageSize(IConst.ALL_PAGE_SIZE);
      totalPage=(interfaceService.count(interfaceQuery) + pageSize - 1) / pageSize;
      if (totalPage == 0) {
        totalPage=1;
      }
      for (int i=1; i <= totalPage; i++) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/interfaceList.do?moduleId=" + module.getId() + "&currentPage=" + i + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + module.getId() + "-interfaceList-" + i + ".html");
      }
      interfaceQuery.setPageSize(ALL_PAGE_SIZE);
      for (      Interface inter : interfaceService.query(interfaceQuery)) {
        String html=HttpPostGet.get(settingCache.getDomain() + "/user/staticize/interfaceDetail.do?interfaceId=" + inter.getId() + "&needStaticizes=" + needStaticizes + "&secretKey=" + secretKey,null,null,10 * 1000);
        Tools.staticize(html,path + "/" + inter.getId() + ".html");
      }
    }
  }
  return new JsonResult(1,null);
}
