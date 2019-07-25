/** 
 * global output protocol
 * @param code
 * @param data
 */
protected void response(int code,String data){
  response.setContentType("application/json;charset=" + config.getCharset());
  JSONWriter json=JSONWriter.create().put("code",code).put("data",data);
  output.println(json.toString());
  output.flush();
  json=null;
}
