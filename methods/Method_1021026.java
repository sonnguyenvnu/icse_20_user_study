public void index(){
  String html;
  try {
    String viewPath=config.getPath().endsWith("/") ? config.getPath() : config.getPath() + "/";
    html=renderToString(viewPath + "index.html",Maps.newHashMap());
  }
 catch (  Exception ex) {
    renderHtml("error?please put  <a href=\"https://github.com/swagger-api/swagger-ui\" target=\"_blank\">swagger-ui</a> " + "into your project path :  " + config.getPath() + " <br />" + "or click <a href=\"" + config.getPath() + "/json\">here</a>  show swagger json.");
    return;
  }
  String basePath=getRequest().getRequestURL().toString();
  String jsonUrl=basePath + "json";
  html=html.replace("http://petstore.swagger.io/v2/swagger.json",jsonUrl);
  html=html.replace("https://petstore.swagger.io/v2/swagger.json",jsonUrl);
  html=html.replace("src=\"./","src=\"" + basePath);
  html=html.replace("href=\"./","href=\"" + basePath);
  renderHtml(html);
}
