@SuppressFBWarnings({"SF_SWITCH_FALLTHROUGH","SF_SWITCH_NO_DEFAULT"}) public void load(ProxyServer proxy,File moduleDirectory) throws Exception {
  moduleDirectory.mkdir();
  ModuleVersion bungeeVersion=ModuleVersion.parse(proxy.getVersion());
  if (bungeeVersion == null) {
    System.out.println("Couldn't detect bungee version. Custom build?");
    return;
  }
  List<ModuleSpec> modules=new ArrayList<>();
  File configFile=new File("modules.yml");
  DumperOptions options=new DumperOptions();
  options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
  Yaml yaml=new Yaml(options);
  Map<String,Object> config;
  configFile.createNewFile();
  try (InputStream is=new FileInputStream(configFile)){
    config=(Map)yaml.load(is);
  }
   if (config == null) {
    config=new CaseInsensitiveMap<>();
  }
 else {
    config=new CaseInsensitiveMap<>(config);
  }
  List<String> defaults=new ArrayList<>();
  Object readModules=config.get("modules");
  if (readModules != null) {
    defaults.addAll((Collection)readModules);
  }
  int version=(config.containsKey("version")) ? (int)config.get("version") : 0;
switch (version) {
case 0:
    defaults.add("jenkins://cmd_alert");
  defaults.add("jenkins://cmd_find");
defaults.add("jenkins://cmd_list");
defaults.add("jenkins://cmd_send");
defaults.add("jenkins://cmd_server");
case 1:
defaults.add("jenkins://reconnect_yaml");
}
config.put("modules",defaults);
config.put("version",2);
try (FileWriter wr=new FileWriter(configFile)){
yaml.dump(config,wr);
}
 for (String s : (List<String>)config.get("modules")) {
URI uri=new URI(s);
ModuleSource source=knownSources.get(uri.getScheme());
if (source == null) {
System.out.println("Unknown module source: " + s);
continue;
}
String name=uri.getAuthority();
if (name == null) {
System.out.println("Unknown module host: " + s);
continue;
}
ModuleSpec spec=new ModuleSpec(name,new File(moduleDirectory,name + ".jar"),source);
modules.add(spec);
System.out.println("Discovered module: " + spec);
}
for (ModuleSpec module : modules) {
ModuleVersion moduleVersion=(module.getFile().exists()) ? getVersion(module.getFile()) : null;
if (!bungeeVersion.equals(moduleVersion)) {
System.out.println("Attempting to update plugin from " + moduleVersion + " to " + bungeeVersion);
module.getProvider().retrieve(module,bungeeVersion);
}
}
}
