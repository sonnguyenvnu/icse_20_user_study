/** 
 * ?????????????? atom ???? POJO ????????????????
 * @param refer ????
 * @param atom ??????
 */
public static void run(Object refer,Runnable atom){
  if (null != atom) {
    if (log.isTraceEnabled())     log.tracef("TableName.run: [%s]->[%s]",object,object.get());
    Object old=get();
    set(refer);
    try {
      atom.run();
    }
 catch (    Exception e) {
      throw Lang.wrapThrow(e);
    }
 finally {
      set(old);
      if (log.isTraceEnabled())       log.tracef("TableName.finally: [%s]->[%s]",object,object.get());
    }
  }
}
