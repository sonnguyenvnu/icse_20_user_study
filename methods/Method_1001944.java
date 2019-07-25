public static String etag(){
  return Integer.toString(version() % MAJOR_SEPARATOR - 201670,36) + Integer.toString(beta(),36);
}
