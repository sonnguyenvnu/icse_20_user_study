/** 
 * Your Flyway license key (FL01...). Not yet a Flyway Pro or Enterprise Edition customer? Request your <a href="https://flywaydb.org/download/">Flyway trial license key</a> to try out Flyway Pro and Enterprise Edition features free for 30 days. <p><i>Flyway Pro and Flyway Enterprise only</i></p>
 * @param licenseKey Your Flyway license key.
 */
public void setLicenseKey(String licenseKey){
  throw new org.flywaydb.core.internal.license.FlywayProUpgradeRequiredException("licenseKey");
}
