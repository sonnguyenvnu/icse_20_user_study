@Override public boolean upload(String fileName,String text){
  return doExecute(Unchecked.function(client -> {
    try (ByteArrayInputStream inputStream=new ByteArrayInputStream(text.getBytes())){
      return client.storeFile(fileName,inputStream);
    }
   }
));
}
