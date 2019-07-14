/** 
 * ??Map,???map?????
 * @param src
 * @param rate
 * @return
 */
public static Map<String,String[]> splitMap(Map<String,String[]> src,double rate){
  assert 0 <= rate && rate <= 1;
  Map<String,String[]> output=new TreeMap<String,String[]>();
  for (  Map.Entry<String,String[]> entry : src.entrySet()) {
    String[][] array=spiltArray(entry.getValue(),rate);
    output.put(entry.getKey(),array[0]);
    entry.setValue(array[1]);
  }
  return output;
}
