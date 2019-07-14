/** 
 * ????????Array<br> Returns the array of SimpleMDAGNodes collectively containing the data of this MDAG, or null if it hasn't been simplified yet.
 * @return the array of SimpleMDAGNodes collectively containing the data of this MDAGif this MDAG has been simplified, or null if it has not
 */
public SimpleMDAGNode[] getSimpleMDAGArray(){
  return mdagDataArray;
}
