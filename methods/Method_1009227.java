private static String body(ErrorBean errorBean){
  StringBuilder stringBuilder=new StringBuilder();
  int level=0;
  version(stringBuilder,errorBean.getPluginVersion());
  description(stringBuilder,level + 1,errorBean.getDescription());
  exception(stringBuilder,level + 1,errorBean);
  attachments(stringBuilder,level + 1,errorBean.getAttachments());
  return stringBuilder.toString();
}
