private static void init(){
  clazzMap=new LinkedHashMap<String,Class>();
  clazzMap.put("1",OschinaBlog.class);
  clazzMap.put("2",IteyeBlog.class);
  clazzMap.put("3",News163.class);
  urlMap=new LinkedHashMap<String,String>();
  urlMap.put("1","http://my.oschina.net/flashsword/blog");
  urlMap.put("2","http://flashsword20.iteye.com/");
  urlMap.put("3","http://news.163.com/");
}
