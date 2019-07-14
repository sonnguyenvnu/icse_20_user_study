/** 
 * Checks whether XDB component is available or not.
 * @return {@code true} if it is available, {@code false} if not.
 * @throws SQLException when checking availability of the component failed.
 */
boolean isXmlDbAvailable() throws SQLException {
  return isDataDictViewAccessible("ALL_XML_TABLES");
}
