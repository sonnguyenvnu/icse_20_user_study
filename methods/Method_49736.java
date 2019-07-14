boolean isNeonSupported(String cpuInfoString){
  return cpuInfoString.contains("-neon");
}
