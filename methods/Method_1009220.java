private static void create(@NotNull ErrorBean errorBean) throws IOException {
  Request request=new Request(errorBean);
  BrowserUtil.open(ISSUES_URL + "/new?" + "title=" + URLEncoder.encode(request.title,"UTF-8") + "&" + "body=" + URLEncoder.encode(request.body,"UTF-8"));
}
