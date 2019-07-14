private static void addArgument(Class type,Field field,Object target,Object value,String delimiter){
  try {
    Object[] os=(Object[])field.get(target);
    Object[] vs=(Object[])getValue(type,value,delimiter);
    Object[] s=(Object[])Array.newInstance(type.getComponentType(),os.length + vs.length);
    System.arraycopy(os,0,s,0,os.length);
    System.arraycopy(vs,0,s,os.length,vs.length);
    field.set(target,s);
  }
 catch (  IllegalAccessException iae) {
    throw new IllegalArgumentException("Could not set field " + field,iae);
  }
catch (  NoSuchMethodException e) {
    throw new IllegalArgumentException("Could not find constructor in class " + type.getName() + " that takes a string",e);
  }
}
