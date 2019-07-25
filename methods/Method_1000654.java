public String render(NutBean context,boolean showKey){
  StringBuilder sb=new StringBuilder();
  if (null == context)   context=new NutMap();
  for (  TmplEle ele : list) {
    ele.join(sb,context,showKey);
  }
  return sb.toString();
}
