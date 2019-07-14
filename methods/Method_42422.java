/** 
 * ??????? return 2014-5-4?2014-5-3
 */
public static List<String> getLastDays(int countDay){
  List<String> listDate=new ArrayList<String>();
  for (int i=0; i < countDay; i++) {
    listDate.add(DateUtils.getReqDateyyyyMMdd(DateUtils.getDate(-i)));
  }
  return listDate;
}
