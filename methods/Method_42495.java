/** 
 */
public String getStatement(String sqlId){
  String name=this.getClass().getName();
  StringBuilder sb=new StringBuilder();
  sb.append(name).append(".").append(sqlId);
  return sb.toString();
}
