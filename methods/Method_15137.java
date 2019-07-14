/** 
 * ROOT ????
 * @return
 */
@SuppressWarnings("unchecked") public Map<String,String> getMap(){
  try {
    return (Map<String,String>)sp.getAll();
  }
 catch (  Exception e) {
    Log.e(TAG,"getMap try { return (Map<String, String>) sp.getAll();" + "}catch(Exception e) {\n " + e.getMessage());
  }
  return null;
}
