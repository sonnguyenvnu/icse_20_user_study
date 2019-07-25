/** 
 * Load all client settings from the given settings. Note this will always at least return a client named "default".
 */
static Map<String,S3ClientSettings> load(Settings settings){
  Set<String> clientNames=settings.getGroups(PREFIX).keySet();
  Map<String,S3ClientSettings> clients=new HashMap<>();
  for (  String clientName : clientNames) {
    clients.put(clientName,getClientSettings(settings,clientName));
  }
  if (clients.containsKey("default") == false) {
    clients.put("default",getClientSettings(settings,"default"));
  }
  return Collections.unmodifiableMap(clients);
}
