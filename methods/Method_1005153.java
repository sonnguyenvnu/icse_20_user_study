/** 
 * ??
 */
public static void add(String newUserName,DirContext dc){
  try {
    BasicAttributes attrs=new BasicAttributes();
    BasicAttribute objclassSet=new BasicAttribute("objectClass");
    objclassSet.add("sAMAccountName");
    objclassSet.add("employeeID");
    attrs.put(objclassSet);
    attrs.put("ou",newUserName);
    dc.createSubcontext("ou=" + newUserName + "," + ROOT,attrs);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
