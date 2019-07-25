/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.selectionRestoring;

import jetbrains.mps.nodeEditor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.util.IterableUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChildCellLocator implements CellLocator {

  @NotNull
  private final CellLocator myParentLocator;
  private final int myChildIndex;

  public ChildCellLocator(@NotNull CellLocator parentLocator, int childIndex) {
    myParentLocator = parentLocator;
    myChildIndex = childIndex;
  }

  @Nullable
  @Override
  public EditorCell locate(@NotNull EditorContext editorContext) {
    EditorCell parent = myParentLocator.locate(editorContext);
    if (!(parent instanceof EditorCell_Collection)) {
      return null;
    }

    EditorCell_Collection parentCollection = (EditorCell_Collection) parent;
    if (myChildIndex < 0 || myChildIndex >= parentCollection.getCellsCount()) {
      return null;
    }

    return IterableUtil.get(parentCollection, myChildIndex);
  }
}
