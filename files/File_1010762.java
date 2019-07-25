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
package jetbrains.mps.nodeEditor;

import jetbrains.mps.errors.MessageStatus;
import jetbrains.mps.errors.item.NodeFeatureReportItem;
import jetbrains.mps.errors.item.NodeReportItem;
import jetbrains.mps.ide.util.ColorAndGraphicsUtil;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Error;
import jetbrains.mps.nodeEditor.cells.EditorCell_Property;
import jetbrains.mps.nodeEditor.messageTargets.EditorMessageWithTarget;
import jetbrains.mps.openapi.editor.ColorConstants;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.message.EditorMessageOwner;
import jetbrains.mps.openapi.editor.message.SimpleEditorMessage;
import jetbrains.mps.openapi.editor.style.StyleRegistry;
import jetbrains.mps.smodel.SNodeUtil;
import org.jetbrains.mps.openapi.model.SNode;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HighlighterMessage extends EditorMessageWithTarget {
  private final NodeReportItem myReportItem;

  public static Color getMessageColor(MessageStatus messageStatus) {
    if (messageStatus == MessageStatus.ERROR) {
      return new Color(ColorConstants.ERROR);
    }
    if (messageStatus == MessageStatus.WARNING) {
      return new Color(StyleRegistry.getInstance().isDarkTheme() ? ColorConstants.WARNING_DARK : ColorConstants.WARNING);
    }
    if (messageStatus == MessageStatus.OK) {
      return new Color(ColorConstants.OK);
    }
    return Color.BLACK;
  }

  public HighlighterMessage(EditorMessageOwner owner, NodeReportItem reportItem, SNode node) {
    super(node, reportItem.getSeverity(), reportItem.getMessageTarget(), getMessageColor(reportItem.getSeverity()),
          reportItem.getMessage(), owner);
    if (!node.getReference().equals(reportItem.getNode())) {
      throw new IllegalStateException();
    }
    myReportItem = reportItem;
  }

  public NodeReportItem getReportItem() {
    return myReportItem;
  }

  @Override
  public boolean sameAs(SimpleEditorMessage message) {
    if (!(message instanceof HighlighterMessage)) {
      return false;
    }
    return super.sameAs(message);
  }

  @Override
  public EditorCell getCellForParentNodeInMainEditor(EditorComponent editor) {
    return super.getCellForParentNodeInMainEditor(editor);
  }

  @Override
  public boolean isBackground() {
    return false;
  }

  @Override
  public boolean showInGutter() {
    return getStatus() != MessageStatus.OK;
  }

  @Override
  public void paint(Graphics g, EditorComponent editorComponent, EditorCell cell) {
    if (cell != null) {
      for (Region nextRegion : getHighlightedRegions(cell)) {
        nextRegion.drawWaveUnderCell(g, getColor());
      }
    }
  }

  private List<Region> getHighlightedRegions(EditorCell cell) {
    Deque<Iterator<EditorCell>> iteratorsStack = new LinkedList<>();
    if (cell instanceof EditorCell_Collection) {
      iteratorsStack.addLast(((EditorCell_Collection) cell).iterator());
    } else {
      iteratorsStack.addLast(Collections.singletonList(cell).iterator());
    }

    Region anchorRegion = null;
    AnchorCellType anchorCellType = AnchorCellType.NONE;
    List<Region> regions = new ArrayList<>();
    boolean insidePrefix = true;
    while (!iteratorsStack.isEmpty()) {
      Iterator<EditorCell> currentIterator = iteratorsStack.peekLast();
      if (!currentIterator.hasNext()) {
        iteratorsStack.removeLast();
        continue;
      }
      EditorCell nextCell = currentIterator.next();
      if (nextCell.getSNode() != cell.getSNode()) {
        insidePrefix = false;
        continue;
      }
      if (nextCell instanceof EditorCell_Collection) {
        iteratorsStack.addLast(((EditorCell_Collection) nextCell).iterator());
      } else {
        Region nextRegion = new Region(nextCell);
        regions.add(nextRegion);
        AnchorCellType nextCellType = getAnchorCellType(nextCell, insidePrefix);
        if (nextCellType.ordinal() < anchorCellType.ordinal()) {
          anchorRegion = nextRegion;
          anchorCellType = nextCellType;
        }
      }
    }

    if (anchorRegion != null) {
      int anchorRegionIndex = regions.indexOf(anchorRegion);
      assert anchorRegionIndex != -1;

      Region result = anchorRegion;
      for (int i = anchorRegionIndex + 1; i < regions.size() && result.canMerge(regions.get(i)); i++) {
        result = result.merge(regions.get(i));
      }
      for (int i = anchorRegionIndex - 1; i >= 0 && result.canMerge(regions.get(i)); i--) {
        result = result.merge(regions.get(i));
      }
      return Collections.singletonList(result);
    }

    for (int i = 0; i < regions.size(); ) {
      if (i > 0 && regions.get(i - 1).canMerge(regions.get(i))) {
        regions.set(i - 1, regions.get(i - 1).merge(regions.get(i)));
        regions.remove(i);
      } else {
        i++;
      }
    }

    return highlightContainingCollection(regions) ? Collections.singletonList(new Region(cell)) : regions;
  }

  public static AnchorCellType getAnchorCellType(EditorCell cell, boolean prefixCell) {
    if (cell.getCellContext() != null && cell.getCellContext().getPropertyInfo() != null) {
      if (SNodeUtil.property_INamedConcept_name.getName().equals(cell.getCellContext().getPropertyInfo().getProperty().getName())) {
        return AnchorCellType.NAME;
      }
    }

    if (cell instanceof EditorCell_Property && prefixCell) {
      return AnchorCellType.PROPERTY;
    } else if (cell instanceof EditorCell_Error && prefixCell) {
      return AnchorCellType.ERROR;
    } else if (cell instanceof EditorCell_Constant && prefixCell) {
      return AnchorCellType.CONSTANT;
    } else {
      return AnchorCellType.NONE;
    }
  }

  /**
   * return true if all regions are located on the same "line", so in this case we will underline
   * containing collection instead of drawing separate errors.
   * <p/>
   * In case of multi-line cells we are still drawing messages as merged cell regions in order to try to highlight editor lines..
   */
  private boolean highlightContainingCollection(List<Region> regions) {
    return regions.size() < 2;
  }

  private class Region {
    private int myX;
    private int myLeftInset;
    private int myWidth;
    private int myEffectiveWidth;
    private int myY;

    public Region(EditorCell cell) {
      this(cell.getX(), cell.getY() + cell.getHeight(), cell.getLeftInset(), cell.getEffectiveWidth(), cell.getWidth());
    }

    private Region(int x, int y, int leftInset, int effectiveWidth, int width) {
      myX = x;
      myY = y;
      myLeftInset = leftInset;
      myEffectiveWidth = effectiveWidth;
      myWidth = width;
    }

    public boolean canMerge(Region another) {
      return myY == another.myY && (myX + myWidth == another.myX || myX == another.myX + another.myWidth);
    }

    public Region merge(Region another) {
      assert canMerge(another);
      int y = myY;
      boolean isFirst = myX + myWidth == another.myX;
      int x = isFirst ? myX : another.myX;
      int leftInset = isFirst ? myLeftInset : another.myLeftInset;
      int width = myWidth + another.myWidth;
      int effectiveWidth = isFirst ? myWidth - myLeftInset + another.myLeftInset + another.myEffectiveWidth :
                           another.myWidth - another.myLeftInset + myLeftInset + myEffectiveWidth;
      return new Region(x, y, leftInset, effectiveWidth, width);
    }

    public void drawWaveUnderCell(Graphics g, Color color) {
      g.setColor(color);
      ColorAndGraphicsUtil.drawWave(g, myX + myLeftInset, myX + myLeftInset + myEffectiveWidth, myY - ColorAndGraphicsUtil.WAVE_HEIGHT);
    }
  }

  enum AnchorCellType {
    NAME, PROPERTY, ERROR, CONSTANT, NONE;

    AnchorCellType() {
    }
  }
}
