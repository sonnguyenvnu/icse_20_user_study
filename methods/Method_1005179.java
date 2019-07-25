/** 
 * ??????
 * @param sb
 */
private void callback(StringBuffer sb,String methodname){
  sb.append("function clickcallback_" + methodname + "(){");
  sb.append("iframe = this.iframe.contentWindow;");
  String[] textnames=null;
  String[] inputTextnames=null;
  if (StringUtil.isNotEmpty(textname)) {
    textnames=textname.split(",");
    if (StringUtil.isNotEmpty(inputTextname)) {
      inputTextnames=inputTextname.split(",");
    }
 else {
      inputTextnames=textnames;
    }
    for (int i=0; i < textnames.length; i++) {
      inputTextnames[i]=inputTextnames[i].replaceAll("\\[","\\\\\\\\[").replaceAll("\\]","\\\\\\\\]").replaceAll("\\.","\\\\\\\\.");
      sb.append("var " + textnames[i] + "=iframe.get" + name + "Selections(\'" + textnames[i] + "\');	");
      sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
      sb.append("$(\'#" + inputTextnames[i] + "\').val(" + textnames[i] + ");");
      sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
      sb.append("}");
      sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
      sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
      sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
      sb.append("}");
    }
  }
  if (StringUtil.isNotEmpty(hiddenName)) {
    sb.append("var id =iframe.get" + name + "Selections(\'" + hiddenid + "\');");
    sb.append("if (id!== undefined &&id!=\"\"){");
    sb.append("$(\'#" + hiddenName + "\').val(id);");
    sb.append("}");
  }
  if (StringUtil.isNotEmpty(fun)) {
    sb.append("" + fun + "();");
  }
  sb.append("}");
}
