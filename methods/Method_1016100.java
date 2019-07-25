/** 
 * Returns whether the specified plugin is accepted by this dependency or not.
 * @param plugin plugin to check
 * @return true if the specified plugin is accepted by this dependency, false otherwise
 */
public boolean accept(final Plugin plugin){
  if (plugin != null && plugin.getId().equals(pluginId)) {
    final PluginVersion pv=plugin.getVersion();
    return (minVersion == null || pv.isNewerOrSame(minVersion)) && (maxVersion == null || pv.isOlderOrSame(maxVersion));
  }
  return false;
}
