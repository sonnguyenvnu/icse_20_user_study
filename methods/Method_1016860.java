public static HierarchicalLDA read(File f) throws Exception {
  HierarchicalLDA topicModel;
  ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
  topicModel=(HierarchicalLDA)ois.readObject();
  ois.close();
  return topicModel;
}
