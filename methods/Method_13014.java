protected HashSet<Container.Entry> getOuterEntries(Set<Container.Entry> matchingEntries){
  HashMap<Container.Entry,Container.Entry> innerTypeEntryToOuterTypeEntry=new HashMap<>();
  HashSet<Container.Entry> matchingOuterEntriesSet=new HashSet<>();
  for (  Container.Entry entry : matchingEntries) {
    TypeFactory typeFactory=TypeFactoryService.getInstance().get(entry);
    if (typeFactory != null) {
      Type type=typeFactory.make(api,entry,null);
      if ((type != null) && (type.getOuterName() != null)) {
        Container.Entry outerTypeEntry=innerTypeEntryToOuterTypeEntry.get(entry);
        if (outerTypeEntry == null) {
          HashMap<String,Container.Entry> typeNameToEntry=new HashMap<>();
          HashMap<String,String> innerTypeNameToOuterTypeName=new HashMap<>();
          for (          Container.Entry e : entry.getParent().getChildren()) {
            typeFactory=TypeFactoryService.getInstance().get(e);
            if (typeFactory != null) {
              type=typeFactory.make(api,e,null);
              if (type != null) {
                typeNameToEntry.put(type.getName(),e);
                if (type.getOuterName() != null) {
                  innerTypeNameToOuterTypeName.put(type.getName(),type.getOuterName());
                }
              }
            }
          }
          for (          Map.Entry<String,String> e : innerTypeNameToOuterTypeName.entrySet()) {
            Container.Entry innerTypeEntry=typeNameToEntry.get(e.getKey());
            if (innerTypeEntry != null) {
              String outerTypeName=e.getValue();
              for (; ; ) {
                String typeName=innerTypeNameToOuterTypeName.get(outerTypeName);
                if (typeName != null) {
                  outerTypeName=typeName;
                }
 else {
                  break;
                }
              }
              outerTypeEntry=typeNameToEntry.get(outerTypeName);
              if (outerTypeEntry != null) {
                innerTypeEntryToOuterTypeEntry.put(innerTypeEntry,outerTypeEntry);
              }
            }
          }
          outerTypeEntry=innerTypeEntryToOuterTypeEntry.get(entry);
          if (outerTypeEntry == null) {
            outerTypeEntry=entry;
          }
        }
        matchingOuterEntriesSet.add(outerTypeEntry);
      }
 else {
        matchingOuterEntriesSet.add(entry);
      }
    }
  }
  return matchingOuterEntriesSet;
}
