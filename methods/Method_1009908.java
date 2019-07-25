/** 
 * Creates a  {@link SimpleLogRecord} in the case of an error during logging. 
 */
public static SimpleLogRecord error(RuntimeException error,LogData data){
  return new SimpleLogRecord(error,data);
}
