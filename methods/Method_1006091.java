private void populate(ObservableList<MenuItem> items,Collection<EntryType> types,BibEntry entry,UndoManager undoManager){
  for (  EntryType type : types) {
    items.add(createMenuItem(type,entry,undoManager));
  }
}
