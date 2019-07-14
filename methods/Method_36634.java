public static Object newProxyInstance() throws Exception {
  String rt="\n\r";
  String src="package proxy.advance.one;" + rt + "public class TankTimeProxy implements Movable {" + rt + "      private Movable tank;" + rt + "      public TankTimeProxy(Movable tank) {" + rt + "             this.tank = tank;" + rt + "      }" + rt + "     @Override" + rt + "     public void move() {" + rt + "          long start = System.currentTimeMillis();" + rt + "          System.out.println(\"start time : \" + start);" + rt + "          tank.move();" + rt + "          long end = System.currentTimeMillis();" + rt + "          System.out.println(\"end time : \" + end);" + rt + "          System.out.println(\"spend all time : \" + (end - start)/1000 + \"s.\");" + rt + "      }" + rt + "}";
  File file=new File("/home/zxzxin/Java_Maven/DesignPatterns/src/main/java/proxy/advance/one/TankTimeProxy.java");
  FileWriter fw=new FileWriter(file);
  fw.write(src);
  fw.flush();
  fw.close();
  JavaCompiler jc=ToolProvider.getSystemJavaCompiler();
  StandardJavaFileManager fileMgr=jc.getStandardFileManager(null,null,null);
  Iterable units=fileMgr.getJavaFileObjects(file);
  JavaCompiler.CompilationTask t=jc.getTask(null,fileMgr,null,null,null,units);
  t.call();
  fileMgr.close();
  URL[] urls=new URL[]{new URL("file:/" + "home/zxzxin/Java_Maven/DesignPatterns/src/main/java/")};
  URLClassLoader ul=new URLClassLoader(urls);
  Class c=ul.loadClass("proxy.advance.one.TankTimeProxy");
  Constructor ctr=c.getConstructor(Movable.class);
  return ctr.newInstance(new Tank());
}
