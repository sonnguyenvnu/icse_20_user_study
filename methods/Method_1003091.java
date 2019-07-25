/** 
 * Initializes full text search functionality for this database. This adds the following Java functions to the database: <ul> <li>FTL_CREATE_INDEX(schemaNameString, tableNameString, columnListString)</li> <li>FTL_SEARCH(queryString, limitInt, offsetInt): result set</li> <li>FTL_REINDEX()</li> <li>FTL_DROP_ALL()</li> </ul> It also adds a schema FTL to the database where bookkeeping information is stored. This function may be called from a Java application, or by using the SQL statements: <pre> CREATE ALIAS IF NOT EXISTS FTL_INIT FOR &quot;org.h2.fulltext.FullTextLucene.init&quot;; CALL FTL_INIT(); </pre>
 * @param conn the connection
 */
public static void init(Connection conn) throws SQLException {
  try (Statement stat=conn.createStatement()){
    stat.execute("CREATE SCHEMA IF NOT EXISTS " + SCHEMA);
    stat.execute("CREATE TABLE IF NOT EXISTS " + SCHEMA + ".INDEXES(SCHEMA VARCHAR, `TABLE` VARCHAR, " + "COLUMNS VARCHAR, PRIMARY KEY(SCHEMA, `TABLE`))");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_CREATE_INDEX FOR \"" + FullTextLucene.class.getName() + ".createIndex\"");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_DROP_INDEX FOR \"" + FullTextLucene.class.getName() + ".dropIndex\"");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_SEARCH FOR \"" + FullTextLucene.class.getName() + ".search\"");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_SEARCH_DATA FOR \"" + FullTextLucene.class.getName() + ".searchData\"");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_REINDEX FOR \"" + FullTextLucene.class.getName() + ".reindex\"");
    stat.execute("CREATE ALIAS IF NOT EXISTS FTL_DROP_ALL FOR \"" + FullTextLucene.class.getName() + ".dropAll\"");
  }
 }
