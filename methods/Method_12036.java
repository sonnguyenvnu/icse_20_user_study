private List<Runner> runners(Class<?>[] children){
  List<Runner> runners=new ArrayList<Runner>();
  for (  Class<?> each : children) {
    Runner childRunner=safeRunnerForClass(each);
    if (childRunner != null) {
      runners.add(childRunner);
    }
  }
  return runners;
}
