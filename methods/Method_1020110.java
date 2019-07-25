/** 
 * ??DB????
 * @param resultSet
 * @param statement
 * @param connection
 */
public static void release(ResultSet resultSet,Statement statement,Connection connection){
  if (resultSet != null) {
    try {
      resultSet.close();
    }
 catch (    SQLException e) {
      e.printStackTrace();
    }
  }
  if (statement != null) {
    try {
      statement.close();
    }
 catch (    SQLException e) {
      e.printStackTrace();
    }
  }
  if (connection != null) {
    try {
      connection.close();
    }
 catch (    SQLException e) {
      e.printStackTrace();
    }
  }
}
