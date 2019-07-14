static void trySet(Field field,Object target,@Nullable Object value){
  try {
    field.set(target,value);
  }
 catch (  IllegalAccessException e) {
    throw new RuntimeException("Unable to assign " + value + " to " + field + " on " + target,e);
  }
}
