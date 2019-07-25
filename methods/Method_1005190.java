/** 
 * ?????
 * @???Alexander
 * @param name
 * @param code
 * @param sb
 */
private void select(String name,String code,StringBuffer sb){
  if (code.equals(this.defaultVal)) {
    if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
      sb.append(" <option style=\"display: none;\"  value=\"" + code + "\" selected=\"selected\">");
    }
 else {
      sb.append(" <option  value=\"" + code + "\" selected=\"selected\">");
    }
  }
 else {
    if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
      sb.append(" <option style=\"display: none;\" value=\"" + code + "\">");
    }
 else {
      sb.append(" <option  value=\"" + code + "\">");
    }
  }
  sb.append(MutiLangUtil.getLang(name));
  sb.append(" </option>");
}
