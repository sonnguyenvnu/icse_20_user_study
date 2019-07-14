static String[] buildEnvironment(boolean failSafe,String cwd){
  new File(TermuxService.HOME_PATH).mkdirs();
  if (cwd == null)   cwd=TermuxService.HOME_PATH;
  List<String> environment=new ArrayList<>();
  environment.add("TERM=xterm-256color");
  environment.add("HOME=" + TermuxService.HOME_PATH);
  environment.add("PREFIX=" + TermuxService.PREFIX_PATH);
  environment.add("BOOTCLASSPATH" + System.getenv("BOOTCLASSPATH"));
  environment.add("ANDROID_ROOT=" + System.getenv("ANDROID_ROOT"));
  environment.add("ANDROID_DATA=" + System.getenv("ANDROID_DATA"));
  environment.add("EXTERNAL_STORAGE=" + System.getenv("EXTERNAL_STORAGE"));
  addToEnvIfPresent(environment,"ANDROID_RUNTIME_ROOT");
  addToEnvIfPresent(environment,"ANDROID_TZDATA_ROOT");
  if (failSafe) {
    environment.add("PATH= " + System.getenv("PATH"));
  }
 else {
    if (shouldAddLdLibraryPath()) {
      environment.add("LD_LIBRARY_PATH=" + TermuxService.PREFIX_PATH + "/lib");
    }
    environment.add("LANG=en_US.UTF-8");
    environment.add("PATH=" + TermuxService.PREFIX_PATH + "/bin:" + TermuxService.PREFIX_PATH + "/bin/applets");
    environment.add("PWD=" + cwd);
    environment.add("TMPDIR=" + TermuxService.PREFIX_PATH + "/tmp");
  }
  return environment.toArray(new String[0]);
}
