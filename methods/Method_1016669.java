private void javaexec(final String[] inArgs){
  final String obj=inArgs[0];
  final String[] args=new String[inArgs.length - 1];
  System.arraycopy(inArgs,1,args,0,inArgs.length - 1);
  final Object[] argList=new Object[1];
  argList[0]=args;
  final Properties pr=System.getProperties();
  final String origPath=(String)pr.get("java.class.path");
  try {
    pr.put("user.dir",this.currentLocalPath.toString());
    System.setProperties(pr);
    final Class<?> c=(new cl()).loadClass(obj);
    final Class<?>[] parameterType=(Class<?>[])Array.newInstance(Class.class,1);
    parameterType[0]=Class.forName("[Ljava.lang.String;");
    Method m=c.getMethod("main",parameterType);
    final Object result=m.invoke(null,argList);
    m=null;
    if (result != null) {
      Data.logger.info("returns " + result);
    }
    this.currentLocalPath=new File((String)pr.get("user.dir"));
  }
 catch (  final ClassNotFoundException e) {
    Data.logger.warn("Command '" + obj + "' not supported. Try 'HELP'.");
  }
catch (  final NoSuchMethodException e) {
    Data.logger.warn("no \"public static main(String args[])\" in " + obj);
  }
catch (  final InvocationTargetException e) {
    final Throwable orig=e.getTargetException();
    if (orig.getMessage() != null) {
      Data.logger.warn("Exception from " + obj + ": " + orig.getMessage(),orig);
    }
  }
catch (  final IllegalAccessException e) {
    Data.logger.warn("Illegal access for " + obj + ": class is probably not declared as public",e);
  }
catch (  final NullPointerException e) {
    Data.logger.warn("main(String args[]) is not defined as static for " + obj);
  }
catch (  final Exception e) {
    Data.logger.warn("Exception caught: ",e);
  }
  pr.put("java.class.path",origPath);
}
