/** 
 * scans all nodes in the scene and apply the css pseduoClass to them.
 * @param parent      stage parent node
 * @param pseudoClass css class for certain device
 */
private void scanAllNodes(Parent parent,PseudoClass pseudoClass){
  parent.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>(){
    @Override public void onChanged(    javafx.collections.ListChangeListener.Change<? extends Node> c){
      while (c.next()) {
        if (!c.wasPermutated() && !c.wasUpdated()) {
          for (          Node addedNode : c.getAddedSubList()) {
            if (addedNode instanceof Parent) {
              scanAllNodes((Parent)addedNode,pseudoClass);
            }
          }
        }
      }
    }
  }
);
  for (  Node component : parent.getChildrenUnmodifiable()) {
    if (component instanceof Pane) {
      ((Pane)component).getChildren().addListener(new ListChangeListener<Node>(){
        @Override public void onChanged(        javafx.collections.ListChangeListener.Change<? extends Node> c){
          while (c.next()) {
            if (!c.wasPermutated() && !c.wasUpdated()) {
              for (              Node addedNode : c.getAddedSubList()) {
                if (addedNode instanceof Parent) {
                  scanAllNodes((Parent)addedNode,pseudoClass);
                }
              }
            }
          }
        }
      }
);
      scanAllNodes((Pane)component,pseudoClass);
    }
 else     if (component instanceof ScrollPane) {
      ((ScrollPane)component).contentProperty().addListener((o,oldVal,newVal) -> {
        scanAllNodes((Parent)newVal,pseudoClass);
      }
);
      if (((ScrollPane)component).getContent() instanceof Parent) {
        scanAllNodes((Parent)((ScrollPane)component).getContent(),pseudoClass);
      }
    }
 else     if (component instanceof Control) {
      component.pseudoClassStateChanged(PSEUDO_CLASS_EX_SMALL,pseudoClass == PSEUDO_CLASS_EX_SMALL);
      component.pseudoClassStateChanged(PSEUDO_CLASS_SMALL,pseudoClass == PSEUDO_CLASS_SMALL);
      component.pseudoClassStateChanged(PSEUDO_CLASS_MEDIUM,pseudoClass == PSEUDO_CLASS_MEDIUM);
      component.pseudoClassStateChanged(PSEUDO_CLASS_LARGE,pseudoClass == PSEUDO_CLASS_LARGE);
    }
  }
}
