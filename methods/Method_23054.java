public void addConfiguredRuntime(FileSet runtime) throws BuildException {
  if (this.runtime != null) {
    throw new BuildException("Runtime already specified.");
  }
  this.runtime=runtime;
  runtime.appendIncludes(new String[]{"jre/"});
  runtime.appendExcludes(new String[]{"bin/","jre/bin/orbd","jre/bin/pack200","jre/bin/policytool","jre/bin/rmid","jre/bin/rmiregistry","jre/bin/servertool","jre/bin/tnameserv","jre/bin/unpack200","jre/lib/deploy/","jre/lib/deploy.jar","jre/lib/javaws.jar","jre/lib/libdeploy.dylib","jre/lib/libnpjp2.dylib","jre/lib/plugin.jar","jre/lib/security/javaws.policy"});
  if (!javafx) {
    runtime.appendExcludes(new String[]{"jre/THIRDPARTYLICENSEREADME-JAVAFX.txt","jre/lib/javafx.properties","jre/lib/jfxrt.jar","jre/lib/security/javafx.policy","jre/lib/fxplugins.dylib","jre/lib/libdecora-sse.dylib","jre/lib/libglass.dylib","jre/lib/libglib-2.0.0.dylib","jre/lib/libgstplugins-lite.dylib","jre/lib/libgstreamer-lite.dylib","jre/lib/libjavafx-font.dylib","jre/lib/libjavafx-iio.dylib","jre/lib/libjfxmedia.dylib","jre/lib/libjfxwebkit.dylib","jre/lib/libprism-es2.dylib"});
  }
}
