/** 
 * Appends a new entry.
 * @param inner     <code>inner_class_info_index</code>
 * @param outer     <code>outer_class_info_index</code>
 * @param name      <code>inner_name_index</code>
 * @param flags     <code>inner_class_access_flags</code>
 */
public void append(String inner,String outer,String name,int flags){
  int i=constPool.addClassInfo(inner);
  int o=constPool.addClassInfo(outer);
  int n=constPool.addUtf8Info(name);
  append(i,o,n,flags);
}
