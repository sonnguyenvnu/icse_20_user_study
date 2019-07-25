@Override public void action(final Event<ViewLoadEventData> event){
  final ViewLoadEventData data=event.getData();
  final String viewName=data.getViewName();
  final Map<String,Object> dataModel=data.getDataModel();
  final PluginManager pluginManager=BeanManager.getInstance().getReference(PluginManager.class);
  final Set<AbstractPlugin> plugins=pluginManager.getPlugins(viewName);
  LOGGER.log(Level.DEBUG,"Plugin count[{0}] of view[name={1}]",plugins.size(),viewName);
  for (  final AbstractPlugin plugin : plugins) {
switch (plugin.getStatus()) {
case ENABLED:
      plugin.plug(dataModel);
    LOGGER.log(Level.DEBUG,"Plugged[name={0}]",plugin.getName());
  break;
case DISABLED:
plugin.unplug();
LOGGER.log(Level.DEBUG,"Unplugged[name={0}]",plugin.getName());
break;
default :
throw new AssertionError("Plugin state error, this is a bug! Please report this bug (https://github.com/b3log/latke/issues/new)!");
}
}
}
