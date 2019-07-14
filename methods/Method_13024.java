protected Collection<Container.Entry> getOuterEntries(Collection<Container.Entry> entries){
  HashMap<Container.Entry,Container.Entry> innerTypeEntryToOuterTypeEntry=new HashMap<>();
  HashSet<Container.Entry> outerEntriesSet=new HashSet<>();
  for (  Container.Entry entry : entries) {
    Container.Entry outerTypeEntry=null;
    TypeFactory factory=TypeFactoryService.getInstance().get(entry);
    if (factory != null) {
      Type type=factory.make(api,entry,null);
      if ((type != null) && (type.getOuterName() != null)) {
        outerTypeEntry=innerTypeEntryToOuterTypeEntry.get(entry);
        if (outerTypeEntry == null) {
          HashMap<String,Container.Entry> typeNameToEntry=new HashMap<>();
          HashMap<String,String> innerTypeNameToOuterTypeName=new HashMap<>();
          for (          Container.Entry e : entry.getParent().getChildren()) {
            factory=TypeFactoryService.getInstance().get(e);
            if (factory != null) {
              type=factory.make(api,e,null);
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
        }
      }
    }
    if (outerTypeEntry != null) {
      outerEntriesSet.add(outerTypeEntry);
    }
 else {
      outerEntriesSet.add(entry);
    }
  }
  ArrayList<Container.Entry> result=new ArrayList<>(outerEntriesSet);
  result.sort(CONTAINER_ENTRY_COMPARATOR);
  return result;
}
