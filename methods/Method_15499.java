/** 
 * ????
 */
@Override public void recycle(){
  if (tri) {
    request.put(KEY_TRY,tri);
  }
  if (drop) {
    request.put(KEY_DROP,drop);
  }
  if (correct != null) {
    request.put(KEY_CORRECT,correct);
  }
  corrected=null;
  method=null;
  parentPath=null;
  arrayConfig=null;
  request=null;
  response=null;
  sqlRequest=null;
  sqlReponse=null;
  functionMap=null;
  customMap=null;
  childMap=null;
}
