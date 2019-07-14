/** 
 * If no user or password has been provided, prompt for it. If you want to avoid the prompt, pass in an empty user or password.
 * @param config The properties object to load to configuration into.
 */
private static void promptForCredentialsIfMissing(Map<String,String> config){
  Console console=System.console();
  if (console == null) {
    return;
  }
  if (!config.containsKey(ConfigUtils.URL)) {
    return;
  }
  if (!config.containsKey(ConfigUtils.USER)) {
    config.put(ConfigUtils.USER,console.readLine("Database user: "));
  }
  if (!config.containsKey(ConfigUtils.PASSWORD)) {
    char[] password=console.readPassword("Database password: ");
    config.put(ConfigUtils.PASSWORD,password == null ? "" : String.valueOf(password));
  }
}
