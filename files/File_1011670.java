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
package jetbrains.mps.ide.devkit.cellExplorer.detailTree.contributors;

import jetbrains.mps.icons.MPSIcons.CellExplorer;
import jetbrains.mps.ide.devkit.cellExplorer.detailTree.TreeBuilder;
import jetbrains.mps.ide.icons.GlobalIconManager;
import jetbrains.mps.nodeEditor.cells.EditorCell_Image;
import jetbrains.mps.openapi.editor.cells.CellLayout;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.cells.EditorCell_Label;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import javax.swing.Icon;

/**
 * Contributes 'Basic Properties' subtree with basic cell properties (ID, position, etc.)
 */
public class BasicPropertiesContributor implements CellTreeContributor {
  @Override
  public void contribute(EditorCell cell, TreeBuilder builder) {
    TreeBuilder basicProperties = builder.subtree().icon(CellExplorer.CellProperty).text("Basic Properties");

    CellLayout cellLayout = getCellLayout(cell);
    if (cellLayout != null) {
      basicProperties.property(CellExplorer.Cells, "cellLayout", cellLayout);
    }

    String text = getCellText(cell);
    if (text != null) {
      basicProperties.property("text", text);
    }

    Icon icon = getCellIcon(cell);
    if (icon != null) {
      basicProperties.property(icon, "icon", icon);
    }

    SNode node = cell.getSNode();
    if (node != null) {
      cell.getEditorComponent().getEditorContext().getRepository().getModelAccess().runReadAction(() -> {
        basicProperties.property(GlobalIconManager.getInstance().getIconFor(node), "node", toString(node));
      });
    }

    basicProperties
        .property("id", cell.getCellId())
        .property("class", cell.getClass().getName())
        .property("role", cell.getSRole() == null ? "<no role>" : cell.getSRole().getName())
        .property("x", cell.getX())
        .property("y", cell.getY())
        .property("width", cell.getWidth())
        .property("effectiveWidth", cell.getEffectiveWidth())
        .property("height", cell.getHeight())
        .property("baseline", cell.getBaseline())
        .property("ascent", cell.getAscent())
        .property("descent", cell.getDescent())
        .property("leftGap", cell.getLeftGap())
        .property("selectable", cell.isSelectable())
        .property("referenceCell", cell.isReferenceCell())
        .property("transformationMenuLookup", cell.getTransformationMenuLookup())
        .property("substituteInfo", cell.getSubstituteInfo());
  }

  private Icon getCellIcon(EditorCell cell) {
    if (cell instanceof EditorCell_Image) return ((EditorCell_Image) cell).getIcon();
    return null;
  }

  private CellLayout getCellLayout(EditorCell cell) {
    if (cell instanceof EditorCell_Collection) return ((EditorCell_Collection) cell).getCellLayout();
    return null;
  }

  private String getCellText(EditorCell cell) {
    if (cell instanceof EditorCell_Label) return ((EditorCell_Label) cell).getText();
    return null;
  }

  @NotNull
  private static String toString(SNode node) {
    String nodeName = node.getName();
    String name = nodeName != null ? nodeName : "<no name>";
    return name + " (" + node.getConcept().getName() + ")";
  }
}
