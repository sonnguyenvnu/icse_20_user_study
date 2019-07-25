/** 
 * Plugs with the specified data model and the args from request.
 * @param dataModel dataModel
 * @param context   context
 */
public void plug(final Map<String,Object> dataModel,final RequestContext context){
  String content=(String)dataModel.get(Plugin.PLUGINS);
  if (null == content) {
    dataModel.put(Plugin.PLUGINS,"");
  }
  handleLangs(dataModel);
  fillDefault(dataModel);
  postPlug(dataModel,context);
  content=(String)dataModel.get(Plugin.PLUGINS);
  final StringBuilder contentBuilder=new StringBuilder(content);
  contentBuilder.append(getViewContent(dataModel));
  final String pluginsContent=contentBuilder.toString();
  dataModel.put(Plugin.PLUGINS,pluginsContent);
  LOGGER.log(Level.DEBUG,"Plugin[name={0}] has been plugged",getName());
}
