/** 
 * Reports a failed module or submodule resolution.
 * @param qname module qname, e.g. "org.foo.bar"
 * @param file the file where the unresolved import occurred
 */
public void recordUnresolvedModule(String qname,String file){
  Set<String> importers=unresolvedModules.get(qname);
  if (importers == null) {
    importers=new TreeSet<String>();
    unresolvedModules.put(qname,importers);
  }
  importers.add(file);
}
