/*
 * Copyright 2003-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.nodeEditor.cellMenu;

import com.intellij.util.ui.UIUtil;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.cells.TextLine;
import jetbrains.mps.nodeEditor.keyboard.TextChangeEvent;
import jetbrains.mps.openapi.editor.style.StyleRegistry;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.KeyEvent;

/**
 * Author: Sergey Dmitriev.
 * Time: Oct 20, 2003 1:45:39 PM
 */
public class NodeSubstitutePatternEditor {
  private EditorWindow myEditorWindow;
  private boolean myEditorActivated;

  private String myCachedText = "";
  private int myCachedCaretPosition;
  private int mySavedCaretPosition = 0;

  public void setText(String text) {
    if (myEditorActivated) {
      myEditorWindow.myTextLine.setText(text);
      myEditorWindow.relayout();
      myEditorWindow.repaint();
    } else {
      myCachedText = text;
    }
  }

  public boolean isActivated() {
    return myEditorActivated;
  }

  public String getText() {
    if (myEditorActivated) {
      return myEditorWindow.myTextLine.getText();
    }
    return myCachedText;
  }

  public void setCaretPosition(int caretPosition) {
    if (myEditorActivated) {
      myEditorWindow.myTextLine.setCaretPosition(caretPosition);
      myEditorWindow.repaint();
    } else {
      myCachedCaretPosition = caretPosition;
    }
  }

  public int getCaretPosition() {
    return myEditorWindow.myTextLine.getCaretPosition();
  }

