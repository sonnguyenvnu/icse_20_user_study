private CoordinatorRepositoryAdapter readRecover(final File file){
  try {
    try (FileInputStream fis=new FileInputStream(file)){
      byte[] content=new byte[(int)file.length()];
      fis.read(content);
      return objectSerializer.deSerialize(content,CoordinatorRepositoryAdapter.class);
    }
   }
 catch (  Exception e) {
    e.printStackTrace();
    return null;
  }
}
