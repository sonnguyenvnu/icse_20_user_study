/** 
 * @return The installation directory of the Flyway Command-line tool.
 */
@SuppressWarnings("ConstantConditions") private static String getInstallationDir(){
  String path=ClassUtils.getLocationOnDisk(Main.class);
  return new File(path).getParentFile().getParentFile().getParentFile().getAbsolutePath();
}
