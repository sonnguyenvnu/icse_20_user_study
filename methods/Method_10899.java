/** 
 * ???????????
 * @param IDStr ????
 * @return ?????"??" ?????String??
 * @throws ParseException
 */
@SuppressWarnings("unchecked") public static String IDCardValidate(String IDStr){
  String errorInfo="";
  String[] ValCodeArr={"1","0","x","9","8","7","6","5","4","3","2"};
  String[] Wi={"7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"};
  String Ai="";
  if (IDStr.length() != 15 && IDStr.length() != 18) {
    errorInfo="??????????15??18??";
    return errorInfo;
  }
  if (IDStr.length() == 18) {
    Ai=IDStr.substring(0,17);
  }
 else   if (IDStr.length() == 15) {
    Ai=IDStr.substring(0,6) + "19" + IDStr.substring(6,15);
  }
  if (isNumeric(Ai) == false) {
    errorInfo="???15???????? ; 18????????????????";
    return errorInfo;
  }
  String strYear=Ai.substring(6,10);
  String strMonth=Ai.substring(10,12);
  String strDay=Ai.substring(12,14);
  if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
    errorInfo="????????";
    return errorInfo;
  }
  GregorianCalendar gc=new GregorianCalendar();
  SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
  try {
    if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
      errorInfo="????????????";
      return errorInfo;
    }
  }
 catch (  NumberFormatException e) {
    e.printStackTrace();
  }
catch (  java.text.ParseException e) {
    e.printStackTrace();
  }
  if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
    errorInfo="???????";
    return errorInfo;
  }
  if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
    errorInfo="???????";
    return errorInfo;
  }
  Hashtable h=GetAreaCode();
  if (h.get(Ai.substring(0,2)) == null) {
    errorInfo="??????????";
    return errorInfo;
  }
  int TotalmulAiWi=0;
  for (int i=0; i < 17; i++) {
    TotalmulAiWi=TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
  }
  int modValue=TotalmulAiWi % 11;
  String strVerifyCode=ValCodeArr[modValue];
  Ai=Ai + strVerifyCode;
  if (IDStr.length() == 18) {
    if (Ai.equals(IDStr) == false) {
      errorInfo="????????????????";
      return errorInfo;
    }
  }
 else {
    return "??";
  }
  return "??";
}
