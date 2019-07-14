private static int getCoresFromCPUFileList(){
  return new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
}
