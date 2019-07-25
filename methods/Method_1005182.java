public StringBuffer end(){
  StringBuffer sb=new StringBuffer();
  ComboBox comboBox=new ComboBox();
  comboBox.setText(text);
  comboBox.setId(id);
  sb.append("<script type=\"text/javascript\">" + "$(function() {" + "$(\'#" + name + "\').combobox({" + "url:\'" + url + "&id=" + id + "&text=" + text + "\'," + "editable:\'" + editable + "\'," + "valueField:\'id\'," + "textField:\'text\'," + "width:\'" + width + "\'," + "listWidth:\'" + listWidth + "\'," + "listHeight:\'" + listWidth + "\'," + "onChange:function(){" + "var val = $(\'#" + name + "\').combobox(\'getValues\');" + "$(\'#" + name + "hidden\').val(val);" + "}" + "});" + "});" + "</script>");
  sb.append("<input type=\"hidden\" name=\"" + name + "\" id=\"" + name + "hidden\" > " + "<input class=\"easyui-combobox\" " + "multiple=\"true\" panelHeight=\"auto\" name=\"" + name + "name\" id=\"" + name + "\" >");
  return sb;
}
