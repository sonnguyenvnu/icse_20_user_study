public String getUsername(){
  if (connection != null) {
    return connection.getCurrentUser();
  }
 else {
    return null;
  }
}
