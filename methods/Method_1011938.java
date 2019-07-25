public static void main(String[] args) throws Exception {
  String mpsInternal=System.getProperty("mps.internal");
  System.setProperty("idea.is.internal",mpsInternal != null ? mpsInternal : "false");
  String fsNotifierKey="idea.filewatcher.executable.path";
  String altExecPath=System.getProperty(fsNotifierKey);
  if (altExecPath == null || !new File(altExecPath).isFile()) {
    String execPath=PathManager.getBinPath() + File.separatorChar + getFsNotifierName();
    if (!new File(execPath).exists()) {
      System.setProperty(fsNotifierKey,PathManager.getBinPath() + File.separatorChar + getFsNotifierDir() + File.separatorChar + getFsNotifierName());
    }
  }
  System.setProperty("idea.additional.classpath",getAdditionalMPSClasspathString());
  Bootstrap.main(args,"jetbrains.mps.MPSMainImpl","start");
}
