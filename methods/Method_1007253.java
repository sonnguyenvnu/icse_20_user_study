/** 
 * @throws Exception
 */
public void scan() throws Exception {
  StringBuilder html=new StringBuilder();
  addHtmlHeader(html);
  ClassPathAnnotationScanner scanner=new ClassPathAnnotationScanner(Plugin.class.getName(),new ClassPathScanner());
  for (  String plugin : scanner.scanPlugins(getClass().getClassLoader(),PluginManager.PLUGIN_PACKAGE.replace(".","/"))) {
    Class pluginClass=Class.forName(plugin);
    Plugin pluginAnnotation=(Plugin)pluginClass.getAnnotation(Plugin.class);
    String pluginName=pluginAnnotation.name();
    String pluginDocFile="plugin/" + pluginName + ".html";
    String pluginLink="ha-plugins/" + pluginName.toLowerCase() + "-plugin";
    URL url=new URL(getBaseURL(getClass()) + TARGET_DIR + pluginDocFile);
    boolean docExists=markdownProcessor.processPlugin(pluginClass,url);
    addHtmlRow(html,pluginAnnotation,docExists ? pluginLink : null);
  }
  addHtmlFooter(html);
  writeHtml(new URL(getBaseURL(getClass()) + TARGET_DIR + "plugins.html"),html.toString());
  String mainReadme=markdownProcessor.markdownToHtml(IOUtils.streamToString(new URL(getBaseURL(getClass()) + "/../README.md").openStream()));
  writeMainReadme(mainReadme);
}
