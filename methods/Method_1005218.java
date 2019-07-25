public static void main(String[] args){
  String str="jform_tbd[0].name";
  String strs[]=str.split("\\.");
  org.jeecgframework.core.util.LogUtil.info(strs.length + "");
  org.jeecgframework.core.util.LogUtil.info(strs[0].substring(0,strs[0].indexOf("[")));
}
