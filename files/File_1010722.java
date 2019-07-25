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
package jetbrains.mps.nodeEditor.braces;

import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.style.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Highlights the first and last content cells of a collection with {@link StyleAttributes#SHOW_BOUNDARIES_IN} style.
 */
class CollectionBracesFinder implements BracesFinder.Algorithm {
  @Nullable
  @Override
  public BracePair find(@NotNull EditorCell cellToSelect) {
    EditorCell_Collection collection = getCollectionToHighlight(cellToSelect);

    if (collection == null) {
      return null;
    }

    return new BracePair(collection.firstContentCell(), collection.lastContentCell(), collection.getStyle().get(StyleAttributes.SHOW_BOUNDARIES_IN));
  }

  @Nullable
  private EditorCell_Collection getCollectionToHighlight(@NotNull EditorCell editorCell) {
    if (editorCell instanceof EditorCell_Collection && isCollectionToHighlight(editorCell)) {
      return (EditorCell_Collection) editorCell;
    }

    if (isFirstChild(editorCell)) {
      EditorCell_Collection result = getCollectionToHighlightFromFirstChild(editorCell);
      if (result != null) {
        return result;
      }
    }

    if (isLastChild(editorCell)) {
      return getCollectionToHighlightFromLastChild(editorCell);
    }

    return null;
  }

  private boolean isCollectionToHighlight(@NotNull EditorCell editorCell) {
    Style editorCellStyle = editorCell.getStyle();
    return editorCellStyle.get(StyleAttributes.SHOW_BOUNDARIES_IN) != null;
  }

  @Nullable
  private EditorCell_Collection getCollectionToHighlightFromLastChild(@NotNull EditorCell editorCell) {
    EditorCell_Collection collection = editorCell instanceof EditorCell_Collection ? (EditorCell_Collection) editorCell : editorCell.getParent();

    while (collection != null) {
      if (isCollectionToHighlight(collection)) {
        return collection;
      }

      if (!isLastChild(collection)) {
        break;
      }
      collection = collection.getParent();
    }

    return null;
  }

  @Nullable
  private EditorCell_Collection getCollectionToHighlightFromFirstChild(@NotNull EditorCell editorCell) {
    EditorCell_Collection collection = editorCell instanceof EditorCell_Collection ? (EditorCell_Collection) editorCell : editorCell.getParent();

    while (collection != null) {
      if (isCollectionToHighlight(collection)) {
        return collection;
      }

      if (!isFirstChild(collection)) {
        break;
      }
      collection = collection.getParent();
    }

    return null;
  }

  private boolean isFirstChild(EditorCell cell) {
    EditorCell_Collection parent = cell.getParent();
    return parent != null && (parent.firstCell() == cell || parent.firstContentCell() == cell);
  }

  private boolean isLastChild(EditorCell cell) {
    EditorCell_Collection parent = cell.getParent();
    return parent != null && (parent.lastCell() == cell || parent.lastContentCell() == cell);
  }
}
