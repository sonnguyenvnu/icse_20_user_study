@Override protected Object deserialize(byte[] data,ClassLoader classLoader){
  try (InputStream bytes=new ByteArrayInputStream(data);ObjectInputStream input=new ClassLoaderAwareObjectInputStream(bytes,classLoader)){
    return input.readObject();
  }
 catch (  IOException e) {
    throw new CacheException("Failed to deserialize",e);
  }
catch (  ClassNotFoundException e) {
    throw new CacheException("Failed to resolve a deserialized class",e);
  }
}
