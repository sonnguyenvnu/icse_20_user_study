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
package jetbrains.mps.ide.devkit.cellExplorer.detailTree.contributors;

import jetbrains.mps.icons.MPSIcons.CellExplorer;
import jetbrains.mps.ide.devkit.cellExplorer.detailTree.TreeBuilder;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.style.Style;
import jetbrains.mps.openapi.editor.style.StyleAttribute;

/**
 * Adds
 */
public class StylesContributor implements CellTreeContributor {
  @Override
  public void contribute(EditorCell cell, TreeBuilder builder) {
    Style style = cell.getStyle();
    if (style == null) return;

    TreeBuilder subtree = builder.subtree().icon(CellExplorer.CellDefault).text("Styles");

    for (StyleAttribute attribute : style.getSpecifiedAttributes()) {
      String name = attribute.getName();
      Object value = style.get(attribute);

      subtree.property(name, value);
    }
  }
}
