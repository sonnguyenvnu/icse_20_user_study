public static BeanTemplate parse(Class<?> clz){
  BeanTemplate template=new BeanTemplate();
  String fullName=clz.getName();
  String simpleName=clz.getSimpleName();
  String pkg="key." + fullName.substring(0,fullName.lastIndexOf("."));
  template.setPackageName(pkg);
  template.setClzName(simpleName + "Key");
  Field[] fields=clz.getDeclaredFields();
  for (  Field field : fields) {
    if (field.getModifiers() > 2)     continue;
    template.getPropList().add(field.getName());
  }
  System.out.println(template);
  return template;
}
