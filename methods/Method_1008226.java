@Override protected void execute(Terminal terminal,OptionSet options) throws Exception {
  final Map<String,String> settings=new HashMap<>();
  for (  final KeyValuePair kvp : settingOption.values(options)) {
    if (kvp.value.isEmpty()) {
      throw new UserException(ExitCodes.USAGE,"setting [" + kvp.key + "] must not be empty");
    }
    if (settings.containsKey(kvp.key)) {
      final String message=String.format(Locale.ROOT,"setting [%s] already set, saw [%s] and [%s]",kvp.key,settings.get(kvp.key),kvp.value);
      throw new UserException(ExitCodes.USAGE,message);
    }
    settings.put(kvp.key,kvp.value);
  }
  putSystemPropertyIfSettingIsMissing(settings,"path.data","es.path.data");
  putSystemPropertyIfSettingIsMissing(settings,"path.home","es.path.home");
  putSystemPropertyIfSettingIsMissing(settings,"path.logs","es.path.logs");
  execute(terminal,options,createEnv(terminal,settings));
}
