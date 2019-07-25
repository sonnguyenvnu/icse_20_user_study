private static void convert(Object dest,Object orig) throws IllegalAccessException, InvocationTargetException {
  if (dest == null) {
    throw new IllegalArgumentException("No destination bean specified");
  }
  if (orig == null) {
    throw new IllegalArgumentException("No origin bean specified");
  }
  if (orig instanceof DynaBean) {
    DynaProperty origDescriptors[]=((DynaBean)orig).getDynaClass().getDynaProperties();
    for (int i=0; i < origDescriptors.length; i++) {
      String name=origDescriptors[i].getName();
      if (PropertyUtils.isWriteable(dest,name)) {
        Object value=((DynaBean)orig).get(name);
        try {
          getInstance().setSimpleProperty(dest,name,value);
        }
 catch (        Exception e) {
          ;
        }
      }
    }
  }
 else   if (orig instanceof Map) {
    Iterator names=((Map)orig).keySet().iterator();
    while (names.hasNext()) {
      String name=(String)names.next();
      if (PropertyUtils.isWriteable(dest,name)) {
        Object value=((Map)orig).get(name);
        try {
          getInstance().setSimpleProperty(dest,name,value);
        }
 catch (        Exception e) {
          ;
        }
      }
    }
  }
 else {
    PropertyDescriptor origDescriptors[]=PropertyUtils.getPropertyDescriptors(orig);
    for (int i=0; i < origDescriptors.length; i++) {
      String name=origDescriptors[i].getName();
      if ("class".equals(name)) {
        continue;
      }
      if (PropertyUtils.isReadable(orig,name) && PropertyUtils.isWriteable(dest,name)) {
        try {
          Object value=PropertyUtils.getSimpleProperty(orig,name);
          getInstance().setSimpleProperty(dest,name,value);
        }
 catch (        java.lang.IllegalArgumentException ie) {
          ;
        }
catch (        Exception e) {
          ;
        }
      }
    }
  }
}
