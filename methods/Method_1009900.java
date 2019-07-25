public synchronized Object invoke(String operation,Object[] paramVals,String[] signature) throws MBeanException, ReflectionException {
  try {
    int slen=signature.length;
    Class[] paramTypes=new Class[slen];
    for (int i=0; i < slen; ++i)     paramTypes[i]=ClassUtils.forName(signature[i]);
    Method m=pds.getClass().getMethod(operation,paramTypes);
    return m.invoke(pds,paramVals);
  }
 catch (  NoSuchMethodException e) {
    try {
      boolean two=false;
      if (signature.length == 0 && (operation.startsWith("get") || (two=operation.startsWith("is")))) {
        int i=two ? 2 : 3;
        String attr=Character.toLowerCase(operation.charAt(i)) + operation.substring(i + 1);
        return getAttribute(attr);
      }
 else       if (signature.length == 1 && operation.startsWith("set")) {
        setAttribute(new Attribute(Character.toLowerCase(operation.charAt(3)) + operation.substring(4),paramVals[0]));
        return null;
      }
 else       throw new MBeanException(e);
    }
 catch (    Exception e2) {
      throw new MBeanException(e2);
    }
  }
catch (  Exception e) {
    throw new MBeanException(e);
  }
}
