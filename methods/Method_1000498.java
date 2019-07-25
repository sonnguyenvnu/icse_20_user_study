/** 
 * ????
 */
@SuppressWarnings({"unchecked","rawtypes"}) public CustomMake init(){
  List<String> plug=(List<String>)((Map)NutConf.get("EL")).get("custom");
  String[] t=plug.toArray(new String[0]);
  PluginManager<RunMethod> rm=new SimplePluginManager<RunMethod>(t);
  for (  RunMethod r : rm.gets()) {
    me().runms.put(r.fetchSelf(),r);
  }
  return this;
}
