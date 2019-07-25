/** 
 * ??readonly ??,?????? readonly????????
 * @author jg_xugj
 * @param sb
 * @return sb
 */
private StringBuffer readonly(StringBuffer sb){
  if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
    if ("radio".equals(type)) {
      sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" disabled=\"true\" ");
    }
 else     if ("checkbox".equals(type)) {
      sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" disabled=\"true\" ");
    }
 else     if ("text".equals(type)) {
    }
 else     if ("list".equals(type)) {
      sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" ");
    }
 else {
      sb.append(" readonly=\"readonly\" style=\"background-color:#eee;cursor:no-drop;\" ");
    }
  }
  return sb;
}
