public static void reboot(ArrayList<String> cmd,Map<String,String> env,String startdir,String... UMSOptions){
  final ArrayList<String> reboot;
  String macAppPath=null;
  if (Platform.isMac()) {
    String libraryPath=ManagementFactory.getRuntimeMXBean().getLibraryPath();
    if (StringUtils.isNotBlank(libraryPath)) {
      Pattern pattern=Pattern.compile("(.+?\\.app)/Contents/MacOS");
      Matcher matcher=pattern.matcher(libraryPath);
      if (matcher.find()) {
        macAppPath=matcher.group(1);
      }
    }
  }
  if (StringUtils.isNotBlank(macAppPath)) {
    reboot=new ArrayList<>();
    reboot.add("open");
    reboot.add("-n");
    reboot.add("-a");
    reboot.add(macAppPath);
    if (UMSOptions.length > 0) {
      reboot.add("--args");
    }
  }
 else {
    reboot=getUMSCommand();
  }
  if (UMSOptions.length > 0) {
    reboot.addAll(Arrays.asList(UMSOptions));
  }
  if (cmd == null) {
    cmd=reboot;
  }
 else {
    if (env == null) {
      env=new HashMap<>();
    }
    env.put("RESTART_CMD",StringUtils.join(reboot," "));
    env.put("RESTART_DIR",System.getProperty("user.dir"));
  }
  if (startdir == null) {
    startdir=System.getProperty("user.dir");
  }
  System.out.println("Starting: " + StringUtils.join(cmd," "));
  final ProcessBuilder pb=new ProcessBuilder(cmd);
  if (env != null) {
    pb.environment().putAll(env);
  }
  pb.directory(new File(startdir));
  System.out.println("In folder: " + pb.directory());
  try {
    pb.start();
  }
 catch (  Exception e) {
    e.printStackTrace();
    return;
  }
  System.exit(0);
}
