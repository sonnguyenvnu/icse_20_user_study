/** 
 * Returns a filename associated with this binding, for debug messages.
 * @return the filename associated with the type (if present)or the first definition (if present), otherwise a string describing what is known about the binding's source.
 */
public String getFirstFile(){
  Type bt=getType();
  if (bt instanceof ModuleType) {
    String file=bt.asModuleType().getFile();
    return file != null ? file : "<built-in module>";
  }
  if (defs != null) {
    for (    Def def : defs) {
      String file=def.getFile();
      if (file != null) {
        return file;
      }
    }
    return "<built-in binding>";
  }
  return "<unknown source>";
}
