/** 
 * Gets the number of cores available in this device, across all processors. Requires: Ability to peruse the filesystem at "/sys/devices/system/cpu"
 * @return The number of cores, or 1 if failed to get result
 */
private int getNumCoresOldPhones(){
class CpuFilter implements FileFilter {
    @Override public boolean accept(    File pathname){
      return Pattern.matches("cpu[0-9]+",pathname.getName());
    }
  }
  try {
    File dir=new File("/sys/devices/system/cpu/");
    File[] files=dir.listFiles(new CpuFilter());
    return files.length;
  }
 catch (  Exception e) {
    return 1;
  }
}
