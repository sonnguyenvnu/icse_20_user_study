/** 
 * Check through the various modes and see if this is a legit sketch. Because the default mode will be the first in the list, this will always prefer that one over the others.
 */
File checkSketchFolder(File subfolder,String item){
  for (  Mode mode : getModeList()) {
    File entry=new File(subfolder,item + "." + mode.getDefaultExtension());
    if (entry.exists()) {
      return entry;
    }
  }
  return null;
}
