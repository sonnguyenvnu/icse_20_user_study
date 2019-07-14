/** 
 * Checks whether Oracle Locator component is available or not.
 * @return {@code true} if it is available, {@code false} if not.
 * @throws SQLException when checking availability of the component failed.
 */
boolean isLocatorAvailable() throws SQLException {
  return isDataDictViewAccessible("MDSYS","ALL_SDO_GEOM_METADATA");
}
