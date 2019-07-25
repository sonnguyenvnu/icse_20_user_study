public static void save(Object obj,String name) throws Exception {
  FileOutputStream fos=new FileOutputStream(name);
  ObjectOutputStream out=new ObjectOutputStream(fos);
  out.writeObject(obj);
  out.close();
  fos.close();
}
