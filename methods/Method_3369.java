/** 
 * Creates a  {@link ValueCreator} object able to create object assignable from given type,using a static one arg method which name is the the given one taking a String object as parameter
 * @param compatibleType the base assignable for which this object will try to invoke the given method
 * @param methodName     the name of the one arg method taking a String as parameter that will be used to built a new value
 * @return null if the object could not be created, the value otherwise
 */
public static ValueCreator byStaticMethodInvocation(final Class<?> compatibleType,final String methodName){
  return new ValueCreator(){
    public Object createValue(    Class<?> type,    String value){
      Object v=null;
      if (compatibleType.isAssignableFrom(type)) {
        try {
          Method m=type.getMethod(methodName,String.class);
          return m.invoke(null,value);
        }
 catch (        NoSuchMethodException e) {
        }
catch (        Exception e) {
          throw new IllegalArgumentException(String.format("could not invoke %s#%s to create an obejct from %s",type.toString(),methodName,value));
        }
      }
      return v;
    }
  }
;
}
