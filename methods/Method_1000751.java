public Object get(ServletContext sc,HttpServletRequest req,HttpServletResponse resp,Object refer){
  ObjectNaviNode no=new ObjectNaviNode();
  String pre="";
  if ("".equals(prefix))   pre="node.";
  ParamExtractor pe=Params.makeParamExtractor(req,refer);
  for (  Object name : pe.keys()) {
    String na=(String)name;
    if (na.startsWith(prefix)) {
      no.put(pre + na,pe.extractor(na));
    }
  }
  Object model=no.get();
  return Mapl.maplistToObj(model,type);
}
