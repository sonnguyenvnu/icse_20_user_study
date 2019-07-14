/** 
 * ?????????
 * @param src ???
 * @param rate ??????????
 * @return ????
 */
public static String[][] spiltArray(String[] src,double rate){
  assert 0 <= rate && rate <= 1;
  String[][] output=new String[2][];
  output[0]=new String[(int)(src.length * rate)];
  output[1]=new String[src.length - output[0].length];
  System.arraycopy(src,0,output[0],0,output[0].length);
  System.arraycopy(src,output[0].length,output[1],0,output[1].length);
  return output;
}
