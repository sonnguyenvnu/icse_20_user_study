private void test(boolean travis){
  downloadTest();
  String cp="temp" + File.pathSeparator + "bin" + File.pathSeparator + "ext/postgresql-42.2.5.jre7.jar" + File.pathSeparator + "ext/servlet-api-3.1.0.jar" + File.pathSeparator + "ext/lucene-core-5.5.5.jar" + File.pathSeparator + "ext/lucene-analyzers-common-5.5.5.jar" + File.pathSeparator + "ext/lucene-queryparser-5.5.5.jar" + File.pathSeparator + "ext/h2mig_pagestore_addon.jar" + File.pathSeparator + "ext/org.osgi.core-4.2.0.jar" + File.pathSeparator + "ext/org.osgi.enterprise-4.2.0.jar" + File.pathSeparator + "ext/jts-core-1.15.0.jar" + File.pathSeparator + "ext/slf4j-api-1.6.0.jar" + File.pathSeparator + "ext/slf4j-nop-1.6.0.jar" + File.pathSeparator + "ext/asm-7.0.jar" + File.pathSeparator + javaToolsJar;
  int version=getJavaVersion();
  if (version >= 9) {
    cp="src/java9/precompiled" + File.pathSeparator + cp;
  }
  int ret;
  if (travis) {
    ret=execJava(args("-ea","-Xmx128m","-XX:MaxDirectMemorySize=2g","-cp",cp,"org.h2.test.TestAll","travis"));
  }
 else {
    ret=execJava(args("-ea","-Xmx128m","-cp",cp,"org.h2.test.TestAll"));
  }
  if (ret != 0) {
    System.exit(ret);
  }
}
