/** 
 * ?????????????????
 * @param first
 * @param second
 */
public void addPair(String first,String second){
  Map<String,Integer> firstMatrix=transferMatrix.get(first);
  if (firstMatrix == null) {
    firstMatrix=new TreeMap<String,Integer>();
    transferMatrix.put(first,firstMatrix);
  }
  Integer frequency=firstMatrix.get(second);
  if (frequency == null)   frequency=0;
  firstMatrix.put(second,frequency + 1);
}
