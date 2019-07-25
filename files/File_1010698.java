/*
 * Copyright 2003-2013 JetBrains s.r.o.
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
package jetbrains.mps.openapi.editor.cells;

import jetbrains.mps.openapi.editor.cells.traversal.CellTreeIterable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.util.Condition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Semen Alperovich
 * 03 05, 2013
 */
public class CellTraversalUtil {

  // no one can instantiate this class.
  private CellTraversalUtil() {
  }

  public static EditorCell getNextSibling(@NotNull EditorCell cell) {
    return getSibling(cell, true);
  }

  public static EditorCell getPrevSibling(@NotNull EditorCell cell) {
    return getSibling(cell, false);
  }

  private static EditorCell getSibling(@NotNull EditorCell cell, boolean forward) {
    final EditorCell_Collection parent = cell.getParent();
    if (parent == null) {
      return null;
    }

    Iterator<EditorCell> iterator = parent.iterator(cell, forward);
    if (iterator.hasNext()) {
      return iterator.next();
    }

    return null;
  }

  public static EditorCell getNextLeaf(@NotNull EditorCell cell) {
    EditorCell next = getNextSibling(cell);
    if (next != null) {
      return getFirstLeaf(next);
    }

    EditorCell_Collection parent = cell.getParent();
    if (parent != null) {
      return getNextLeaf(parent);
    }

    return null;
  }

  /**
   * Compares two cells.
   * Cell which is first is the editor is lesser.
   * <p/>
   * Comparing cells must have common parent.
   * Check getCommonParent(firstCell, secondCell) != null
   *
   * @param firstCell  a first cell to be compared.
   * @param secondCell a second cell to be compared.
   * @return -1, zero, or 1 as the first cell
   * is less than, equal to, or greater than the second cell.
   * @throws java.lang.IllegalArgumentException if the first cell and
   *                                            the second cell don't have common parent.
   */
  public static int compare(@NotNull EditorCell firstCell, @NotNull EditorCell secondCell) {
    if (firstCell.equals(secondCell)) {
      return 0;
    }

    List<EditorCell> firstCellAndParents = new ArrayList<>();
    EditorCell parent = firstCell;

    while (parent != null) {
      if (parent.equals(secondCell)) {
        return 1;
      }
      firstCellAndParents.add(parent);
      parent = parent.getParent();
    }

    EditorCell_Collection commonParent = secondCell.getParent();
    EditorCell secondChild = secondCell;
    while (commonParent != null && !firstCellAndParents.contains(commonParent)) {
      secondChild = commonParent;
      commonParent = commonParent.getParent();
    }

    if (commonParent == null) {
      throw new IllegalArgumentException(firstCell.toString() + " and " + secondCell.toString() + " don't have common parent");
    }

    if (commonParent.equals(firstCell)) {
      return -1;
    }

    EditorCell firstChild = firstCellAndParents.get(firstCellAndParents.indexOf(commonParent) - 1);

    if (findInNextSiblings(firstChild, secondChild)) {
      return -1;
    }

    if (findInNextSiblings(secondChild, firstChild)){
      return 1;
    }


    for (EditorCell cell : commonParent) {
      if (cell == firstChild) {
        return -1;
      }
      if (cell == secondChild) {
        return 1;
      }
    }

    return 0;
  }

  private static boolean findInNextSiblings(EditorCell firstChild, EditorCell secondChild) {
    EditorCell sibling = firstChild.getNextSibling();
    while (sibling != null) {
      if (sibling.equals(secondChild)) {
        return true;
      }
      sibling = sibling.getNextSibling();
    }
    return false;
  }

  public static EditorCell getNextLeaf(@NotNull EditorCell cell, @NotNull Condition<EditorCell> condition) {
    EditorCell current = getNextLeaf(cell);
    while (current != null) {
      if (condition.met(current)) {
        return current;
      }
      current = getNextLeaf(current);
    }
    return null;
  }

  public static EditorCell getPrevLeaf(@NotNull EditorCell cell) {
    EditorCell prev = getPrevSibling(cell);
    if (prev != null) {
      return getLastLeaf(prev);
    }

    EditorCell_Collection parent = cell.getParent();
    if (parent != null) {
      return getPrevLeaf(parent);
    }

    return null;
  }

