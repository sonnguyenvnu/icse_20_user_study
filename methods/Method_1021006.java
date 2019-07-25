/** 
 * ?? jfinal use ???????????
 * @param configName
 * @return
 */
@Override public M use(String configName){
  M m=this.get(DATASOURCE_CACHE_PREFIX + configName);
  if (m == null) {
synchronized (configName.intern()) {
      m=this.get(DATASOURCE_CACHE_PREFIX + configName);
      if (m == null) {
        m=this.copy().superUse(configName);
        this.put(DATASOURCE_CACHE_PREFIX + configName,m);
      }
    }
  }
  return m;
}
