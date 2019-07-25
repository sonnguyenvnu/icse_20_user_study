public static SModelHeader create(int persistenceVersion){
  SModelHeader header=new SModelHeader();
  header.setPersistenceVersion(persistenceVersion);
  return header;
}
