public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  sb.append("<textarea id=\"" + name + "_text\" name=\"" + name + "\">" + value + "</textarea>");
  sb.append("<script type=\"text/javascript\">var ckeditor_" + name + "=CKEDITOR.replace(\"" + name + "_text\",{");
  if (StringUtil.isNotEmpty(type))   sb.append(type);
  sb.append("});");
  sb.append("</script>");
  return sb;
}
