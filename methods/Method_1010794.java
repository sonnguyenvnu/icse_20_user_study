private void highlight(final List<SearchEntry> searchEntries){
  final SRepository editorRepo=myEditor.getEditorContext().getRepository();
  editorRepo.getModelAccess().runReadAction(() -> {
    myHighlightManager=myEditor.getHighlightManager();
    List<EditorMessage> messages=new ArrayList<>();
    Map<EditorCell_Label,List<Pair>> cellToPositions=new LinkedHashMap<>();
    for (    SearchEntry searchEntry : searchEntries) {
      for (      TextRange range : searchEntry.getRangesIterator()) {
        if (!cellToPositions.containsKey(range.getLabel())) {
          cellToPositions.put(range.getLabel(),new ArrayList<>());
        }
        cellToPositions.get(range.getLabel()).add(new Pair(range.getStartPosition(),range.getEndPosition()));
      }
    }
    for (    EditorCell_Label cell : cellToPositions.keySet()) {
      messages.add(new SearchPanelEditorMessage(cell,cellToPositions.get(cell)));
    }
    myHighlightManager.mark(messages);
  }
);
}
