/** 
 * ??????
 */
public static ArrayList<String> getTodayTime(){
  String data=TimeUtil.getData();
  String[] split=data.split("-");
  String year=split[0];
  String month=split[1];
  String day=split[2];
  ArrayList<String> list=new ArrayList<>();
  list.add(year);
  list.add(month);
  list.add(day);
  return list;
}
