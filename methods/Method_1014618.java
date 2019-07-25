private ServerPlugin load(File pluginFile){
  LogManager.LOGGER.info("Loading plugin file " + pluginFile.getName());
  ZipFile zipFile=null;
  try {
    zipFile=new ZipFile(pluginFile);
    ZipEntry configEntry=zipFile.getEntry("plugin.properties");
    if (configEntry != null) {
      InputStream stream=zipFile.getInputStream(configEntry);
      Properties pluginConfig=new Properties();
      pluginConfig.load(stream);
      ClassLoader loader=URLClassLoader.newInstance(new URL[]{pluginFile.toURI().toURL()});
      Class<?> aClass=Class.forName(pluginConfig.getProperty("classpath"),true,loader);
      Class<? extends ServerPlugin> pluginClass=aClass.asSubclass(ServerPlugin.class);
      Constructor<? extends ServerPlugin> constructor=pluginClass.getConstructor();
      ServerPlugin plugin=constructor.newInstance();
      plugin.setName(pluginConfig.getProperty("name"));
      plugin.setVersion(pluginConfig.getProperty("version"));
      String dependStr=pluginConfig.getProperty("depend");
      if (dependStr != null) {
        for (        String dep : dependStr.split(",")) {
          plugin.dependencies.add(dep.trim());
        }
      }
      return plugin;
    }
 else {
      LogManager.LOGGER.severe("Couldn't find plugin.properties in " + pluginFile.getName());
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
 finally {
    try {
      if (zipFile != null) {
        zipFile.close();
      }
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
  return null;
}
