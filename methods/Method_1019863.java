@RequestMapping("/create") public Map<String,Object> create(){
  Map<String,Object> resultMap=new HashMap<String,Object>();
  try {
    Connection cn=simpleDataSource.getConnection();
    Statement st=cn.createStatement();
    st.execute("DROP TABLE IF EXISTS TEST;\n" + "CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255));");
    resultMap.put("success",true);
    resultMap.put("result","CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255))");
  }
 catch (  Throwable throwable) {
    resultMap.put("success",false);
    resultMap.put("error",throwable.getMessage());
  }
  return resultMap;
}
