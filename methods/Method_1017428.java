/** 
 * <p>Deep clone an  {@code Object} using serialization.</p><p>This is many times slower than writing clone methods by hand on all objects in your object graph. However, for complex object graphs, or for those that don't support deep cloning this can be a simple alternative implementation. Of course all the objects must be  {@code Serializable}.</p>
 * @param < T > the type of the object involved
 * @param object  the {@code Serializable} object to clone
 * @return the cloned object
 * @throws SerializationException (runtime) if the serialization fails
 */
public static <T extends Serializable>T clone(final T object){
  if (object == null) {
    return null;
  }
  final byte[] objectData=serialize(object);
  final ByteArrayInputStream bais=new ByteArrayInputStream(objectData);
  ClassLoaderAwareObjectInputStream in=null;
  try {
    in=new ClassLoaderAwareObjectInputStream(bais,object.getClass().getClassLoader());
    @SuppressWarnings("unchecked") final T readObject=(T)in.readObject();
    return readObject;
  }
 catch (  final ClassNotFoundException ex) {
    throw new RuntimeException("ClassNotFoundException while reading cloned object data",ex);
  }
catch (  final IOException ex) {
    throw new RuntimeException("IOException while reading cloned object data",ex);
  }
 finally {
    try {
      if (in != null) {
        in.close();
      }
    }
 catch (    final IOException ex) {
      throw new RuntimeException("IOException on closing cloned object data InputStream.",ex);
    }
  }
}
