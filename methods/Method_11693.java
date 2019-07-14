@Override public void process(Page page){
  if (page.getUrl().regex(LIST_URL).match()) {
    List<String> ids=new JsonPathSelector("$.data[*]._id").selectList(page.getRawText());
    if (CollectionUtils.isNotEmpty(ids)) {
      for (      String id : ids) {
        page.addTargetRequest("http://angularjs.cn/api/article/" + id);
      }
    }
  }
 else {
    page.putField("title",new JsonPathSelector("$.data.title").select(page.getRawText()));
    page.putField("content",new JsonPathSelector("$.data.content").select(page.getRawText()));
  }
}
