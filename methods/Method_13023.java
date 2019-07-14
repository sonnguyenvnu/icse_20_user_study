@SuppressWarnings("unchecked") public void show(Point location,Collection<Container.Entry> entries,Consumer<Container.Entry> selectedLocationCallback,Runnable closeCallback){
  HashMap<Container,ArrayList<Container.Entry>> map=new HashMap<>();
  for (  Container.Entry entry : entries) {
    Container container=entry.getContainer();
    while (true) {
      Container parentContainer=container.getRoot().getParent().getContainer();
      if (parentContainer.getRoot() == null) {
        break;
      }
 else {
        container=parentContainer;
      }
    }
    ArrayList<Container.Entry> list=map.get(container);
    if (list == null) {
      map.put(container,list=new ArrayList<>());
    }
    list.add(entry);
  }
  HashSet<DelegatingFilterContainer> delegatingFilterContainers=new HashSet<>();
  for (  Map.Entry<Container,ArrayList<Container.Entry>> mapEntry : map.entrySet()) {
    Container container=mapEntry.getKey();
    delegatingFilterContainers.add(new DelegatingFilterContainer(container,getOuterEntries(mapEntry.getValue())));
  }
  Consumer<URI> selectedEntryCallback=uri -> onLocationSelected(delegatingFilterContainers,uri,selectedLocationCallback);
  selectLocationView.show(location,delegatingFilterContainers,entries.size(),selectedEntryCallback,closeCallback);
}
