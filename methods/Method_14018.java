/** 
 * This should only be used when creating a new item. This ensures that we never add an alias without adding a label in the same language.
 */
public ItemUpdate normalizeLabelsAndAliases(){
  Set<MonolingualTextValue> filteredAliases=new HashSet<>();
  Map<String,MonolingualTextValue> newLabels=new HashMap<>(labelsIfNew);
  newLabels.putAll(labels);
  for (  MonolingualTextValue alias : getAliases()) {
    if (!newLabels.containsKey(alias.getLanguageCode())) {
      newLabels.put(alias.getLanguageCode(),alias);
    }
 else {
      filteredAliases.add(alias);
    }
  }
  Map<String,MonolingualTextValue> newDescriptions=new HashMap<>(descriptionsIfNew);
  newDescriptions.putAll(descriptions);
  return new ItemUpdate(qid,addedStatements,deletedStatements,newLabels,Collections.emptyMap(),newDescriptions,Collections.emptyMap(),constructTermListMap(filteredAliases));
}
