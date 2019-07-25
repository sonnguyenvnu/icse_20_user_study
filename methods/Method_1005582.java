public static PluginLauncher instance(){
  if (runtime == null) {
synchronized (PluginLauncher.class) {
      if (runtime == null) {
        runtime=new PluginLauncher();
      }
    }
  }
  return runtime;
}
