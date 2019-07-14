private static int padSpaceAround(EditText editText,int start,int end){
  Editable editable=editText.getText();
  if (start > 0) {
    char c=editable.charAt(start - 1);
    if (c == '\n') {
    }
 else     if (c == ' ') {
      --start;
    }
 else {
      insertPreservingSelection(editText,start," ");
      ++end;
    }
  }
  if (end < editable.length()) {
    char c=editable.charAt(end);
    if (c == '\n') {
    }
 else     if (c == ' ') {
      ++end;
    }
 else {
      insertPreservingSelection(editText,end," ");
      ++end;
    }
  }
  return end;
}
