/** 
 * 1.2.24
 * @param enumClasses
 */
public void configEnumAsJavaBean(Class<? extends Enum>... enumClasses){
  for (  Class<? extends Enum> enumClass : enumClasses) {
    put(enumClass,createJavaBeanSerializer(enumClass));
  }
}
