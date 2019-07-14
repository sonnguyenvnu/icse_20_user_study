/** 
 * ???????????
 * @param input
 * @return
 */
private boolean isNumConnector(char input){
  int index=Arrays.binarySearch(Num_Connector,input);
  return index >= 0;
}
