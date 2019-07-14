/** 
 * ?????????????
 */
private boolean isAliasFiled(String filedName){
  if (select.getFields().size() > 0) {
    for (    Field field : select.getFields()) {
      if (null != field.getAlias() && field.getAlias().equals(filedName)) {
        return true;
      }
    }
  }
  return false;
}
