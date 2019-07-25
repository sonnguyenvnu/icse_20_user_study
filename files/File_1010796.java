/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.search.AbstractSearchPanel;
import jetbrains.mps.ide.search.SearchHistoryStorage;
import jetbrains.mps.nodeEditor.cellLayout.PunctuationUtil;
import jetbrains.mps.nodeEditor.cells.EditorCell_Collection;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.nodeEditor.text.TextRenderUtil;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.util.CollectionUtil;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SRepository;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchPanel extends AbstractSearchPanel {
  private EditorComponent myEditor;
  private List<SearchEntry> mySearchEntries = new ArrayList<>();
  private NodeHighlightManager myHighlightManager;
  private EditorMessageOwner myOwner;
  private SearchHistoryStorage mySearchHistory;

  public SearchPanel(EditorComponent editor) {
    super();
    myEditor = editor;
  }

  @Override
  protected SearchHistoryStorage getSearchHistory() {
    if (mySearchHistory == null) {
      final MPSProject p = MPSCommonDataKeys.MPS_PROJECT.getData(DataManager.getInstance().getDataContext(myEditor));
      if (p != null) {
        mySearchHistory = p.getComponent(SearchHistoryComponent.class);
      }
      if (mySearchHistory == null) {
        mySearchHistory = new SearchHistoryComponent();
      }
    }
    return mySearchHistory;
  }

  private Pair<List<EditorCell_Label>, String> allCellsAndContent() {
    StringBuilder sb = new StringBuilder();
    List<EditorCell_Label> cells = new ArrayList<>();
    EditorCell rootCell = myEditor.getRootCell();
    if (rootCell instanceof EditorCell_Label) {
      EditorCell_Label cell_label = (EditorCell_Label) rootCell;
      cells.add(cell_label);
      sb.append(cell_label.getRenderedText());
    }
    if (rootCell instanceof EditorCell_Collection) {
      EditorCell_Collection collection = (EditorCell_Collection) rootCell;
      List<EditorCell_Label> editorCell_labelList = CollectionUtil.filter(EditorCell_Label.class, collection.dfsCells());
      for (EditorCell_Label label : editorCell_labelList) {
        if (PunctuationUtil.hasLeftGap(label)) {
          sb.append(' ');
        }
        sb.append(label.getRenderedText());
      }
      cells.addAll(editorCell_labelList);
    }
    return new Pair<>(cells, sb.toString());
  }

  @Override
  protected boolean showExportToFindTool() {
    return true;
  }

  @Override
  protected boolean canExportToFindTool() {
    return !getMessages().isEmpty();
  }

  @Override
  public void goToPrevious() {
    if (mySearchEntries.size() == 0) {
      return;
    }
    addToHistory();
    EditorCell selectedCell = myEditor.getDeepestSelectedCell();
    int selectionStart = 0;
    boolean isEmpty = false;
    if (selectedCell instanceof EditorCell_Label) {
      EditorCell_Label labelCell = (EditorCell_Label) selectedCell;
      selectionStart = labelCell.getSelectionStart();
      isEmpty = labelCell.getText().isEmpty();
    }
    SearchEntry entryToSelect = null;
    for (ListIterator<SearchEntry> it = mySearchEntries.listIterator(mySearchEntries.size()); it.hasPrevious() && entryToSelect == null; ) {
      SearchEntry currentEntry = it.previous();
      if (CellTraversalUtil.compare(selectedCell, currentEntry.getStartLabel()) >= 0) {
        while (entryToSelect == null) {
          if (!currentEntry.getStartLabel().equals(selectedCell) || (selectionStart >= currentEntry.getFirstRange().getEndPosition() && !isEmpty)) {
            entryToSelect = currentEntry;
          }
          if (it.hasPrevious()) {
            currentEntry = it.previous();
          } else {
            break;
          }
        }
      }
    }
    if (entryToSelect == null) {
      entryToSelect = mySearchEntries.get(mySearchEntries.size() - 1);
    }
    entryToSelect.select();
  }

  @Override
  public void goToNext() {
    if (mySearchEntries.size() == 0) {
      return;
    }
    addToHistory();
    EditorCell selectedCell = myEditor.getDeepestSelectedCell();
    int selectionEnd = -1;
    boolean isEmpty = false;
    if (selectedCell instanceof EditorCell_Label) {
      EditorCell_Label labelCell = (EditorCell_Label) selectedCell;
      selectionEnd = labelCell.getSelectionEnd();
      isEmpty = labelCell.getText().isEmpty();
    }
    SearchEntry entryToSelect = null;
    for (ListIterator<SearchEntry> it = mySearchEntries.listIterator(); it.hasNext() && entryToSelect == null; ) {
      SearchEntry currentEntry = it.next();
      if (CellTraversalUtil.compare(selectedCell, currentEntry.getStartLabel()) <= 0) {
        while (entryToSelect == null) {
          if (!currentEntry.getStartLabel().equals(selectedCell) || (selectionEnd <= currentEntry.getFirstRange().getStartPosition() && !isEmpty)) {
            entryToSelect = currentEntry;
          }
          if (it.hasNext()) {
            currentEntry = it.next();
          } else {
            break;
          }
        }
      }
    }
    if (entryToSelect == null) {
      entryToSelect = mySearchEntries.get(0);
    }
    entryToSelect.select();
  }

  private void clearHighlight() {
    if (myOwner != null && myHighlightManager != null && mySearchEntries.size() <= 100) {
      myHighlightManager.clearForOwner(myOwner);
    }
  }

  @Override
  protected void search() {
    search(true);
  }

  protected void search(boolean requestFocus) {
    clearHighlight();
    mySearchEntries.clear();
    if (myText.getText().length() == 0) {
      myFindResult.setText("");
      myText.setBackground(myDefaultBackground);
      if (requestFocus) {
        myText.requestFocus();
        myEditor.repaintExternalComponent();
      }
      return;
    }
    selectCell(requestFocus);
    updateSearchReport(mySearchEntries.size());
  }

  public void update(AnActionEvent e) {

  }

  private void selectCell(boolean requestFocus) {
    Pair<List<EditorCell_Label>, String> pair = allCellsAndContent();
    final List<EditorCell_Label> cells = pair.o1;
    List<Integer> startCellPosition = new ArrayList<>();
    List<Integer> endCellPosition = new ArrayList<>();
    String content = pair.o2;
    int current = 0;
    List<EditorCell> emptyCells = new ArrayList<>();
    for (EditorCell_Label cell : cells) {
      if (cell.getRenderedText().isEmpty()) {
        emptyCells.add(cell);
      }
    }
    cells.removeAll(emptyCells);
    for (EditorCell_Label cell : cells) {
      if (current >= content.length()) {
        break;
      }
      String contentPart = content.substring(current);
      int start = contentPart.indexOf(cell.getRenderedText()) + current;
      startCellPosition.add(start);
      current = start + cell.getRenderedText().length();
      endCellPosition.add(current);
    }

    Pattern pattern = getPattern();
    if (pattern == null) {
      setErrorMessage("Incorrect regular expression");
      return;
    }
    setErrorMessage(null);
    Matcher matcher = pattern.matcher(content);
    int index = 0;
    SearchEntry searchEntryToSelect = null;
    while (matcher.find()) {
      while (index < endCellPosition.size() && endCellPosition.get(index) <= matcher.start()) {
        index++;
      }
      if (index >= startCellPosition.size()) {
        break;
      }
      if (startCellPosition.get(index) > matcher.start()) {
        // found text does not belong to any cell. Looking for next entry.
        continue;
      }

      EditorCell_Label startCell = cells.get(index);
      assert startCell != null;

      List<TextRange> textRanges = new ArrayList<>();
      for (int rangeIndex = index; rangeIndex < startCellPosition.size() && startCellPosition.get(rangeIndex) < matcher.end(); rangeIndex++) {
        int startPosition = Math.max(0, matcher.start() - startCellPosition.get(rangeIndex));
        int endPosition = Math.min(matcher.end(), endCellPosition.get(rangeIndex)) - startCellPosition.get(rangeIndex);
        EditorCell_Label nextCell = cells.get(rangeIndex);
        assert nextCell != null;
        textRanges.add(new TextRange(nextCell, startPosition, endPosition));
      }
      SearchEntry searchEntry = new SearchEntry(startCell, textRanges);
      mySearchEntries.add(searchEntry);

      //noinspection SuspiciousMethodCalls
      if (requestFocus && searchEntryToSelect == null && index >= cells.indexOf(myEditor.getSelectedCell())) {
        searchEntryToSelect = searchEntry;
      }
    }
    myOwner = new EditorMessageOwner() {
    };
    if (!mySearchEntries.isEmpty() && mySearchEntries.size() <= 100) {
      highlight(mySearchEntries);
    }
    if (searchEntryToSelect != null) {
      searchEntryToSelect.select();
    }
  }

  private void highlight(final List<SearchEntry> searchEntries) {
    final SRepository editorRepo = myEditor.getEditorContext().getRepository();
    editorRepo.getModelAccess().runReadAction(() -> {
      myHighlightManager = myEditor.getHighlightManager();
      List<EditorMessage> messages = new ArrayList<>();
      Map<EditorCell_Label, List<Pair>> cellToPositions = new LinkedHashMap<>();
      for (SearchEntry searchEntry : searchEntries) {
        for (TextRange range : searchEntry.getRangesIterator()) {
          if (!cellToPositions.containsKey(range.getLabel())) {
            cellToPositions.put(range.getLabel(), new ArrayList<>());
          }
          cellToPositions.get(range.getLabel()).add(new Pair(range.getStartPosition(), range.getEndPosition()));
        }
      }
      for (EditorCell_Label cell : cellToPositions.keySet()) {
        messages.add(new SearchPanelEditorMessage(cell, cellToPositions.get(cell)));
      }
      myHighlightManager.mark(messages);
    });
  }

  private List<SearchPanelEditorMessage> getMessages() {
    final List<SearchPanelEditorMessage> searchMessages = new ArrayList<>();
    if (myEditor == null) {
      return searchMessages;
    }
    for (SimpleEditorMessage candidate : myEditor.getMessages()) {
      if (candidate instanceof SearchPanelEditorMessage) {
        searchMessages.add((SearchPanelEditorMessage) candidate);
      }
    }
    return searchMessages;
  }

  @Override
  public void exportToFindTool() {
    final List<SearchPanelEditorMessage> searchMessages = getMessages();
    final List<EditorCell_Label> editorLabels = allCellsAndContent().o1;
    Collections.sort(searchMessages, (o1, o2) -> {
      Integer i1 = editorLabels.indexOf(o1.getCell(myEditor));
      Integer i2 = editorLabels.indexOf(o2.getCell(myEditor));
      return i1.compareTo(i2);
    });
    // TODO FIXME
//    UsagesViewTool usagesViewTool = new UsagesViewTool(ProjectHelper.toIdeaProject(myEditor.getOperationContext().getProject()));
//    BaseNode baseNode = new BaseNode() {
//      public SearchResults doGetResults(SearchQuery query, @NotNull ProgressMonitor monitor) {
//        monitor.start("", 1);
//        SearchResults<SNode> searchResults = new SearchResults<SNode>();
//        for (SearchPanelEditorMessage message : searchMessages) {
//          EditorCell cell = message.getCell(myEditor);
//          if (cell == null) continue;
//          SNode node = cell.getSNode();
//          searchResults.getSearchResults().add(new SearchResult<SNode>(node, "Search Panel"));
//        }
//        monitor.done();
//        return searchResults;
//      }
//    };
//    SearchQuery searchQuery = new SearchQuery(null) {
//      @NotNull
//      public String getCaption() {
//        return "Occurrences of '" + myText.getText() + "'";
//      }
//    };
//    usagesViewTool.findUsages(baseNode, searchQuery, false, false, false, null);
  }

  boolean isTextFieldFocused() {
    return myText.isFocusOwner();
  }

  @Override
  public void deactivate() {
    setVisible(false);
    clearHighlight();
    if (!mySearchEntries.isEmpty()) {
      mySearchEntries.clear();
    }
    myFindResult.setText("");
    myText.setText("");
    myText.setBackground(myDefaultBackground);
    revalidate();
    myEditor.removeUpperComponent(this);
    myEditor.requestFocus();
  }

  @Override
  public void activate() {
    String initValue = "";
    if (myEditor.getDeepestSelectedCell() instanceof EditorCell_Label) {
      EditorCell_Label cell_label = (EditorCell_Label) myEditor.getDeepestSelectedCell();
      if (cell_label.getSelectionStart() != cell_label.getSelectionEnd()) {
        initValue = TextRenderUtil.getTextBuilderForSelectedCellsOfEditor(myEditor).getText();
      }
    }
    setInitialText(initValue);
    myEditor.addUpperComponent(this);
    super.activate();
  }

  private class SearchPanelEditorMessage extends DefaultEditorMessage {
    @NotNull
    private final List<Pair> myPositions;
    /**
     * Using cell instead of CellInfo here because SearchPanel itself depends on EditorCells
     * and re-execute search query/re-create EditorMessages on each underlying editor relayout
     */
    @NotNull
    private EditorCell_Label myCell;

    public SearchPanelEditorMessage(@NotNull EditorCell_Label cell, @NotNull List<Pair> positions) {
      super(cell.getSNode(),
          EditorColorsManager.getInstance().getGlobalScheme().getAttributes(EditorColors.SEARCH_RESULT_ATTRIBUTES).getBackgroundColor(),
          "", SearchPanel.this.myOwner);
      myCell = cell;
      myPositions = positions;
    }

    @Override
    public EditorCell getCell(EditorComponent editor) {
      return myCell;
    }

    @Override
    public boolean acceptCell(EditorCell cell, EditorComponent editor) {
      return myCell == cell;
    }

    @Override
    public void paint(Graphics g, EditorComponent editorComponent, EditorCell cell) {
      if (!(cell instanceof EditorCell_Label)) {
        return;
      }
      EditorCell_Label editorCell = (EditorCell_Label) cell;
      for (Pair position : myPositions) {
        int startPosition = (Integer) position.o1;
        int endPosition = (Integer) position.o2;
        if (editorCell.getRenderedText().length() >= endPosition) {
          FontMetrics metrics = g.getFontMetrics();
          String text = editorCell.getRenderedText().substring(startPosition, endPosition);
          int prevStringWidth = metrics.stringWidth(editorCell.getRenderedText().
              substring(0, startPosition));
          int x = editorCell.getX() + editorCell.getLeftInset()
              + prevStringWidth;
          int y = editorCell.getY();
          int height = editorCell.getHeight();
          int width = metrics.stringWidth(text);

          g.setColor(getColor());
          // Filling smaller rectangle to not cover frames created by other nessages
          g.fillRect(x + 1, y + 1, width - 2, height - 2);
        }
      }
    }

    @Override
    public boolean sameAs(SimpleEditorMessage message) {
      return super.sameAs(message) && this.equals(message);
    }

    @Override
    public boolean isBackground() {
      return true;
    }

    @Override
    public int getPriority() {
      return 10;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof SearchPanelEditorMessage)) {
        return false;
      }

      SearchPanelEditorMessage that = (SearchPanelEditorMessage) o;

      if (!myCell.equals(that.myCell)) {
        return false;
      }
      return myPositions.equals(that.myPositions);
    }

    @Override
    public int hashCode() {
      int result = myPositions.hashCode();
      result = 31 * result + myCell.hashCode();
      return result;
    }
  }

  private class SearchEntry {
    private EditorCell_Label myStartLabel;
    private List<TextRange> myTextRanges;

    public SearchEntry(@NotNull EditorCell_Label startLabel, @NotNull List<TextRange> textRanges) {
      myStartLabel = startLabel;
      assert textRanges.size() > 0;
      myTextRanges = textRanges;
    }

    @NotNull
    public Iterable<TextRange> getRangesIterator() {
      return myTextRanges;
    }

    @NotNull
    public TextRange getFirstRange() {
      return myTextRanges.get(0);
    }

    @NotNull
    public EditorCell_Label getStartLabel() {
      return myStartLabel;
    }

    public void select() {
      TextRange range = getFirstRange();
      myEditor.changeSelection(range.getLabel());
      boolean canSetCaretStart = range.getLabel().isCaretPositionAllowed(range.getStartPosition());
      if (canSetCaretStart) {
        range.getLabel().setCaretPosition(range.getStartPosition());
      }
      boolean canSetCaretEnd = range.getLabel().isCaretPositionAllowed(range.getEndPosition());
      if (canSetCaretEnd) {
        range.getLabel().setCaretPosition(range.getEndPosition(), canSetCaretStart);
      }

      if (!(canSetCaretStart && canSetCaretEnd)) {
        range.getLabel().setSelectionStart(range.getStartPosition());
        range.getLabel().setSelectionEnd(range.getEndPosition());
      }

    }
  }

  private class TextRange {
    private EditorCell_Label myLabel;
    private int myStartPosition;
    private int myEndPosition;

    public TextRange(@NotNull EditorCell_Label firstLabel, int startPosition, int endPosition) {
      myLabel = firstLabel;
      myStartPosition = startPosition;
      myEndPosition = endPosition;
    }

    @NotNull
    public EditorCell_Label getLabel() {
      return myLabel;
    }

    public int getStartPosition() {
      return myStartPosition;
    }

    public int getEndPosition() {
      return myEndPosition;
    }
  }
}
