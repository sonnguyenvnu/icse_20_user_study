/** 
 * Lookups value as an alias and, if not found, as a default alias.
 */
protected String lookupAlias(final String alias){
  String value=actionsManager.lookupPathAlias(alias);
  if (value == null) {
    ActionRuntime cfg=actionsManager.lookup(alias);
    if (cfg != null) {
      value=cfg.getActionPath();
    }
  }
  return value;
}
