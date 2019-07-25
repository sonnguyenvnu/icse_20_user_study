public static void parse(Class<?> clz){
  Annotation reyClientAnno=clz.getAnnotation(ReyClient.class);
  if (reyClientAnno == null)   return;
  ReyClient reyClient=(ReyClient)reyClientAnno;
  String url=reyClient.value();
  ClientParsed parsed=new ClientParsed();
  map.put(clz.getName(),parsed);
  parsed.setObjectType(clz);
  parsed.setUrl(url);
  Class<?> fallbackClz=reyClient.fallback();
  if (fallbackClz != null && fallbackClz != void.class) {
    Method[] fallbackMethodArr=fallbackClz.getMethods();
    for (    Method fm : fallbackMethodArr) {
      parsed.getFallbackMethodMap().put(fm.getName(),fm);
    }
    try {
      parsed.setFallback(fallbackClz.newInstance());
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  Method[] arr=clz.getDeclaredMethods();
  for (  Method method : arr) {
    String methodName=method.getName();
    Class<?> returnType=method.getReturnType();
    Annotation mappingAnno=method.getAnnotation(RequestMapping.class);
    if (mappingAnno == null)     throw new RuntimeException(clz.getName() + "." + methodName + ", Not Found Annotation: " + RequestMapping.class.getName());
    RequestMapping requestMapping=(RequestMapping)mappingAnno;
    if (requestMapping.value() == null || requestMapping.value().length == 0)     throw new RuntimeException(clz.getName() + "." + methodName + " RequestMapping, no mapping value");
    String mapping=requestMapping.value()[0];
    RequestMethod rm=RequestMethod.POST;
    RequestMethod[] rmArr=requestMapping.method();
    if (rmArr == null || rmArr.length == 0) {
      if (mapping != null && mapping.contains("{") && mapping.contains("}")) {
        rm=RequestMethod.GET;
      }
    }
 else {
      rm=rmArr[0];
    }
    List<KV> hearderList=null;
    String[] headers=requestMapping.headers();
    if (headers != null && headers.length > 0) {
      hearderList=new ArrayList<>();
      for (      String header : headers) {
        int i=header.indexOf("=");
        String key=header.substring(0,i);
        String value=header.substring(i + 1);
        KV kv=new KV(key,value);
        hearderList.add(kv);
      }
    }
    MethodParsed methodParsed=new MethodParsed();
    methodParsed.setRequestMapping(mapping);
    methodParsed.setReturnType(returnType);
    methodParsed.setRequestMethod(rm);
    methodParsed.setHeaderList(hearderList);
    parsed.getMap().put(methodName,methodParsed);
  }
}
