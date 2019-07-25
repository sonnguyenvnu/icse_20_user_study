private void parse(String[] argv){
  OptionSet options=buildOptionParser().parse(argv);
  Properties properties;
  if (options.has("config")) {
    properties=parseFile((String)options.valueOf("config"),true);
  }
 else {
    properties=parseFile(DEFAULT_CONFIG_FILE,false);
  }
  String envConfigPrefix=fetchOption("env_config_prefix",options,properties,null);
  if (envConfigPrefix != null) {
    String prefix=envConfigPrefix.toLowerCase();
    System.getenv().entrySet().stream().filter(map -> map.getKey().toLowerCase().startsWith(prefix)).forEach(config -> properties.put(config.getKey().toLowerCase().replaceFirst(prefix,""),config.getValue()));
  }
  if (options.has("help"))   usage("Help for Maxwell:");
  setup(options,properties);
  List<?> arguments=options.nonOptionArguments();
  if (!arguments.isEmpty()) {
    usage("Unknown argument(s): " + arguments);
  }
}
