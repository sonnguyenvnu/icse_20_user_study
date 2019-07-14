public void clearSelections(){
  if (getData().isEmpty())   return;
  for (  FileWrapper wrapper : getData()) {
    wrapper.selected=false;
  }
}
