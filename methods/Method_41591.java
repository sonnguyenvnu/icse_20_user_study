protected void setBeanProps(Object obj,JobDataMap data) throws SchedulerException {
  BeanInfo bi=null;
  try {
    bi=Introspector.getBeanInfo(obj.getClass());
  }
 catch (  IntrospectionException e) {
    handleError("Unable to introspect Job class.",e);
  }
  PropertyDescriptor[] propDescs=bi.getPropertyDescriptors();
  for (Iterator<?> entryIter=data.getWrappedMap().entrySet().iterator(); entryIter.hasNext(); ) {
    Map.Entry<?,?> entry=(Map.Entry<?,?>)entryIter.next();
    String name=(String)entry.getKey();
    String c=name.substring(0,1).toUpperCase(Locale.US);
    String methName="set" + c + name.substring(1);
    java.lang.reflect.Method setMeth=getSetMethod(methName,propDescs);
    Class<?> paramType=null;
    Object o=null;
    try {
      if (setMeth == null) {
        handleError("No setter on Job class " + obj.getClass().getName() + " for property '" + name + "'");
        continue;
      }
      paramType=setMeth.getParameterTypes()[0];
      o=entry.getValue();
      Object parm=null;
      if (paramType.isPrimitive()) {
        if (o == null) {
          handleError("Cannot set primitive property '" + name + "' on Job class " + obj.getClass().getName() + " to null.");
          continue;
        }
        if (paramType.equals(int.class)) {
          if (o instanceof String) {
            parm=Integer.valueOf((String)o);
          }
 else           if (o instanceof Integer) {
            parm=o;
          }
        }
 else         if (paramType.equals(long.class)) {
          if (o instanceof String) {
            parm=Long.valueOf((String)o);
          }
 else           if (o instanceof Long) {
            parm=o;
          }
        }
 else         if (paramType.equals(float.class)) {
          if (o instanceof String) {
            parm=Float.valueOf((String)o);
          }
 else           if (o instanceof Float) {
            parm=o;
          }
        }
 else         if (paramType.equals(double.class)) {
          if (o instanceof String) {
            parm=Double.valueOf((String)o);
          }
 else           if (o instanceof Double) {
            parm=o;
          }
        }
 else         if (paramType.equals(boolean.class)) {
          if (o instanceof String) {
            parm=Boolean.valueOf((String)o);
          }
 else           if (o instanceof Boolean) {
            parm=o;
          }
        }
 else         if (paramType.equals(byte.class)) {
          if (o instanceof String) {
            parm=Byte.valueOf((String)o);
          }
 else           if (o instanceof Byte) {
            parm=o;
          }
        }
 else         if (paramType.equals(short.class)) {
          if (o instanceof String) {
            parm=Short.valueOf((String)o);
          }
 else           if (o instanceof Short) {
            parm=o;
          }
        }
 else         if (paramType.equals(char.class)) {
          if (o instanceof String) {
            String str=(String)o;
            if (str.length() == 1) {
              parm=Character.valueOf(str.charAt(0));
            }
          }
 else           if (o instanceof Character) {
            parm=o;
          }
        }
      }
 else       if ((o != null) && (paramType.isAssignableFrom(o.getClass()))) {
        parm=o;
      }
      if ((o != null) && (parm == null)) {
        handleError("The setter on Job class " + obj.getClass().getName() + " for property '" + name + "' expects a " + paramType + " but was given " + o.getClass().getName());
        continue;
      }
      setMeth.invoke(obj,new Object[]{parm});
    }
 catch (    NumberFormatException nfe) {
      handleError("The setter on Job class " + obj.getClass().getName() + " for property '" + name + "' expects a " + paramType + " but was given " + o.getClass().getName(),nfe);
    }
catch (    IllegalArgumentException e) {
      handleError("The setter on Job class " + obj.getClass().getName() + " for property '" + name + "' expects a " + paramType + " but was given " + o.getClass().getName(),e);
    }
catch (    IllegalAccessException e) {
      handleError("The setter on Job class " + obj.getClass().getName() + " for property '" + name + "' could not be accessed.",e);
    }
catch (    InvocationTargetException e) {
      handleError("The setter on Job class " + obj.getClass().getName() + " for property '" + name + "' could not be invoked.",e);
    }
  }
}
