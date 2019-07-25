@GetMapping("/news/edit/{newsId}") public String edit(HttpServletRequest request,@PathVariable("newsId") Long newsId){
  request.setAttribute("path","edit");
  News news=newsService.queryNewsById(newsId);
  if (news == null) {
    return "error/error_400";
  }
  request.setAttribute("news",news);
  request.setAttribute("categories",categoryService.getAllCategories());
  return "admin/edit";
}
