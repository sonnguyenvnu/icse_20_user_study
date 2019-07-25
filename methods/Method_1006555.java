protected boolean enabled(){
  Map<String,String> options=processingEnv.getOptions();
  return !options.containsKey(disableKey);
}
