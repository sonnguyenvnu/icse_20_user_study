/** 
 * Injects some target instance into  {@link jodd.proxetta.impl.WrapperProxetta wrapper} proxyin given  {@link WrapperProxettaFactory#setTargetFieldName(String) target field name}.
 */
public static void injectTargetIntoWrapper(final Object target,final Object wrapper,final String targetFieldName){
  try {
    final Field field=wrapper.getClass().getField(targetFieldName);
    field.setAccessible(true);
    field.set(wrapper,target);
  }
 catch (  Exception ex) {
    throw new ProxettaException(ex);
  }
}
