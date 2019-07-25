/** 
 * ??? hh:mm:ss ???????????????long? mm:ss ?? ss ?????????
 * @param ss
 * @return ??:?
 */
public static long format(String ss){
  String[] tt=ss.split(":");
  int[] unit=new int[4];
  unit[0]=60;
  unit[1]=60;
  unit[2]=60;
  unit[3]=24;
  long total=0;
  int j=0;
  int x=1;
  for (int i=tt.length - 1; i >= 0; i--, j++) {
    String t=tt[i];
    int time=Integer.parseInt(t);
    time*=x;
    total+=time;
    x*=unit[j];
  }
  return total;
}
