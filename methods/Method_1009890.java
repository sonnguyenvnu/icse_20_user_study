public void generate(ClassInfo info,Class superclassType,Property[] props,Class[] propTypes,IndentedWriter iw) throws IOException {
  iw.println("// JDK7 add-on");
  iw.println("public Logger getParentLogger() throws SQLFeatureNotSupportedException");
  iw.println("{ throw new SQLFeatureNotSupportedException(\042javax.sql.DataSource.getParentLogger() is not currently supported by \042 + this.getClass().getName());}");
}
