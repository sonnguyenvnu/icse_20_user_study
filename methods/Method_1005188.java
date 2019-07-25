/** 
 * ?????
 * @???Alexander
 * @param name
 * @param code
 * @param sb
 */
private void radio(String name,String code,StringBuffer sb){
  if (code.equals(this.defaultVal)) {
    sb.append("<input type=\"radio\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
  }
 else {
    sb.append("<input type=\"radio\" name=\"" + field + "\" value=\"" + code + "\"");
  }
  if (!StringUtils.isBlank(this.id)) {
    sb.append(" id=\"" + id + "\"");
  }
  this.readonly(sb);
  this.datatype(sb);
  if (!StringUtils.isBlank(this.extendJson)) {
    sb.append(this.getExtendJsonCommon(extendJson));
  }
  sb.append(" />");
  sb.append(MutiLangUtil.getLang(name) + "&nbsp;&nbsp;");
}
