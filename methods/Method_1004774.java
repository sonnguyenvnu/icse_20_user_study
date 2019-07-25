/** 
 * Parse an aliases response into an instance of  {@link IndicesAliases}Example of response from server: <pre> { "index1" : { "aliases" : { "alias1" : { "filter" : { "term" : { "user" : "kimchy" } }, "index_routing" : "1", "search_routing" : "1", "is_write_index" : true }, "alias2" : { "search_routing" : "5" } } }, "index2" : { "aliases" : { ... } } } </pre>
 * @param resp JSON Response in the form of a Java Map
 */
public static IndicesAliases parse(Map<String,Object> resp){
  final Map<String,Map<String,Alias>> indices=new HashMap<String,Map<String,Alias>>();
  for (  Map.Entry<String,Object> index : resp.entrySet()) {
    final Map<String,Object> metadata=(Map<String,Object>)index.getValue();
    final Map<String,Map<String,Object>> aliases=(Map<String,Map<String,Object>>)metadata.get("aliases");
    final Map<String,Alias> indexAliases=new HashMap<String,Alias>();
    indices.put(index.getKey(),indexAliases);
    for (    Map.Entry<String,Map<String,Object>> entry : aliases.entrySet()) {
      String name=entry.getKey();
      Map<String,Object> aliasMetadata=entry.getValue();
      String searchRouting=null;
      String indexRouting=null;
      Map<String,Object> filter=null;
      boolean isWriteIndex=false;
      if (aliasMetadata.containsKey("search_routing")) {
        searchRouting=(String)aliasMetadata.get("search_routing");
      }
      if (aliasMetadata.containsKey("index_routing")) {
        indexRouting=(String)aliasMetadata.get("index_routing");
      }
      if (aliasMetadata.containsKey("filter")) {
        filter=(Map<String,Object>)aliasMetadata.get("filter");
      }
      if (aliasMetadata.containsKey("is_write_index")) {
        isWriteIndex=(Boolean)aliasMetadata.get("is_write_index");
      }
      Alias alias=new Alias(name,searchRouting,indexRouting,filter,isWriteIndex);
      indexAliases.put(alias.name,alias);
    }
  }
  return new IndicesAliases(Collections.unmodifiableMap(indices));
}
