/** 
 * Returns the list of schemas that were created and are maintained by Oracle-supplied scripts and must not be changed in any other way. The list is composed of default schemas mentioned in the official documentation for Oracle Database versions from 10.1 to 12.2, and is dynamically extended with schemas from DBA_REGISTRY and ALL_USERS (marked with ORACLE_MAINTAINED = 'Y' in Oracle 12c).
 * @return the set of system schema names
 */
Set<String> getSystemSchemas() throws SQLException {
  Set<String> result=new HashSet<>(Arrays.asList("SYS","SYSTEM","SYSBACKUP","SYSDG","SYSKM","SYSRAC","SYS$UMF","DBSNMP","MGMT_VIEW","SYSMAN","OUTLN","AUDSYS","ORACLE_OCM","APPQOSSYS","OJVMSYS","DVF","DVSYS","DBSFWUSER","REMOTE_SCHEDULER_AGENT","DIP","APEX_PUBLIC_USER","FLOWS_FILES","ANONYMOUS","XDB","XS$NULL","CTXSYS","LBACSYS","EXFSYS","MDDATA","MDSYS","SPATIAL_CSW_ADMIN_USR","SPATIAL_WFS_ADMIN_USR","ORDDATA","ORDPLUGINS","ORDSYS","SI_INFORMTN_SCHEMA","WMSYS","OLAPSYS","OWBSYS","OWBSYS_AUDIT","GSMADMIN_INTERNAL","GSMCATUSER","GSMUSER","GGSYS","WK_TEST","WKSYS","WKPROXY","ODM","ODM_MTR","DMSYS","TSMSYS"));
  result.addAll(getMainConnection().getJdbcTemplate().queryForStringList("SELECT USERNAME FROM ALL_USERS " + "WHERE REGEXP_LIKE(USERNAME, '^(APEX|FLOWS)_\\d+$')" + " OR ORACLE_MAINTAINED = 'Y'"));
  return result;
}
