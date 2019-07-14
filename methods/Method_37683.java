private int detectJavaVersionNumber(){
  String javaVersion=JAVA_VERSION;
  final int lastDashNdx=javaVersion.lastIndexOf('-');
  if (lastDashNdx != -1) {
    javaVersion=javaVersion.substring(0,lastDashNdx);
  }
  if (javaVersion.startsWith("1.")) {
    final int index=javaVersion.indexOf('.',2);
    return Integer.parseInt(javaVersion.substring(2,index));
  }
 else {
    final int index=javaVersion.indexOf('.');
    return Integer.parseInt(index == -1 ? javaVersion : javaVersion.substring(0,index));
  }
}
