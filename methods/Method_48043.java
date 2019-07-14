private Map<String,String> convertOptionsToMap(ConfigOption<String[]> optionsKey,Configuration config){
  String[] options=config.get(optionsKey);
  if (options.length % 2 != 0)   throw new IllegalArgumentException(optionsKey.getName() + " should have even number of elements.");
  final Map<String,String> converted=new HashMap<>(options.length / 2);
  for (int i=0; i < options.length; i+=2) {
    converted.put(options[i],options[i + 1]);
  }
  return ImmutableMap.copyOf(converted);
}
