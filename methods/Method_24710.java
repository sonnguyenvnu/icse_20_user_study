/** 
 * Check whether a  {@link LineID} is on the current tab.
 * @param line the {@link LineID}
 * @return true, if the {@link LineID} is on the current tab.
 */
public boolean isInCurrentTab(LineID line){
  return line.fileName().equals(getSketch().getCurrentCode().getFileName());
}
