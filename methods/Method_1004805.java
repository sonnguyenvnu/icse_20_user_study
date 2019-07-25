public static EsMajorVersion parse(String version){
  if (version.startsWith("0.")) {
    return new EsMajorVersion((byte)0,version);
  }
  if (version.startsWith("1.")) {
    return new EsMajorVersion((byte)1,version);
  }
  if (version.startsWith("2.")) {
    return new EsMajorVersion((byte)2,version);
  }
  if (version.startsWith("5.")) {
    return new EsMajorVersion((byte)5,version);
  }
  if (version.startsWith("6.")) {
    return new EsMajorVersion((byte)6,version);
  }
  if (version.startsWith("7.")) {
    return new EsMajorVersion((byte)7,version);
  }
  if (version.startsWith("8.")) {
    return new EsMajorVersion((byte)8,version);
  }
  throw new EsHadoopIllegalArgumentException("Unsupported/Unknown Elasticsearch version [" + version + "]." + "Highest supported version is [" + LATEST.version + "]. You may need to upgrade ES-Hadoop.");
}
