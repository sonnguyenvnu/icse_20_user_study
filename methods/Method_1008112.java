/** 
 * Parses settings and read all settings available under azure.client.
 * @param settings settings to parse
 * @return All the named configurations
 */
public static Map<String,AzureStorageSettings> load(Settings settings){
  Map<String,AzureStorageSettings> storageSettings=new HashMap<>();
  for (  String clientName : ACCOUNT_SETTING.getNamespaces(settings)) {
    storageSettings.put(clientName,getClientSettings(settings,clientName));
  }
  if (storageSettings.containsKey("default") == false && storageSettings.isEmpty() == false) {
    AzureStorageSettings defaultSettings=storageSettings.values().iterator().next();
    storageSettings.put("default",defaultSettings);
  }
  return Collections.unmodifiableMap(storageSettings);
}
