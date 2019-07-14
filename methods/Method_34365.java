private void addToCollection(ConcurrentHashMap<String,Collection<OAuth2AccessToken>> store,String key,OAuth2AccessToken token){
  if (!store.containsKey(key)) {
synchronized (store) {
      if (!store.containsKey(key)) {
        store.put(key,new HashSet<OAuth2AccessToken>());
      }
    }
  }
  store.get(key).add(token);
}
