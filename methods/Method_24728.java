/** 
 * Intercepts key pressed events for JEditTextArea. <p/> Called by JEditTextArea inside processKeyEvent(). Note that this won't intercept actual characters, because those are fired on keyTyped().
 * @return true if the event has been handled (to remove it from the queue)
 */
public boolean handlePressed(KeyEvent event){
  char c=event.getKeyChar();
  int code=event.getKeyCode();
  Sketch sketch=editor.getSketch();
  JEditTextArea textarea=editor.getTextArea();
  if ((event.getModifiers() & InputEvent.META_MASK) != 0) {
    return false;
  }
  if ((code == KeyEvent.VK_BACK_SPACE) || (code == KeyEvent.VK_TAB) || (code == KeyEvent.VK_ENTER) || ((c >= 32) && (c < 128))) {
    sketch.setModified(true);
  }
  if ((code == KeyEvent.VK_UP) && ((event.getModifiers() & InputEvent.CTRL_MASK) != 0)) {
    char contents[]=textarea.getText().toCharArray();
    int caretIndex=textarea.getCaretPosition();
    int index=calcLineStart(caretIndex - 1,contents);
    index-=2;
    boolean onlySpaces=true;
    while (index > 0) {
      if (contents[index] == 10) {
        if (onlySpaces) {
          index++;
          break;
        }
 else {
          onlySpaces=true;
        }
      }
 else       if (contents[index] != ' ') {
        onlySpaces=false;
      }
      index--;
    }
    if (index < 0)     index=0;
    if ((event.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
      textarea.setSelectionStart(caretIndex);
      textarea.setSelectionEnd(index);
    }
 else {
      textarea.setCaretPosition(index);
    }
    event.consume();
  }
 else   if ((code == KeyEvent.VK_DOWN) && ((event.getModifiers() & InputEvent.CTRL_MASK) != 0)) {
    char contents[]=textarea.getText().toCharArray();
    int caretIndex=textarea.getCaretPosition();
    int index=caretIndex;
    int lineStart=0;
    boolean onlySpaces=false;
    while (index < contents.length) {
      if (contents[index] == 10) {
        if (onlySpaces) {
          index=lineStart;
          break;
        }
 else {
          lineStart=index + 1;
          onlySpaces=true;
        }
      }
 else       if (contents[index] != ' ') {
        onlySpaces=false;
      }
      index++;
    }
    if ((event.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
      textarea.setSelectionStart(caretIndex);
      textarea.setSelectionEnd(index);
    }
 else {
      textarea.setCaretPosition(index);
    }
    event.consume();
  }
 else   if (c == 9) {
    if ((event.getModifiers() & InputEvent.SHIFT_MASK) != 0) {
      editor.handleOutdent();
    }
 else     if (textarea.isSelectionActive()) {
      editor.handleIndent();
    }
 else     if (Preferences.getBoolean("editor.tabs.expand")) {
      int tabSize=Preferences.getInteger("editor.tabs.size");
      textarea.setSelectedText(spaces(tabSize));
      event.consume();
    }
 else {
      textarea.setSelectedText("\t");
      event.consume();
    }
  }
 else   if (code == 10 || code == 13) {
    if (Preferences.getBoolean("editor.indent")) {
      char contents[]=textarea.getText().toCharArray();
      int tabSize=Preferences.getInteger("editor.tabs.size");
      int origIndex=textarea.getCaretPosition() - 1;
      int spaceCount=calcSpaceCount(origIndex,contents);
      int index2=origIndex;
      while ((index2 >= 0) && Character.isWhitespace(contents[index2])) {
        index2--;
      }
      if (index2 != -1) {
        if (contents[index2] == '{') {
          spaceCount=calcSpaceCount(index2,contents);
          spaceCount+=tabSize;
        }
      }
      int index=origIndex + 1;
      int extraCount=0;
      while ((index < contents.length) && (contents[index] == ' ')) {
        extraCount++;
        index++;
      }
      int braceCount=0;
      while ((index < contents.length) && (contents[index] != '\n')) {
        if (contents[index] == '}') {
          braceCount++;
        }
        index++;
      }
      spaceCount-=extraCount;
      if (spaceCount < 0) {
        textarea.setSelectionEnd(textarea.getSelectionStop() - spaceCount);
        textarea.setSelectedText("\n");
        textarea.setCaretPosition(textarea.getCaretPosition() + extraCount + spaceCount);
      }
 else {
        String insertion="\n" + spaces(spaceCount);
        textarea.setSelectedText(insertion);
        textarea.setCaretPosition(textarea.getCaretPosition() + extraCount);
      }
      if (braceCount > 0) {
        int sel=textarea.getSelectionStart();
        if (sel - tabSize >= 0) {
          textarea.select(sel - tabSize,sel);
          String s=spaces(tabSize);
          if (textarea.getSelectedText().equals(s)) {
            textarea.setSelectedText("");
          }
 else {
            textarea.select(sel,sel);
          }
        }
      }
    }
 else {
      textarea.setSelectedText(String.valueOf(c));
    }
    event.consume();
  }
 else   if (c == '}') {
    if (Preferences.getBoolean("editor.indent")) {
      if (textarea.getSelectionStart() != textarea.getSelectionStop()) {
        textarea.setSelectedText("");
      }
      char contents[]=textarea.getText().toCharArray();
      int prevCharIndex=textarea.getCaretPosition() - 1;
      int index=prevCharIndex;
      boolean finished=false;
      while ((index != -1) && (!finished)) {
        if (contents[index] == 10) {
          finished=true;
          index++;
        }
 else         if (contents[index] != ' ') {
          return false;
        }
 else {
          index--;
        }
      }
      if (!finished)       return false;
      int lineStartIndex=index;
      int pairedSpaceCount=calcBraceIndent(prevCharIndex,contents);
      if (pairedSpaceCount == -1)       return false;
      textarea.setSelectionStart(lineStartIndex);
      textarea.setSelectedText(spaces(pairedSpaceCount));
      event.consume();
      return true;
    }
  }
  return false;
}
