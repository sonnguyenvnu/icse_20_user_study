/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.ide.messages;

import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import jetbrains.mps.ide.search.AbstractSearchPanel;
import jetbrains.mps.ide.search.SearchHistoryStorage;

import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MessageToolSearchPanel extends AbstractSearchPanel {

  private final JList myList;
  private final SearchHistoryStorage myHistory;
  private int myCountResult = 0;
  private final List<Integer> myResults = new ArrayList<>();
  private final MyCellRenderer myRenderer = new MyCellRenderer();
  private ListCellRenderer myOriginalCellRenderer;

  public MessageToolSearchPanel(JList list, SearchHistoryStorage history) {
    myList = list;
    myHistory = history;

    // Update search if list of messages is changed in any way
    myList.getModel().addListDataListener(new ListDataListener() {
      @Override
      public void intervalAdded(ListDataEvent e) {
        reSearch();
      }

      @Override
      public void intervalRemoved(ListDataEvent e) {
        reSearch();
      }

      @Override
      public void contentsChanged(ListDataEvent e) {
        reSearch();
      }

      private void reSearch() {
        myRenderer.search();
      }
    });
  }

  @Override
  public void goToPrevious() {
    if (myResults.isEmpty()) {
      return;
    }
    addToHistory();
    int selected = myList.getSelectedIndex();
    if (selected != -1) {
      for (int i = myResults.size() - 1; i >= 0; i--) {
        if (selected > myResults.get(i)) {
          selectAndReveal(myResults.get(i));
          return;
        }
      }
    }
    selectAndReveal(myResults.get(myResults.size() - 1));
  }

  @Override
  public void goToNext() {
    if (myResults.isEmpty()) {
      return;
    }
    addToHistory();
    int selected = myList.getSelectedIndex();
    if (selected != -1) {
      for (Integer index : myResults) {
        if (selected < index) {
          selectAndReveal(index);
          return;
        }
      }
    }
    selectAndReveal(myResults.get(0));
  }

  @Override
  protected SearchHistoryStorage getSearchHistory() {
    return myHistory;
  }

  @Override
  protected void search() {
    myRenderer.search();
  }

  @Override
  public void activate() {
    myOriginalCellRenderer = myList.getCellRenderer();
    myList.setCellRenderer(myRenderer);
    super.activate();
  }

  @Override
  protected void deactivate() {
    setVisible(false);
    myFindResult.setText("");
    myText.setText("");
    revalidate();
    myList.setCellRenderer(myOriginalCellRenderer);
    myList.requestFocus();
  }


  private void selectAndReveal(int index) {
    myList.setSelectedIndex(index);
    myList.ensureIndexIsVisible(index);
  }

  private class MyCellRenderer extends MessagesListCellRenderer {
    private int myIndex = -1;
    private final List<Integer> myColumnResults = new ArrayList<>();

    private void updateView() {
      updateSearchReport(myCountResult);
    }

    public void search() {
      myCountResult = 0;
      myResults.clear();
      myColumnResults.clear();
      for (int i = 0; i < myList.getModel().getSize(); i++) {
        StringBuilder line = new StringBuilder();
        line.append(myList.getModel().getElementAt(i));
        if (myText.getText().length() != 0) {
          Pattern pattern = getPattern();
          if (pattern == null) {
            setErrorMessage("Incorrect regular expression");
            return;
          }
          setErrorMessage(null);
          Matcher matcher = pattern.matcher(line.toString());
          while (matcher.find()) {
            int column = matcher.start() + 11;
            if (!(myColumnResults.contains(column) && myResults.contains(i)
                && myColumnResults.indexOf(column) == myResults.indexOf(i))) {
              myColumnResults.add(column);
              myResults.add(i);
              myCountResult++;
            }
          }
        }
      }
      updateView();
      myList.repaint();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      myIndex = index;
      return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }

    @Override
    public void paint(Graphics g) {
      super.paint(g);
      if (myText.getText().length() == 0) {
        myFindResult.setText("");
      }
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (myResults.contains(myIndex)) {
        final Color highlightColor =
            EditorColorsManager.getInstance().getGlobalScheme().getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES).getBackgroundColor();
        List<Integer> columns = new ArrayList<>();
        for (int i = 0; i < myResults.size(); i++) {
          int value = myResults.get(i);
          if (value == myIndex) {
            columns.add(myColumnResults.get(i));
          }
        }
        for (Integer column : columns) {
          final int endIndex = column + myText.getText().length();
          if (getText().length() <= endIndex) {
            continue; // just skip this outdated result to avoid IndexOutOfBoundsException (prevent painting problems).
          }
          final String foundText = getText().substring(column, endIndex);

          Graphics2D g2 = (Graphics2D) g;
          FontMetrics fontMetrics = g2.getFontMetrics();
          Color color = g2.getColor();
          g2.setColor(highlightColor);
          int startTextX = getInsets().left + getIcon().getIconWidth()
              + getIconTextGap()
              + fontMetrics.stringWidth(getText().substring(0, column));
          g2.fillRect(startTextX, 1,
              fontMetrics.stringWidth(foundText),
              fontMetrics.getHeight() - 1);
          g2.setColor(color);
          g2.drawString(foundText, startTextX, fontMetrics.getHeight() - fontMetrics.getLeading() - 1);
        }
      }
    }
  }
}
