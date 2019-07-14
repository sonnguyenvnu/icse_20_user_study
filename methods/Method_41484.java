private void setBeanProps(Object obj,Properties props) throws NoSuchMethodException, IllegalAccessException, java.lang.reflect.InvocationTargetException, IntrospectionException, SchedulerConfigException {
  props.remove("class");
  props.remove(PoolingConnectionProvider.POOLING_PROVIDER);
  BeanInfo bi=Introspector.getBeanInfo(obj.getClass());
  PropertyDescriptor[] propDescs=bi.getPropertyDescriptors();
  PropertiesParser pp=new PropertiesParser(props);
  java.util.Enumeration<Object> keys=props.keys();
  while (keys.hasMoreElements()) {
    String name=(String)keys.nextElement();
    String c=name.substring(0,1).toUpperCase(Locale.US);
    String methName="set" + c + name.substring(1);
    java.lang.reflect.Method setMeth=getSetMethod(methName,propDescs);
    try {
      if (setMeth == null) {
        throw new NoSuchMethodException("No setter for property '" + name + "'");
      }
      Class<?>[] params=setMeth.getParameterTypes();
      if (params.length != 1) {
        throw new NoSuchMethodException("No 1-argument setter for property '" + name + "'");
      }
      PropertiesParser refProps=pp;
      String refName=pp.getStringProperty(name);
      if (refName != null && refName.startsWith("$@")) {
        refName=refName.substring(2);
        refProps=cfg;
      }
 else       refName=name;
      if (params[0].equals(int.class)) {
        setMeth.invoke(obj,new Object[]{Integer.valueOf(refProps.getIntProperty(refName))});
      }
 else       if (params[0].equals(long.class)) {
        setMeth.invoke(obj,new Object[]{Long.valueOf(refProps.getLongProperty(refName))});
      }
 else       if (params[0].equals(float.class)) {
        setMeth.invoke(obj,new Object[]{Float.valueOf(refProps.getFloatProperty(refName))});
      }
 else       if (params[0].equals(double.class)) {
        setMeth.invoke(obj,new Object[]{Double.valueOf(refProps.getDoubleProperty(refName))});
      }
 else       if (params[0].equals(boolean.class)) {
        setMeth.invoke(obj,new Object[]{Boolean.valueOf(refProps.getBooleanProperty(refName))});
      }
 else       if (params[0].equals(String.class)) {
        setMeth.invoke(obj,new Object[]{refProps.getStringProperty(refName)});
      }
 else {
        throw new NoSuchMethodException("No primitive-type setter for property '" + name + "'");
      }
    }
 catch (    NumberFormatException nfe) {
      throw new SchedulerConfigException("Could not parse property '" + name + "' into correct data type: " + nfe.toString());
    }
  }
}
