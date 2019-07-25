private void compile(boolean debugInfo,boolean clientOnly,boolean basicResourcesOnly){
  clean();
  mkdir("temp");
  download();
  String classpath="temp" + File.pathSeparator + "ext/servlet-api-3.1.0.jar" + File.pathSeparator + "ext/lucene-core-5.5.5.jar" + File.pathSeparator + "ext/lucene-analyzers-common-5.5.5.jar" + File.pathSeparator + "ext/lucene-queryparser-5.5.5.jar" + File.pathSeparator + "ext/slf4j-api-1.6.0.jar" + File.pathSeparator + "ext/org.osgi.core-4.2.0.jar" + File.pathSeparator + "ext/org.osgi.enterprise-4.2.0.jar" + File.pathSeparator + "ext/jts-core-1.15.0.jar" + File.pathSeparator + "ext/asm-7.0.jar" + File.pathSeparator + javaToolsJar;
  FileList files;
  if (clientOnly) {
    files=files("src/main/org/h2/Driver.java");
    files.addAll(files("src/main/org/h2/jdbc"));
    files.addAll(files("src/main/org/h2/jdbcx"));
  }
 else {
    files=files("src/main");
  }
  StringList args=args();
  if (debugInfo) {
    args=args.plus("-Xlint:unchecked","-d","temp","-sourcepath","src/main","-classpath",classpath);
  }
 else {
    args=args.plus("-Xlint:unchecked","-g:none","-d","temp","-sourcepath","src/main","-classpath",classpath);
  }
  String version=getTargetJavaVersion();
  if (version != null) {
    args=args.plus("-target",version,"-source",version);
  }
  javac(args,files);
  files=files("src/main/META-INF/services");
  copy("temp",files,"src/main");
  if (!clientOnly) {
    files=files("src/test");
    files.addAll(files("src/tools"));
    files=files.exclude("src/test/org/h2/test/TestAllJunit.java");
    args=args("-Xlint:unchecked","-Xlint:deprecation","-d","temp","-sourcepath","src/test" + File.pathSeparator + "src/tools","-classpath",classpath);
    if (version != null) {
      args=args.plus("-target",version,"-source",version);
    }
    javac(args,files);
    files=files("src/test").exclude("*.java").exclude("*/package.html");
    copy("temp",files,"src/test");
  }
  resources(clientOnly,basicResourcesOnly);
}
