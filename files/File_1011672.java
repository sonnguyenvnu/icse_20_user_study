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
package jetbrains.mps.ide.devkit.cellExplorer.detailTree;

import jetbrains.mps.ide.ui.tree.MPSTree;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.awt.Font;

public class TreeBuilder {
  private final MPSTreeNode myRoot;

  public TreeBuilder() {
    myRoot = new MPSTreeNode();
  }

  public TreeBuilder icon(Icon icon) {
    myRoot.setIcon(icon);
    return this;
  }

  public TreeBuilder text(String text) {
    myRoot.setText(text);
    myRoot.setNodeIdentifier(text == null ? "null" : text.replace(MPSTree.TREE_PATH_SEPARATOR, " "));
    return this;
  }

  private TreeBuilder fontStyle(int fontStyle) {
    myRoot.setFontStyle(fontStyle);
    return this;
  }

  public TreeBuilder subtree() {
    TreeBuilder subtreeBuilder = new TreeBuilder();
    subtreeBuilder.fontStyle(Font.BOLD);
    myRoot.add(subtreeBuilder.build());
    return subtreeBuilder;
  }

  public TreeBuilder property(String name, Object value) {
    return property(null, name, value);
  }

  public TreeBuilder property(@Nullable Icon icon, String name, Object value) {
    TextTreeNode newChild = new TextTreeNode(propertyText(name, String.valueOf(value)));
    if (icon != null) newChild.setIcon(icon);
    myRoot.add(newChild);
    return this;
  }

  public MPSTreeNode build() {
    return myRoot;
  }

  @NotNull
  private static String propertyText(String name, String value) {
    return "<html>" + name + " = <b>" + StringUtil.escapeXml(value) + "</b>";
  }
}
