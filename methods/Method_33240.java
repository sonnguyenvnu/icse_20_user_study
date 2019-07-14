private void setupEditor(){
  editor=new FakeFocusTextArea();
  editor.setManaged(false);
  editor.getStyleClass().add("editor");
  editor.setWrapText(true);
  editor.addEventFilter(KeyEvent.KEY_RELEASED,event -> {
    if (event.getCode() != KeyCode.ENTER) {
      getSkinnable().pseudoClassStateChanged(PSEUDO_CLASS_ERROR,false);
    }
  }
);
  editor.addEventFilter(KeyEvent.KEY_PRESSED,event -> {
switch (event.getCode()) {
case ENTER:
      if (!editor.getText().trim().isEmpty()) {
        try {
          final StringConverter<T> sc=control.getConverter();
          final T item=sc.fromString(editor.getText());
          if (item != null) {
            getSkinnable().getChips().add(item);
          }
          editor.clear();
          autoCompletePopup.hide();
        }
 catch (        Exception ex) {
          getSkinnable().pseudoClassStateChanged(PSEUDO_CLASS_ERROR,true);
        }
      }
    event.consume();
  break;
case TAB:
if (editor.getText().trim().isEmpty()) {
  if (event.isShiftDown()) {
    getBehavior().traverse(getSkinnable(),Direction.PREVIOUS);
  }
 else {
    getBehavior().traverse(editor,Direction.NEXT);
  }
}
event.consume();
break;
case BACK_SPACE:
ObservableList<T> chips=getSkinnable().getChips();
int size=chips.size();
if ((size > 0) && editor.getText().isEmpty()) {
chips.remove(size - 1);
if (autoCompletePopup.isShowing()) {
autoCompletePopup.hide();
}
}
break;
case SPACE:
if (event.isControlDown()) {
if (!autoCompletePopup.getFilteredSuggestions().isEmpty()) {
autoCompletePopup.show(editor);
}
}
break;
}
}
);
editor.textProperty().addListener(observable -> {
requiredWidth=editor.snappedLeftInset() + computeTextContentWidth(editor) + editor.snappedRightInset() + 13;
if (availableWidth < requiredWidth && !editorOnNewLine && !moveToNewLine) {
moveToNewLine=true;
root.requestLayout();
}
 else if (availableWidth > requiredWidth && editorOnNewLine && moveToNewLine) {
moveToNewLine=false;
root.requestLayout();
}
autoCompletePopup.filter(item -> getSkinnable().getPredicate().test(item,editor.getText()));
if (autoCompletePopup.getFilteredSuggestions().isEmpty()) {
autoCompletePopup.hide();
}
 else {
autoCompletePopup.show(editor);
}
}
);
editor.promptTextProperty().bind(control.promptTextProperty());
root.getChildren().add(editor);
control.focusedProperty().addListener((obj,oldVal,newVal) -> {
if (editor != null) {
editor.setFakeFocus(newVal);
}
}
);
control.addEventFilter(KeyEvent.ANY,ke -> {
if (editor != null) {
if (ke.getTarget().equals(editor)) {
return;
}
if (ke.getTarget().equals(control)) {
switch (ke.getCode()) {
case ESCAPE:
case F10:
break;
default :
editor.fireEvent(ke.copyFor(editor,editor));
ke.consume();
}
}
}
}
);
}
