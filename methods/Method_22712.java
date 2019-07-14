/** 
 * Internal helper function to set the current tab based on a name.
 * @param findName the file name (not pretty name) to be shown
 */
public void setCurrentCode(String findName){
  for (int i=0; i < codeCount; i++) {
    if (findName.equals(code[i].getFileName()) || findName.equals(code[i].getPrettyName())) {
      setCurrentCode(i);
      return;
    }
  }
}
