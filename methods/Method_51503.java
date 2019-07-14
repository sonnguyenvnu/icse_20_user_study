private void maybeAdd(String attr,String value,StringBuilder buf){
  if (value != null && value.length() > 0) {
    buf.append(' ').append(attr).append("=\"");
    StringUtil.appendXmlEscaped(buf,value,useUTF8);
    buf.append('"');
  }
}
