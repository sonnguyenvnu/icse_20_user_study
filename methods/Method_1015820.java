/** 
 * Called by the loader to initialize the fields in this plugin.
 * @param proxy current proxy instance
 * @param description the description that describes this plugin
 */
final void init(ProxyServer proxy,PluginDescription description){
  this.proxy=proxy;
  this.description=description;
  this.file=description.getFile();
  this.logger=new PluginLogger(this);
}
