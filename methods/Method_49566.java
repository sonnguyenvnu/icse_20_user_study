public synchronized static HBaseCompat getCompat(String classOverride){
  if (null != cachedCompat) {
    log.debug("Returning cached HBase compatibility layer: {}",cachedCompat);
    return cachedCompat;
  }
  HBaseCompat compat;
  String className=null;
  String classNameSource=null;
  if (null != classOverride) {
    className=classOverride;
    classNameSource="from explicit configuration";
  }
 else {
    String hbaseVersion=VersionInfo.getVersion();
    for (    String supportedVersion : HBASE_SUPPORTED_VERSIONS) {
      if (hbaseVersion.startsWith(supportedVersion + ".")) {
        className=DEFAULT_HBASE_COMPAT_CLASS_NAME;
        classNameSource="supporting runtime HBase version " + hbaseVersion;
        break;
      }
    }
    if (null == className) {
      log.info("The HBase version {} is not explicitly supported by JanusGraph.  " + "Loading JanusGraph's compatibility layer for its most recent supported HBase version ({})",hbaseVersion,DEFAULT_HBASE_COMPAT_VERSION);
      className=DEFAULT_HBASE_COMPAT_CLASS_NAME;
      classNameSource=" by default";
    }
  }
  final String errTemplate=" when instantiating HBase compatibility class " + className;
  try {
    compat=(HBaseCompat)Class.forName(className).newInstance();
    log.info("Instantiated HBase compatibility layer {}: {}",classNameSource,compat.getClass().getCanonicalName());
  }
 catch (  IllegalAccessException|InstantiationException|ClassNotFoundException e) {
    throw new RuntimeException(e.getClass().getSimpleName() + errTemplate,e);
  }
  return cachedCompat=compat;
}
