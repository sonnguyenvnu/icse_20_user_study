public Path getConfigInPluginDir(){
  return PathUtils.get(new File(AnalysisIkPlugin.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent(),"config").toAbsolutePath();
}
