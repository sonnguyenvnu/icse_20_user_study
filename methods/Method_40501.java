/** 
 * Create an outline for a file in the index.
 * @param scope the file scope
 * @param path the file for which to build the outline
 * @return a list of entries constituting the file outline.Returns an empty list if the indexer hasn't indexed that path.
 */
public List<Entry> generate(Indexer idx,String abspath) throws Exception {
  ModuleType mt=idx.getModuleForFile(abspath);
  if (mt == null) {
    return new ArrayList<Entry>();
  }
  return generate(mt.getTable(),abspath);
}
