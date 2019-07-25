/** 
 * ?????
 * @???Alexander
 * @param name
 * @param code
 * @param sb
 */
private void checkbox(String name,String code,StringBuffer sb){
  if (this.defaultVal == null) {
    this.defaultVal="";
  }
  String[] values=this.defaultVal.split(",");
  Boolean checked=false;
  for (int i=0; i < values.length; i++) {
    String value=values[i];
    if (code.equals(value)) {
      checked=true;
      break;
    }
    checked=false;
  }
  if (checked) {
    sb.append("<input type=\"checkbox\" name=\"" + field + "\" checked=\"checked\" value=\"" + code + "\"");
  }
 else {
    sb.append("<input type=\"checkbox\" name=\"" + field + "\" value=\"" + code + "\"");
  }
  if (!StringUtils.isBlank(this.id)) {
    sb.append(" id=\"" + id + "\"");
  }
  this.readonly(sb);
  this.datatype(sb);
  if (!StringUtils.isBlank(this.extendJson)) {
    sb.append(" " + this.getExtendJsonCommon(extendJson));
  }
  sb.append(" />");
  sb.append(MutiLangUtil.getLang(name) + "&nbsp;&nbsp;");
}