  public boolean processKeyPressed(KeyEvent keyEvent) {
    if (myEditorActivated) {
      if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE && keyEvent.isControlDown()) {
        toggleReplaceMode();
        return true;
      }
      return myEditorWindow.processKeyPressed(keyEvent);
    }
    return false;
  }

  public void toggleReplaceMode() {
    if (myEditorActivated) {
      TextLine textLine = myEditorWindow.myTextLine;
      if (mySavedCaretPosition != 0) {
        textLine.setCaretPosition(mySavedCaretPosition);
        mySavedCaretPosition = 0;
      } else {
        mySavedCaretPosition = textLine.getCaretPosition();
        textLine.setCaretPosition(0);
      }
      myEditorWindow.repaint();
    } else {
      if (mySavedCaretPosition != 0) {
        myCachedCaretPosition = mySavedCaretPosition;
        mySavedCaretPosition = 0;
      } else {
        mySavedCaretPosition = myCachedCaretPosition;
        myCachedCaretPosition = 0;
      }
    }
  }

  public boolean processKeyTyped(KeyEvent keyEvent) {
    if (myEditorActivated && myEditorWindow.processKeyTyped(keyEvent)) {
      mySavedCaretPosition = 0;
      return true;
    }
    return false;
  }

  public boolean processTextChanged(TextChangeEvent textChangeEvent) {
    if (myEditorActivated) {
      myEditorWindow.processTextChanged(textChangeEvent);
      mySavedCaretPosition = 0;
      return true;
    }
    return false;
  }

  @NotNull
  public String getPattern() {
    if (myEditorActivated) {
      TextLine textLine = myEditorWindow.myTextLine;
      int caretPosition = textLine.getCaretPosition();
      String text = textLine.getText();
      return text.substring(0, caretPosition);
    }

    if (myCachedText == null) {
      return "";
    }
    int caretPos = Math.min(myCachedText.length(), Math.max(myCachedCaretPosition, 0));
    return myCachedText.substring(0, caretPos);
  }

  // ------------------

  public void activate(Window owner, Point location, Dimension size, boolean show) {
    if (!myEditorActivated) {
      myEditorActivated = true;
      myEditorWindow = new EditorWindow(owner);
      myEditorWindow.setLocation(location);
      myEditorWindow.setMinimalSize(size);
      myEditorWindow.myTextLine.setText(myCachedText);
      myEditorWindow.myTextLine.setCaretPosition(myCachedCaretPosition);
      if (show) {
        myEditorWindow.relayout();
        myEditorWindow.setVisible(true);
      }
    }
  }

  public void setLocation(Point point) {
    myEditorWindow.setLocation(point);
  }

  public Point getLeftBottomPosition() {
    Point location = myEditorWindow.getLocation();
    location.translate(0, myEditorWindow.getSize().height);
    return location;
  }

  public int getHeight() {
    return myEditorWindow.getSize().height;
  }

  public void done() {
    if (myEditorActivated) {
      myEditorWindow.dispose();
      myEditorActivated = false;
      mySavedCaretPosition = 0;
    }
  }

  private static class EditorWindow extends JWindow {
    private TextLine myTextLine;
    private Dimension myMinimalSize;
    private JPanel myPanel = new EditorPanel();

    public EditorWindow(Window owner) {
      super(owner);
      myTextLine = new TextLine("");
      add(myPanel);
    }

    public void setMinimalSize(Dimension size) {
      myMinimalSize = size;
    }

    public void relayout() {
      myTextLine.relayout();
      int h = Math.max(myMinimalSize.height, myTextLine.getHeight());
      int w = Math.max(myMinimalSize.width, getWidth());
      w = Math.max(w, myTextLine.getWidth());
      setSize(w, h);
    }

    public boolean processKeyTyped(KeyEvent keyEvent) {
      if (processKeyTypedInternal(keyEvent)) {
        relayout();
        repaint();
        return true;
      }
      return false;
    }

    public void processTextChanged(TextChangeEvent textChangeEvent) {
      String oldText = myTextLine.getText();
      int keptTextEndIndex = Math.max(myTextLine.getCaretPosition() - textChangeEvent.getOffset(), 0);
      changeText(oldText.substring(0, keptTextEndIndex) + textChangeEvent.getText());
      myTextLine.setCaretPosition(keptTextEndIndex + textChangeEvent.getText().length());
      relayout();
      repaint();
    }

    private boolean processKeyTypedInternal(KeyEvent keyEvent) {
      String oldText = myTextLine.getText();
      int caretPosition = myTextLine.getCaretPosition();

      char keyChar = keyEvent.getKeyChar();
      if (UIUtil.isReallyTypedEvent(keyEvent)) {
        changeText(oldText.substring(0, caretPosition) + keyChar/* + myText.substring(caretPosition)*/);
        myTextLine.setCaretPosition(caretPosition + 1);
        return true;
      }

      return false;
    }

    public boolean processKeyPressed(KeyEvent keyEvent) {
      if (processKeyPressedInternal(keyEvent)) {
        relayout();
        repaint();
        return true;
      }
      return false;
    }

    private boolean processKeyPressedInternal(KeyEvent keyEvent) {
      if (keyEvent.isControlDown()) {
        return false;
      }

      String oldText = myTextLine.getText();
      int caretPosition = myTextLine.getCaretPosition();
      if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
        if (caretPosition > 0) {
          changeText(oldText.substring(0, caretPosition - 1) + oldText.substring(caretPosition));
          myTextLine.setCaretPosition(caretPosition - 1);
          return true;
        } else {
          return false;
        }
      }

      if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
        if (caretPosition < oldText.length()) {
          changeText(oldText.substring(0, caretPosition) + oldText.substring(caretPosition + 1));
          return true;
        } else {
          return false;
        }
      }

      if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
        if (caretPosition > 0) {
          myTextLine.setCaretPosition(caretPosition - 1);
          return true;
        } else {
          return false;
        }
      }

      if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (caretPosition < oldText.length()) {
          myTextLine.setCaretPosition(caretPosition + 1);
          return true;
        } else {
          return false;
        }
      }

      return false;
    }

    protected void changeText(String text) {
      myTextLine.setText(text);
    }

    private class EditorPanel extends JPanel {
      @Override
      protected void paintComponent(Graphics g) {
        // COLORS: move colors to properties
        Rectangle bounds = g.getClipBounds();
        g.setColor(StyleRegistry.getInstance().getSimpleColor(Color.YELLOW));
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(StyleRegistry.getInstance().getSimpleColor(Color.GRAY));
        g.drawRect(bounds.x, bounds.y, bounds.width - 1, bounds.height - 1);
        EditorComponent.turnOnAliasingIfPossible((Graphics2D) g);

        TextLine textLine = myTextLine;
        textLine.setSelected(false);
        textLine.setShowCaret(true);
        textLine.paint(g, 0, 0);
      }
    }
  } // private class EditorWindow
}
