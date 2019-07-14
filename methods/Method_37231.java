@Setup public void setup(){
  final NestedJavaBean nestedJavaBean3=new NestedJavaBean().setFieldA("nested-3");
  final NestedJavaBean nestedJavaBean2=new NestedJavaBean().setFieldA("nested-2").setNestedJavaBean(nestedJavaBean3);
  final NestedJavaBean nestedJavaBean1=new NestedJavaBean().setFieldA("nested-1").setNestedJavaBean(nestedJavaBean2);
  javaBean=new JavaBean().setFieldA("fieldA").setNestedJavaBean(nestedJavaBean1);
}
