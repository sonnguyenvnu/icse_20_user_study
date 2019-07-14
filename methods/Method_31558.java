private static String getDriverInfo(DatabaseMetaData databaseMetaData){
  try {
    return databaseMetaData.getDriverName() + " " + databaseMetaData.getDriverVersion();
  }
 catch (  SQLException e) {
    throw new FlywaySqlException("Unable to read database driver info: " + e.getMessage(),e);
  }
}
