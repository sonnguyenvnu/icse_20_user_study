/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.leftHighlighter;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionPopupMenu;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.util.ui.UIUtil;
import gnu.trove.THashMap;
import jetbrains.mps.ide.actions.MPSActions;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import jetbrains.mps.ide.editor.MPSEditorDataKeys;
import jetbrains.mps.ide.tooltips.MPSToolTipManager;
import jetbrains.mps.ide.tooltips.TooltipComponent;
import jetbrains.mps.nodeEditor.EditorComponent;
import jetbrains.mps.nodeEditor.EditorMessageIconRenderer;
import jetbrains.mps.nodeEditor.EditorMessageIconRenderer.IconRendererType;
import jetbrains.mps.nodeEditor.EditorSettings;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.nodeEditor.leftHighlighter.IconPositionCalculator.IntLocation;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.update.UpdaterListenerAdapter;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.workbench.action.ActionUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.module.SRepository;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class should be called in UI (EventDispatch) thread only
 */
public class LeftEditorHighlighter extends JComponent implements TooltipComponent {
  public static final String ICON_AREA = "LeftEditorHighlighterIconArea";

  private static final int MIN_LEFT_TEXT_WIDTH = 0;
  private static final int MIN_LEFT_FOLDING_AREA_WIDTH = 7;
  private static final int MIN_RIGHT_FOLDING_AREA_WIDTH = 4;
  private static final int FOLDING_LINE_WIDTH = 1;
  private static final Comparator<AbstractFoldingAreaPainter> FOLDING_AREA_PAINTERS_COMPARATOR = (afap1, afap2) -> {
    if (afap1.getWeight() == afap2.getWeight() && !afap1.equals(afap2)) {
      return System.identityHashCode(afap1) - System.identityHashCode(afap2);
    }
    return afap1.getWeight() - afap2.getWeight();
  };

  private EditorComponent myEditorComponent;
  private NavigableSet<AbstractFoldingAreaPainter> myFoldingAreaPainters = new TreeSet<>(FOLDING_AREA_PAINTERS_COMPARATOR);
  private BracketsPainter myBracketsPainter;
  private FoldingButtonsPainter myFoldingButtonsPainter;

  private List<AbstractLeftColumn> myLeftColumns = new ArrayList<>();

  private Set<EditorMessageIconRenderer> myIconRenderers = new HashSet<>();
  private THashMap<EditorMessageIconRenderer, IntLocation> myRendererToCoord;
  private EditorMessageIconRenderer myRendererUnderMouse;
  private int myMaxIconHeight = 0;

  private boolean myMouseIsInFoldingArea;

  private int myFoldingLineX;
  private int myIconRenderersWidth;
  private int myTextColumnWidth;
  private int myLeftFoldingAreaWidth;
  private int myRightFoldingAreaWidth;
  private int myWidth;
  private int myHeight;
  private boolean myRightToLeft;

  public LeftEditorHighlighter(@NotNull EditorComponent editorComponent, boolean rightToLeft) {
    setBackground(EditorSettings.getInstance().getLeftHighlighterBackgroundColor());
    myEditorComponent = editorComponent;
    myRightToLeft = rightToLeft;
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        mouseExitedFoldingArea(e);
        mouseExitedIconsArea(e);
      }

