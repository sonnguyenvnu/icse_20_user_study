public static <T extends Plugin<S>,S>Java8PluginRegistry<T,S> empty(){
  return Java8PluginRegistry.of(Collections.emptyList());
}
