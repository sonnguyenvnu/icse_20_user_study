public void join(StringBuilder sb,NutBean context,boolean showKey){
  Object val=__get_val(context,key);
  if (null == val) {
    if (null != _dft_key) {
      val=__get_val(context,_dft_key);
    }
 else     if (null != _dft_val) {
      val=_dft_val;
    }
  }
  String str=_val(val);
  if (null == str) {
    if (showKey) {
      sb.append("${").append(key).append('}');
    }
  }
 else {
    sb.append(str);
  }
}
