private boolean find(boolean wrap,boolean backwards){
  String searchTerm=findField.getText();
  if (searchTerm.length() != 0) {
    String text=editor.getText();
    Sketch sketch=editor.getSketch();
    int tabIndex=sketch.getCurrentCodeIndex();
    if (ignoreCase) {
      searchTerm=searchTerm.toLowerCase();
      text=text.toLowerCase();
    }
    int nextIndex;
    if (!backwards) {
      int selectionEnd=editor.getSelectionStop();
      nextIndex=text.indexOf(searchTerm,selectionEnd);
      if (nextIndex == -1 && wrap && !allTabs) {
        nextIndex=text.indexOf(searchTerm,0);
      }
 else       if (nextIndex == -1 && allTabs) {
        int tempIndex=tabIndex;
        while (tabIndex <= sketch.getCodeCount() - 1) {
          if (tabIndex == sketch.getCodeCount() - 1) {
            tabIndex=-1;
          }
 else           if (tabIndex == sketch.getCodeCount() - 1) {
            break;
          }
          try {
            Document doc=sketch.getCode(tabIndex + 1).getDocument();
            if (doc != null) {
              text=doc.getText(0,doc.getLength());
            }
 else {
              text=sketch.getCode(tabIndex + 1).getProgram();
            }
          }
 catch (          BadLocationException e) {
            e.printStackTrace();
          }
          tabIndex++;
          if (ignoreCase) {
            text=text.toLowerCase();
          }
          nextIndex=text.indexOf(searchTerm,0);
          if (nextIndex != -1 || tabIndex == tempIndex) {
            break;
          }
        }
        if (nextIndex == -1) {
          tabIndex=tempIndex;
        }
      }
    }
 else {
      int selectionStart=editor.getSelectionStart() - 1;
      if (selectionStart >= 0) {
        nextIndex=text.lastIndexOf(searchTerm,selectionStart);
      }
 else {
        nextIndex=-1;
      }
      if (wrap && !allTabs && nextIndex == -1) {
        nextIndex=text.lastIndexOf(searchTerm);
      }
 else       if (nextIndex == -1 && allTabs) {
        int tempIndex=tabIndex;
        while (tabIndex >= 0) {
          if (tabIndex == 0) {
            tabIndex=sketch.getCodeCount();
          }
 else           if (tabIndex == 0) {
            break;
          }
          try {
            Document doc=sketch.getCode(tabIndex - 1).getDocument();
            if (doc != null) {
              text=doc.getText(0,doc.getLength());
            }
 else {
              text=sketch.getCode(tabIndex - 1).getProgram();
            }
          }
 catch (          BadLocationException e) {
            e.printStackTrace();
          }
          tabIndex--;
          if (ignoreCase) {
            text=text.toLowerCase();
          }
          nextIndex=text.lastIndexOf(searchTerm);
          if (nextIndex != -1 || tabIndex == tempIndex) {
            break;
          }
        }
        if (nextIndex == -1) {
          tabIndex=tempIndex;
        }
      }
    }
    if (nextIndex != -1) {
      if (allTabs) {
        sketch.setCurrentCode(tabIndex);
      }
      editor.setSelection(nextIndex,nextIndex + searchTerm.length());
    }
 else {
    }
    if (nextIndex != -1) {
      setFound(true);
      return true;
    }
  }
  setFound(false);
  return false;
}
