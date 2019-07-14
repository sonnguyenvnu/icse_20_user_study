private <T>void reflectionFieldConsumer(String fieldName,CheckedConsumer<Field> consumer){
  Field field=null;
  try {
    field=TextFieldSkin.class.getDeclaredField(fieldName);
    field.setAccessible(true);
    consumer.accept(field);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
