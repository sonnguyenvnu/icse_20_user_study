private static String determineTermuxArchName(){
  for (  String androidArch : Build.SUPPORTED_ABIS) {
switch (androidArch) {
case "arm64-v8a":
      return "aarch64";
case "armeabi-v7a":
    return "arm";
case "x86_64":
  return "x86_64";
case "x86":
return "i686";
}
}
throw new RuntimeException("Unable to determine arch from Build.SUPPORTED_ABIS =  " + Arrays.toString(Build.SUPPORTED_ABIS));
}
