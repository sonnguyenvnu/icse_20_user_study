/** 
 * ?????
 * @param nums     ????
 * @param sperator ???
 * @return int[]
 */
public static int[] parseInts(String nums,String sperator){
  String[] ss=StringUtils.split(nums,sperator);
  int[] ints=new int[ss.length];
  for (int i=0; i < ss.length; i++) {
    ints[i]=Integer.parseInt(ss[i]);
  }
  return ints;
}
