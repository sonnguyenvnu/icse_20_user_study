public static byte[] eval(SusiAction action,JSONArray data,boolean compressed,String threadnameprefix,final boolean useHeadlessLoader){
  if (action.getRenderType() != RenderType.loader)   return new byte[0];
  JSONArray urls=action.getArrayAttr("urls");
  List<String> urlss=new ArrayList<>();
  urls.forEach(u -> urlss.add(((String)u)));
  byte[] payload=data.toString(2).getBytes(StandardCharsets.UTF_8);
  return load(urlss,payload,compressed,threadnameprefix,useHeadlessLoader);
}