      @Override
      public void mouseEntered(MouseEvent e) {
        if (isInFoldingArea(e)) {
          mouseMovedInFoldingArea(e);
        } else if (isInTextArea(e)) {
          mouseMovedInTextArea(e);
        } else {
          mouseMovedInIconsArea(e);
        }
      }
    });
    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        if (isInFoldingArea(e)) {
          mouseExitedIconsArea(e);
          mouseMovedInFoldingArea(e);
        } else if (isInTextArea(e)) {
          mouseExitedFoldingArea(e);
          mouseExitedIconsArea(e);
          mouseMovedInTextArea(e);
        } else {
          mouseExitedFoldingArea(e);
          mouseMovedInIconsArea(e);
        }
      }
    });
    if (MPSToolTipManager.getInstance() != null) {
      MPSToolTipManager.getInstance().registerComponent(this);
    }
    editorComponent.getUpdater().addListener(new UpdaterListenerAdapter() {
      @Override
      public void editorUpdated(jetbrains.mps.openapi.editor.EditorComponent editorComponent) {
        assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter$RebuildListener should be called in eventDispatchThread";
        for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
          painter.editorRebuilt();
        }
        relayout(true);
      }
    });
    myBracketsPainter = new BracketsPainter(this, myRightToLeft);
    myFoldingButtonsPainter = new FoldingButtonsPainter(this);

    myFoldingAreaPainters.add(myBracketsPainter);
    myFoldingAreaPainters.add(myFoldingButtonsPainter);
  }

  @NotNull
  public EditorComponent getEditorComponent() {
    return myEditorComponent;
  }

  public int getRightFoldingAreaWidth() {
    return myRightFoldingAreaWidth;
  }

  public int getFoldingLineX() {
    return myFoldingLineX;
  }

  public int getIconRenderersOffset() {
    return myRightToLeft ? getFoldingAreaWidth() : myTextColumnWidth;
  }

  private int getFoldingAreaOffset() {
    return myRightToLeft ? 0 : myTextColumnWidth + myIconRenderersWidth;
  }

  private int getTextColumnOffset() {
    return myRightToLeft ? getFoldingAreaWidth() + myIconRenderersWidth : 0;
  }

  private int getFoldingAreaWidth() {
    return myLeftFoldingAreaWidth + FOLDING_LINE_WIDTH + myRightFoldingAreaWidth;
  }

  private boolean isInside(int offset, int width, int x) {
    return offset <= x && x < offset + width;
  }

  private boolean hasIntersection(int x1, int width1, Rectangle rectangle) {
    int x2 = rectangle.x;
    int width2 = rectangle.width;
    return x1 <= x2 ? x1 + width1 >= x2 : x2 + width2 >= x1;
  }

  public void dispose() {
    for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
      painter.dispose();
    }
    for (AbstractLeftColumn column : myLeftColumns) {
      column.dispose();
    }
    if (MPSToolTipManager.getInstance() != null) {
      MPSToolTipManager.getInstance().unregisterComponent(this);
    }
  }

  public void setDefaultFoldingAreaPaintersEnabled(boolean enabled) {
    if (enabled) {
      addFoldingAreaPainter(myBracketsPainter);
      addFoldingAreaPainter(myFoldingButtonsPainter);
    } else {
      removeFoldingAreaPainter(myBracketsPainter);
      removeFoldingAreaPainter(myFoldingButtonsPainter);
    }
  }

  public void addFoldingAreaPainter(AbstractFoldingAreaPainter painter) {
    if (myFoldingAreaPainters.contains(painter)) {
      return;
    }
    myFoldingAreaPainters.add(painter);
    relayout(true);
    repaint();
  }

  public void removeFoldingAreaPainter(AbstractFoldingAreaPainter painter) {
    if (!myFoldingAreaPainters.contains(painter)) {
      return;
    }
    myFoldingAreaPainters.remove(painter);
    relayout(true);
    repaint();
  }

  public void addLeftColumn(AbstractLeftColumn column) {
    myLeftColumns.add(column);
    relayoutOnLeftColumnChange();
    repaint();
  }

  public void removeLeftColumn(AbstractLeftColumn column) {
    myLeftColumns.remove(column);
    relayoutOnLeftColumnChange();
    repaint();
  }

  public List<AbstractLeftColumn> getLeftColumns() {
    return myLeftColumns;
  }

  @Override
  public void paintComponent(Graphics g) {
    Rectangle clipBounds = g.getClipBounds();
    paintBackgroundAndFoldingLine(g, clipBounds);
    paintTextColumns(g, clipBounds);
    paintIconRenderers(g, clipBounds);
    paintFoldingArea(g, clipBounds);
  }

  private void paintFoldingArea(Graphics g, Rectangle clipBounds) {
    if (!hasIntersection(getFoldingAreaOffset(), getFoldingAreaWidth(), clipBounds)) {
      return;
    }
    for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
      painter.paint(g);
    }
  }

  private void paintBackgroundAndFoldingLine(Graphics g, Rectangle clipBounds) {
    Graphics2D g2d = (Graphics2D) g;
    g.setColor(getBackground());
    g.fillRect(clipBounds.x, clipBounds.y, Math.min(clipBounds.width, myFoldingLineX - clipBounds.x), clipBounds.height);
    g.setColor(getEditorComponent().getBackground());
    g.fillRect(Math.max(clipBounds.x, myFoldingLineX), clipBounds.y, clipBounds.width - Math.max(0, myFoldingLineX - clipBounds.x), clipBounds.height);

    // same as in EditorComponent.paint() method
    EditorCell deepestCell = myEditorComponent.getDeepestSelectedCell();
    if (deepestCell instanceof EditorCell_Label) {
      int selectedCellY = deepestCell.getY();
      int selectedCellHeight = deepestCell.getHeight() - deepestCell.getTopInset() - deepestCell.getBottomInset();
      if (g.hitClip(clipBounds.x, selectedCellY, clipBounds.width, selectedCellHeight)) {
        g.setColor(EditorSettings.getInstance().getCaretRowColor());
        g.fillRect(clipBounds.x, selectedCellY, clipBounds.width, selectedCellHeight);
        // Drawing folding line
        UIUtil.drawVDottedLine(g2d, myFoldingLineX, clipBounds.y, selectedCellY, getBackground(),
                               EditorSettings.getInstance().getLeftHighlighterTearLineColor());
        UIUtil.drawVDottedLine(g2d, myFoldingLineX, selectedCellY, selectedCellY + selectedCellHeight, EditorSettings.getInstance().getCaretRowColor(),
                               EditorSettings.getInstance().getLeftHighlighterTearLineColor());
        UIUtil.drawVDottedLine(g2d, myFoldingLineX, selectedCellY + selectedCellHeight, clipBounds.y + clipBounds.height, getBackground(),
                               EditorSettings.getInstance().getLeftHighlighterTearLineColor());
        return;
      }
    }
    // Drawing folding line
    // COLORS: Remove hardcoded color
    UIUtil.drawVDottedLine(g2d, myFoldingLineX, clipBounds.y, clipBounds.y + clipBounds.height, getBackground(), Color.gray);
  }

  private void paintIconRenderers(final Graphics g, Rectangle clipBounds) {
    if (!hasIntersection(getIconRenderersOffset(), myIconRenderersWidth, clipBounds)) {
      return;
    }
    final int startY = clipBounds.y;
    final int endY = clipBounds.y + clipBounds.height;
    myRendererToCoord.forEachEntry((renderer, location) -> {
      if (startY <= location.getY() && location.getY() <= endY) {
        renderer.getIcon().paintIcon(LeftEditorHighlighter.this, g, location.getX(), location.getY());
      }
      return true;
    });
  }

  private void paintTextColumns(Graphics g, Rectangle clipBounds) {
    if (!hasIntersection(getTextColumnOffset(), myTextColumnWidth, clipBounds)) {
      return;
    }
    for (AbstractLeftColumn column : myLeftColumns) {
      if (clipBounds.x > column.getX() + column.getWidth()) {
        continue;
      }
      column.paint(g);
      //  COLORS: find out where it is and remove hardcoded color
      UIUtil.drawVDottedLine((Graphics2D) g, myRightToLeft ? column.getX() : column.getX() + column.getWidth() - 1,
                             (int) clipBounds.getMinY(), (int) clipBounds.getMaxY(), getBackground(), Color.GRAY);
    }
  }

  public void unHighlight(EditorCell cell) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.unHighlight() should be called in eventDispatchThread";
    myBracketsPainter.removeBracket(cell);
    relayout(true);
    repaint();
  }

  public void highlight(EditorCell cell, EditorCell cell2, Color c) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.highlight() should be called in eventDispatchThread";
    assert cell.getEditorComponent() == myEditorComponent : "cell must be from my editor";
    myBracketsPainter.addBracket(cell, cell2, c);
    relayout(true);
    repaint();
  }

  public void relayout(boolean updateFolding) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.relayout() should be executed in eventDispatchThread";
    final SNode editedNode = myEditorComponent.getEditedNode();
    // additional check - during editor dispose process some Folding area painters can be removed calling relayout()..
    if (myEditorComponent.isDisposed()) {
      return;
    }
    if (editedNode != null) {
      SRepository repository = myEditorComponent.getEditorContext().getRepository();
      if (new ModelAccessHelper(repository).runReadAction(() -> !SNodeUtil.isAccessible(editedNode, repository))) {
        return;
      }
    }
    if (myRightToLeft) {
      recalculateFoldingAreaWidth();
      updateSeparatorLinePosition();
      if (updateFolding) {
        for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
          painter.relayout();
        }
        // wee need to recalculateIconRenderersWidth only if one of collections was folded/unfolded
        recalculateIconRenderersWidth();
      }
      recalculateTextColumnWidth();
    } else {
      recalculateTextColumnWidth();
      if (updateFolding) {
        for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
          painter.relayout();
        }
        // wee need to recalculateIconRenderersWidth only if one of collections was folded/unfolded
        recalculateIconRenderersWidth();
      }
      recalculateFoldingAreaWidth();
      updateSeparatorLinePosition();
    }
    updatePreferredSize();
  }

  // Optimization: partly layouting
  private void relayoutOnIconRendererChanges() {
    if (myRightToLeft) {
      recalculateIconRenderersWidth();
      recalculateTextColumnWidth();
    } else {
      recalculateIconRenderersWidth();
      updateSeparatorLinePosition();
    }
    updatePreferredSize();
  }

  // Optimization: partly layouting
  private void relayoutOnLeftColumnChange() {
    if (myRightToLeft) {
      recalculateTextColumnWidth();
    } else {
      recalculateTextColumnWidth();
      recalculateIconRenderersWidth();
      updateSeparatorLinePosition();
    }
    updatePreferredSize();
  }

  private void recalculateFoldingAreaWidth() {
    myLeftFoldingAreaWidth = myRightToLeft ? MIN_RIGHT_FOLDING_AREA_WIDTH : MIN_LEFT_FOLDING_AREA_WIDTH;
    myRightFoldingAreaWidth = myRightToLeft ? MIN_LEFT_FOLDING_AREA_WIDTH : MIN_RIGHT_FOLDING_AREA_WIDTH;
    // Layouting painters
    for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
      myLeftFoldingAreaWidth = Math.max(myLeftFoldingAreaWidth, painter.getLeftAreaWidth());
      myRightFoldingAreaWidth = Math.max(myRightFoldingAreaWidth, painter.getRightAreaWidth());
    }
  }

  public void addIconRenderer(EditorMessageIconRenderer renderer) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.addIconRenderer() should be called in eventDispatchThread";
    myIconRenderers.add(renderer);
    relayoutOnIconRendererChanges();
  }

  public void addAllIconRenderers(Collection<EditorMessageIconRenderer> renderers) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.addAllIconRenderers() should be called in eventDispatchThread";
    myIconRenderers.addAll(renderers);
    relayoutOnIconRendererChanges();
  }

  public void removeIconRenderer(EditorMessageIconRenderer renderer) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.removeIconRenderer() should be called in eventDispatchThread";
    if (myIconRenderers.remove(renderer)) {
      relayoutOnIconRendererChanges();
    }
  }

  public void removeIconRenderer(SNode snode, IconRendererType type) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.removeIconRenderer() should be called in eventDispatchThread";
    boolean wasModified = false;
    final SNodeReference ptr = snode == null ? null : snode.getReference();
    for (Iterator<EditorMessageIconRenderer> it = myIconRenderers.iterator(); it.hasNext(); ) {
      EditorMessageIconRenderer renderer = it.next();
      if (Objects.equals(renderer.getNodeReference(), ptr) && Objects.equals(renderer.getType(), type)) {
        it.remove();
        wasModified = true;
      }
    }
    if (wasModified) {
      relayoutOnIconRendererChanges();
    }
  }

  public void removeAllIconRenderers(Collection<? extends EditorMessageIconRenderer> renderers) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.removeAllIconRenderers() should be called in eventDispatchThread";
    if (myIconRenderers.removeAll(renderers)) {
      relayoutOnIconRendererChanges();
    }
  }

  public void removeAllIconRenderers(IconRendererType type) {
    assert SwingUtilities.isEventDispatchThread() : "LeftEditorHighlighter.removeAllIconRenderers() should be called in eventDispatchThread";
    boolean wasModified = false;
    for (Iterator<EditorMessageIconRenderer> it = myIconRenderers.iterator(); it.hasNext(); ) {
      EditorMessageIconRenderer renderer = it.next();
      if (renderer.getType() == type) {
        it.remove();
        wasModified = true;
      }
    }
    if (wasModified) {
      relayoutOnIconRendererChanges();
    }
  }

  private void recalculateIconRenderersWidth() {
    IconPositionCalculator ipc = new IconPositionCalculator(myIconRenderers, getIconRenderersOffset(), myEditorComponent);
    ipc.calculate();

    myRendererToCoord = ipc.getLocations();
    myMaxIconHeight = ipc.getMaxHeight();
    myIconRenderersWidth = ipc.getWidth();
  }

  private void recalculateTextColumnWidth() {
    int initialOffset = getTextColumnOffset();
    int offset = initialOffset;
    for (AbstractLeftColumn column : myLeftColumns) {
      column.setX(offset);
      column.relayout();
      offset += column.getWidth();
    }
    myTextColumnWidth = Math.max(MIN_LEFT_TEXT_WIDTH, offset - initialOffset);
  }

  private void updateSeparatorLinePosition() {
    myFoldingLineX = getFoldingAreaOffset() + myLeftFoldingAreaWidth;
  }

  private void updatePreferredSize() {
    int newWidth = myTextColumnWidth + myIconRenderersWidth + getFoldingAreaWidth();
    int newHeight = myEditorComponent.getPreferredSize().height;
    if (myWidth != newWidth || myHeight != newHeight) {
      myWidth = newWidth;
      myHeight = newHeight;
      firePreferredSizeChanged();
    }
  }

  private void firePreferredSizeChanged() {
    processComponentEvent(new ComponentEvent(this, ComponentEvent.COMPONENT_RESIZED));
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(myWidth + 1, myEditorComponent.getPreferredSize().height);
  }

  //used in plugin
  @SuppressWarnings("unused")
  public int getIconCoordinate(EditorMessageIconRenderer renderer) {
    return new IconPositionCalculator(myIconRenderers, getIconRenderersOffset(), myEditorComponent).getIconCoordinate(renderer);
  }

  @Override
  public String getMPSTooltipText(MouseEvent e) {
    if (isInFoldingArea(e)) {
      for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
        if (painter.getToolTipText() != null) {
          return painter.getToolTipText();
        }
      }
    } else if (isInTextArea(e)) {
      AbstractLeftColumn column = getTextColumnByX(e.getX());
      if (column != null) {
        return column.getTooltipText(e);
      }
    } else {
      EditorMessageIconRenderer iconRenderer = getIconRendererUnderMouse(e);
      if (iconRenderer != null) {
        return iconRenderer.getTooltipText();
      }
    }
    return null;
  }

  @Override
  protected void processMouseEvent(MouseEvent e) {
    switch (e.getID()) {
      case MouseEvent.MOUSE_PRESSED:
      case MouseEvent.MOUSE_RELEASED:
      case MouseEvent.MOUSE_CLICKED:
        if (isInFoldingArea(e)) {
          mousePressedInFoldingArea(e);
        } else if (isInTextArea(e)) {
          mousePressedInTextArea(e);
        } else {
          mousePressedInIconsArea(e);
        }
        if (!e.isConsumed() && e.getButton() == MouseEvent.BUTTON3 && e.getID() == MouseEvent.MOUSE_PRESSED) {
          ActionGroup actionGroup = ActionUtils.getDefaultGroup(MPSActions.EDITOR_LEFTPANEL_GROUP);
          if (actionGroup != null) {
            ActionPopupMenu popupMenu = ActionManager.getInstance().createActionPopupMenu(ActionPlaces.EDITOR_POPUP, actionGroup);
            popupMenu.getComponent().show(e.getComponent(), e.getX(), e.getY());
            e.consume();
          }
        }
    }

    // suppressing future event processig in case event was consumed by one of LeftHighlighter elements
    if (!e.isConsumed()) {
      super.processMouseEvent(e);
    }
  }

  private void mousePressedInIconsArea(MouseEvent e) {
    EditorMessageIconRenderer iconRenderer = getIconRendererUnderMouse(e);
    if (iconRenderer != null) {
      if (e.getButton() == MouseEvent.BUTTON3) {
        JPopupMenu popupMenu = iconRenderer.getPopupMenu();
        if (popupMenu != null && e.getID() == MouseEvent.MOUSE_PRESSED) {
          e.consume();
          Component component = e.getComponent();
          popupMenu.show(component == null ? myEditorComponent : component, e.getX(), e.getY());
        }
        return;
      }
      AnAction action = iconRenderer.getClickAction();
      if (e.getButton() == MouseEvent.BUTTON1 && action != null) {
        if (e.getID() == MouseEvent.MOUSE_CLICKED) {
          AnActionEvent actionEvent =
              new AnActionEvent(e, new LeftEditorHighlighterDataContext(myEditorComponent, iconRenderer), ICON_AREA, action.getTemplatePresentation(),
                                ActionManager.getInstance(), e.getModifiers());
          action.update(actionEvent);
          action.actionPerformed(actionEvent);
        }
        e.consume();
      }
    }
  }

  private void mousePressedInFoldingArea(MouseEvent e) {
    for (Iterator<AbstractFoldingAreaPainter> it = myFoldingAreaPainters.descendingIterator(); it.hasNext(); ) {
      AbstractFoldingAreaPainter painter = it.next();
      painter.mousePressed(e);
      if (e.isConsumed()) {
        break;
      }
    }
  }

  private void mousePressedInTextArea(MouseEvent e) {
    if (e.isConsumed()) {
      return;
    }
    AbstractLeftColumn column = getTextColumnByX(e.getX());
    if (column != null) {
      column.mousePressed(e);
      e.consume();
    }
  }

  private void mouseExitedFoldingArea(MouseEvent e) {
    if (myMouseIsInFoldingArea) {
      for (AbstractFoldingAreaPainter painter : myFoldingAreaPainters) {
        painter.mouseExited(e);
      }
    }
  }

  private void mouseMovedInFoldingArea(MouseEvent e) {
    myMouseIsInFoldingArea = true;
    for (Iterator<AbstractFoldingAreaPainter> it = myFoldingAreaPainters.descendingIterator(); it.hasNext(); ) {
      AbstractFoldingAreaPainter painter = it.next();
      painter.mouseMoved(e);
      if (e.isConsumed()) {
        break;
      }
    }
  }

  private void mouseExitedIconsArea(MouseEvent e) {
    if (!myMouseIsInFoldingArea && myRendererUnderMouse != null && !isInTextArea(e)) {
      setCursor(null);
    }
  }

  private void mouseMovedInIconsArea(MouseEvent e) {
    myMouseIsInFoldingArea = false;
    EditorMessageIconRenderer newRendererUnderMouse = getIconRendererUnderMouse(e);
    if (newRendererUnderMouse != null) {
      setCursor(newRendererUnderMouse.getMouseOverCursor());
    } else if (myRendererUnderMouse != null) {
      setCursor(null);
    }
    myRendererUnderMouse = newRendererUnderMouse;
  }

  private void mouseMovedInTextArea(MouseEvent e) {
    myMouseIsInFoldingArea = false;
    AbstractLeftColumn textColumn = getTextColumnByX(e.getX());
    if (textColumn != null) {
      setCursor(textColumn.getCursor(e));
    } else {
      setCursor(null);
    }
  }

  private boolean isInFoldingArea(MouseEvent e) {
    return isInside(getFoldingAreaOffset(), getFoldingAreaWidth(), e.getX());
  }

  private boolean isInTextArea(MouseEvent e) {
    return isInside(getTextColumnOffset(), myTextColumnWidth, e.getX());
  }

  private EditorMessageIconRenderer getIconRendererUnderMouse(MouseEvent e) {
    final int mouseX = e.getX();
    final int mouseY = e.getY();
    final EditorMessageIconRenderer[] theRenderer = new EditorMessageIconRenderer[]{null};
    myRendererToCoord.forEachEntry((renderer, location) -> {
      if (location.getY() <= mouseY && mouseY <= location.getY() + myMaxIconHeight) {
        if (location.getY() <= mouseY && mouseY <= location.getY() + renderer.getIcon().getIconHeight() &&
            location.getX() <= mouseX && mouseX <= location.getX() + renderer.getIcon().getIconWidth()) {
          theRenderer[0] = renderer;
          return false;
        }
      }
      return true;
    });
    return theRenderer[0];
  }

  private AbstractLeftColumn getTextColumnByX(int x) {
    for (AbstractLeftColumn column : myLeftColumns) {
      int columnX = column.getX();
      if (columnX <= x && columnX + column.getWidth() > x) {
        return column;
      }
    }
    return null;
  }

  private static class LeftEditorHighlighterDataContext implements DataContext {
    private final DataContext myEditorDataContext;
    private final SNode mySelectedNode;
    private final EditorCell myNodeCell;

    public LeftEditorHighlighterDataContext(@NotNull EditorComponent editorComponent, EditorMessageIconRenderer renderer) {
      myEditorDataContext = DataManager.getInstance().getDataContext(editorComponent);
      myNodeCell = renderer.getNodeCell(editorComponent);
      mySelectedNode = myNodeCell == null ? null : myNodeCell.getSNode();
    }

    @Override
    public Object getData(@NotNull @NonNls String dataId) {
      if (MPSCommonDataKeys.NODE.is(dataId)) {
        return mySelectedNode;
      }
      if (MPSEditorDataKeys.EDITOR_CELL.is(dataId)) {
        return myNodeCell;
      }
      return myEditorDataContext.getData(dataId);
    }
  }
}
