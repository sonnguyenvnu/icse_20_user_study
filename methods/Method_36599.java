public List<NewsDO> read(String author) throws SQLException {
  try {
    return newManageDao.query(author);
  }
 catch (  SQLException ex) {
    System.err.println(ex.getMessage());
    throw ex;
  }
}
