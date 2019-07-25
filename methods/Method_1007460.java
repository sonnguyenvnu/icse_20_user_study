public static Object load(String name) throws Exception {
  FileInputStream fin=new FileInputStream(name);
  ObjectInputStream in=new ObjectInputStream(fin);
  Object obj=in.readObject();
  in.close();
  fin.close();
  return obj;
}
