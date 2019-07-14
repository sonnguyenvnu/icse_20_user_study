/** 
 * Register lifecycle listener(s) with these token services.
 * @param lifecycleListeners The listeners.
 */
@Autowired(required=false) public void register(OAuthTokenLifecycleListener... lifecycleListeners){
  if (lifecycleListeners != null) {
    this.lifecycleListeners.addAll(Arrays.asList(lifecycleListeners));
  }
}
