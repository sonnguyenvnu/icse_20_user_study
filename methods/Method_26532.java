/** 
 * Returns a  {@code String} representation of a sequence of statements, with semicolons andnewlines.
 */
private static String printStatements(Context context,Iterable<JCStatement> statements){
  StringWriter writer=new StringWriter();
  try {
    pretty(context,writer).printStats(com.sun.tools.javac.util.List.from(statements));
  }
 catch (  IOException e) {
    throw new AssertionError("StringWriter cannot throw IOExceptions");
  }
  return writer.toString();
}
