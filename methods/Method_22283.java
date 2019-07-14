@Nullable public static String serialize(@NonNull Serializable serializable){
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  try (ObjectOutputStream outputStream=new ObjectOutputStream(out)){
    outputStream.writeObject(serializable);
    return Base64.encodeToString(out.toByteArray(),Base64.DEFAULT);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
