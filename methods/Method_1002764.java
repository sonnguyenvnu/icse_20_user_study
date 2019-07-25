/** 
 * Retrieves the version information of Armeria artifacts using the specified  {@link ClassLoader}.
 * @return A {@link Map} whose keys are Maven artifact IDs and whose values are {@link Version}s
 */
public static Map<String,Version> identify(@Nullable ClassLoader classLoader){
  if (classLoader == null) {
    classLoader=getContextClassLoader();
  }
  final Properties props=new Properties();
  try {
    final Enumeration<URL> resources=classLoader.getResources(PROP_RESOURCE_PATH);
    while (resources.hasMoreElements()) {
      final URL url=resources.nextElement();
      final InputStream in=url.openStream();
      try {
        props.load(in);
      }
  finally {
        Closeables.closeQuietly(in);
      }
    }
  }
 catch (  Exception ignore) {
  }
  final Set<String> artifactIds=new HashSet<>();
  for (  Object o : props.keySet()) {
    final String k=(String)o;
    final int dotIndex=k.indexOf('.');
    if (dotIndex <= 0) {
      continue;
    }
    final String artifactId=k.substring(0,dotIndex);
    if (!props.containsKey(artifactId + PROP_VERSION) || !props.containsKey(artifactId + PROP_COMMIT_DATE) || !props.containsKey(artifactId + PROP_SHORT_COMMIT_HASH) || !props.containsKey(artifactId + PROP_LONG_COMMIT_HASH) || !props.containsKey(artifactId + PROP_REPO_STATUS)) {
      continue;
    }
    artifactIds.add(artifactId);
  }
  final Map<String,Version> versions=new HashMap<>();
  for (  String artifactId : artifactIds) {
    versions.put(artifactId,new Version(artifactId,props.getProperty(artifactId + PROP_VERSION),parseIso8601(props.getProperty(artifactId + PROP_COMMIT_DATE)),props.getProperty(artifactId + PROP_SHORT_COMMIT_HASH),props.getProperty(artifactId + PROP_LONG_COMMIT_HASH),props.getProperty(artifactId + PROP_REPO_STATUS)));
  }
  return versions;
}
