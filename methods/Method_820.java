public static boolean isProxy(Class<?> clazz){
  for (  Class<?> item : clazz.getInterfaces()) {
    String interfaceName=item.getName();
    if (interfaceName.equals("net.sf.cglib.proxy.Factory") || interfaceName.equals("org.springframework.cglib.proxy.Factory")) {
      return true;
    }
    if (interfaceName.equals("javassist.util.proxy.ProxyObject") || interfaceName.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject")) {
      return true;
    }
    if (interfaceName.equals("org.hibernate.proxy.HibernateProxy")) {
      return true;
    }
  }
  return false;
}
