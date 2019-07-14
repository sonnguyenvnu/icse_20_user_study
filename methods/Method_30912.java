public static boolean isSamsung(){
  return getBuildProperties().containsKey("ro.build.PDA");
}
