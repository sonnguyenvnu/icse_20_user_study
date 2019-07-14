private static ReadConfiguration getLocalConfiguration(String shortcutOrFile){
  File file=new File(shortcutOrFile);
  if (file.exists())   return getLocalConfiguration(file);
 else {
    int pos=shortcutOrFile.indexOf(':');
    if (pos < 0)     pos=shortcutOrFile.length();
    String backend=shortcutOrFile.substring(0,pos);
    Preconditions.checkArgument(StandardStoreManager.getAllManagerClasses().containsKey(backend.toLowerCase()),"Backend shorthand unknown: %s",backend);
    String secondArg=null;
    if (pos + 1 < shortcutOrFile.length())     secondArg=shortcutOrFile.substring(pos + 1).trim();
    BaseConfiguration config=new BaseConfiguration();
    ModifiableConfiguration writeConfig=new ModifiableConfiguration(ROOT_NS,new CommonsConfiguration(config),BasicConfiguration.Restriction.NONE);
    writeConfig.set(STORAGE_BACKEND,backend);
    ConfigOption option=Backend.getOptionForShorthand(backend);
    if (option == null) {
      Preconditions.checkArgument(secondArg == null);
    }
 else     if (option == STORAGE_DIRECTORY || option == STORAGE_CONF_FILE) {
      Preconditions.checkArgument(StringUtils.isNotBlank(secondArg),"Need to provide additional argument to initialize storage backend");
      writeConfig.set(option,getAbsolutePath(secondArg));
    }
 else     if (option == STORAGE_HOSTS) {
      Preconditions.checkArgument(StringUtils.isNotBlank(secondArg),"Need to provide additional argument to initialize storage backend");
      writeConfig.set(option,new String[]{secondArg});
    }
 else     throw new IllegalArgumentException("Invalid configuration option for backend " + option);
    return new CommonsConfiguration(config);
  }
}