  public static EditorCell getPrevLeaf(@NotNull EditorCell cell, @NotNull Condition<EditorCell> condition) {
    EditorCell current = getPrevLeaf(cell);
    while (current != null) {
      if (condition.met(current)) {
        return current;
      }
      current = getPrevLeaf(current);
    }
    return null;
  }

  @NotNull
  public static EditorCell getFirstLeaf(@NotNull EditorCell cell) {
    if (cell instanceof EditorCell_Collection) {
      return ((EditorCell_Collection) cell).isEmpty() ? cell : getFirstLeaf(((EditorCell_Collection) cell).firstCell());
    } else {
      return cell;
    }
  }

  @NotNull
  public static EditorCell getLastLeaf(@NotNull EditorCell cell) {
    if (cell instanceof EditorCell_Collection) {
      return ((EditorCell_Collection) cell).isEmpty() ? cell : getLastLeaf(((EditorCell_Collection) cell).lastCell());
    } else {
      return cell;
    }
  }

  public static EditorCell getCommonParent(@NotNull EditorCell firstCell, @NotNull EditorCell secondCell) {
    List<EditorCell> firstParents = getParents(firstCell, true);
    List<EditorCell> secondParents = getParents(secondCell, true);
    for (EditorCell cell : firstParents) {
      if (secondParents.contains(cell)) {
        return cell;
      }
    }
    return null;
  }

  public static List<EditorCell> getParents(@NotNull EditorCell cell, boolean includeThis) {
    List<EditorCell> parents = new ArrayList<>();
    EditorCell tempCell = includeThis ? cell : cell.getParent();
    while (tempCell != null) {
      parents.add(tempCell);
      tempCell = tempCell.getParent();
    }
    return parents;
  }

  public static boolean isAncestor(@NotNull EditorCell ancestor, @NotNull EditorCell child) {
    EditorCell_Collection parent = child.getParent();
    while (parent != null) {
      if (parent.equals(ancestor)) {
        return true;
      }
      parent = parent.getParent();
    }
    return false;
  }

  public static boolean isAncestorOrEquals(@NotNull EditorCell ancestor, @NotNull EditorCell child) {
    return ancestor.equals(child) || isAncestor(ancestor, child);
  }

  public static EditorCell_Collection getFoldedParent(@NotNull EditorCell cell) {
    for (EditorCell_Collection parent = cell.getParent(); parent != null; parent = parent.getParent()) {
      if (parent.isCollapsed()) {
        return parent;
      }
    }
    return null;
  }

  /**
   * Returns a {@link CellTreeIterable} that iterates over the subtree of {@code root}, in preorder, starting with {@code start}.
   *
   * @param root    the root of the subtree to iterate, {@code null} to iterate the whole tree that {@code start} is part of.
   * @param start   the first node to visit
   * @param forward {@code true} to visit children of a cell from first to last, {@code false} for the reverse order.
   * @return a new instance of {@link CellTreeIterable}
   */
  public static CellTreeIterable iterateTree(@Nullable EditorCell root, @NotNull EditorCell start, boolean forward) {
    return new CellTreeIterable(root, start, forward);
  }

  @NotNull
  public static EditorCell getContainingBigCell(@NotNull EditorCell cell) {
    while (!cell.isBig() && cell.getParent() != null) {
      cell = cell.getParent();
    }
    return cell;
  }

  public static boolean isCellUnderFoldedCollection(@NotNull EditorCell cell) {
    // traversing all foldable cells up to the root and checking if this component cell is included
    // into visible cells sub-tree
    jetbrains.mps.openapi.editor.cells.EditorCell child = cell;
    EditorCell_Collection parent = cell.getParent();
    while (parent != null) {
      if (parent.isFoldable()) {
        boolean isUnderFolded = true;
        for (jetbrains.mps.openapi.editor.cells.EditorCell editorCell : parent) {
          if (editorCell == child) {
            isUnderFolded = false;
            break;
          }
        }
        if (isUnderFolded) {
          return true;
        }
      }
      child = parent;
      parent = parent.getParent();
    }
    return false;
  }
}
