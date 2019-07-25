/** 
 * Unification applies a piece of memory within the current argument to a statement which creates an instantiated statement
 * @param statement
 * @return the instantiated statement with elements of the argument applied as much as possible
 */
public String unify(String statement){
  if (statement.indexOf('$') < 0)   return statement;
  JSONArray table=this.getData();
  if (table != null && table.length() > 0) {
    for (int rownum=0; rownum < table.length(); rownum++) {
      JSONObject row=table.getJSONObject(rownum);
      for (      String key : row.keySet()) {
        int i;
        while ((i=statement.indexOf("$" + key + "$")) >= 0) {
          statement=statement.substring(0,i) + row.get(key).toString() + statement.substring(i + key.length() + 2);
        }
        if (statement.indexOf('$') < 0)         break;
      }
      if (statement.indexOf('$') < 0)       break;
    }
  }
  return statement;
}
