/** 
 * Registers the specified plugin into the specified holder.
 * @param plugin the specified plugin
 * @param holder the specified holder
 */
private void register(final AbstractPlugin plugin,final Map<String,HashSet<AbstractPlugin>> holder){
  final String rendererId=plugin.getRendererId();
  final String[] redererIds=rendererId.split(";");
  for (  final String rid : redererIds) {
    final HashSet<AbstractPlugin> set=holder.computeIfAbsent(rid,k -> new HashSet<>());
    set.add(plugin);
  }
  LOGGER.log(Level.DEBUG,"Registered plugin[name={0}, version={1}] for rendererId[name={2}], [{3}] plugins totally",plugin.getName(),plugin.getVersion(),rendererId,holder.size());
}
