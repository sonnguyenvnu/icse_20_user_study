/** 
 * ????????
 */
public static ArrayList<String> getLastTime(String year,String month,String day){
  Calendar ca=Calendar.getInstance();
  ca.set(Integer.valueOf(year),Integer.valueOf(month) - 1,Integer.valueOf(day));
  int inDay=ca.get(Calendar.DATE);
  ca.set(Calendar.DATE,inDay - 1);
  ArrayList<String> list=new ArrayList<>();
  list.add(String.valueOf(ca.get(Calendar.YEAR)));
  list.add(String.valueOf(ca.get(Calendar.MONTH) + 1));
  list.add(String.valueOf(ca.get(Calendar.DATE)));
  return list;
}
