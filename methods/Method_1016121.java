/** 
 * Returns whether both list of files have equal files in the same positions or not.
 * @param files1 first files list to be compared
 * @param files2 second files list to be compared
 * @return true if both list of files have equal files in the same positions, false otherwise
 */
public static boolean equals(final List<File> files1,final List<File> files2){
  if (files1.size() != files2.size()) {
    return false;
  }
 else   if (files1.size() == files2.size() && files2.size() == 0) {
    return true;
  }
 else {
    for (int i=0; i < files1.size(); i++) {
      if (!files1.get(i).getAbsolutePath().equals(files2.get(i).getAbsolutePath())) {
        return false;
      }
    }
    return true;
  }
}
