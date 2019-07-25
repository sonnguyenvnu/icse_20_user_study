/** 
 * Loads a plugin by the specified plugin directory and put it into the specified holder.
 * @param pluginDirPath the specified plugin directory
 * @param holder        the specified holder
 * @return loaded plugin
 * @throws Exception exception
 */
private AbstractPlugin load(final String pluginDirPath,final Map<String,HashSet<AbstractPlugin>> holder) throws Exception {
  final Properties props=new Properties();
  final ServletContext servletContext=AbstractServletListener.getServletContext();
  String plugin=StringUtils.substringAfter(pluginDirPath,"/plugins");
  plugin=plugin.replace("/","");
  final File file=Latkes.getWebFile("/plugins/" + plugin + "/plugin.properties");
  props.load(new FileInputStream(file));
  final URL defaultClassesFileDirURL=servletContext.getResource("/plugins/" + plugin + "classes");
  URL classesFileDirURL=null;
  try {
    classesFileDirURL=servletContext.getResource(props.getProperty("classesDirPath"));
  }
 catch (  final MalformedURLException e) {
    LOGGER.log(Level.ERROR,"Reads [" + props.getProperty("classesDirPath") + "] failed",e);
  }
  URLClassLoader classLoader;
  if (null == defaultClassesFileDirURL) {
    classLoader=new URLClassLoader(new URL[]{classesFileDirURL},PluginManager.class.getClassLoader());
  }
 else {
    classLoader=new URLClassLoader(new URL[]{defaultClassesFileDirURL,classesFileDirURL},PluginManager.class.getClassLoader());
  }
  classLoaders.add(classLoader);
  String pluginClassName=props.getProperty(Plugin.PLUGIN_CLASS);
  if (StringUtils.isBlank(pluginClassName)) {
    pluginClassName=NotInteractivePlugin.class.getName();
  }
  final String rendererId=props.getProperty(Plugin.PLUGIN_RENDERER_ID);
  if (StringUtils.isBlank(rendererId)) {
    LOGGER.log(Level.WARN,"no renderer defined by this plugin[" + plugin + "]?this plugin will be ignore!");
    return null;
  }
  final Class<?> pluginClass=classLoader.loadClass(pluginClassName);
  LOGGER.log(Level.TRACE,"Loading plugin class[name={0}]",pluginClassName);
  final AbstractPlugin ret=(AbstractPlugin)pluginClass.newInstance();
  ret.setRendererId(rendererId);
  setPluginProps(plugin,ret,props);
  registerEventListeners(props,classLoader,ret);
  register(ret,holder);
  ret.changeStatus();
  return ret;
}
