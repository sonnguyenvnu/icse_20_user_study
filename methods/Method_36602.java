/** 
 * Create a news table
 * @return
 */
@RequestMapping("/create") public Map<String,Object> create(){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  try {
    Connection cn=dataSource.getConnection();
    Statement st=cn.createStatement();
    st.execute("DROP TABLE IF EXISTS NewsTable;" + "CREATE TABLE NewsTable(ID INT AUTO_INCREMENT, PRIMARY KEY (ID), AUTHOR VARCHAR(50),TITLE VARCHAR(255));");
    resultMap.put("success",true);
    resultMap.put("result","CREATE TABLE NewsTable(ID INT AUTO_INCREMENT, PRIMARY KEY (ID), AUTHOR VARCHAR(50), TITLE VARCHAR(255))");
  }
 catch (  Throwable throwable) {
    resultMap.put("success",false);
    resultMap.put("error",throwable.getMessage());
  }
  return resultMap;
}
