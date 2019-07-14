private void releaseConnection(Jedis connection){
  if (connection != null) {
    connection.close();
  }
}
