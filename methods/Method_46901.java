private ArrayList<LayoutElementParcelable> listRecent(){
  UtilsHandler utilsHandler=new UtilsHandler(c);
  final LinkedList<String> paths=utilsHandler.getHistoryLinkedList();
  ArrayList<LayoutElementParcelable> songs=new ArrayList<>();
  for (  String f : paths) {
    if (!f.equals("/")) {
      HybridFileParcelable hybridFileParcelable=RootHelper.generateBaseFile(new File(f),showHiddenFiles);
      if (hybridFileParcelable != null) {
        hybridFileParcelable.generateMode(ma.getActivity());
        if (!hybridFileParcelable.isSmb() && !hybridFileParcelable.isDirectory() && hybridFileParcelable.exists()) {
          LayoutElementParcelable parcelable=createListParcelables(hybridFileParcelable);
          if (parcelable != null)           songs.add(parcelable);
        }
      }
    }
  }
  return songs;
}
