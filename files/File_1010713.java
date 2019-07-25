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
package jetbrains.mps.lang.editor.menus.transformation;

import jetbrains.mps.nodeEditor.cellMenu.CompletionActionItemAsSubstituteAction;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.menus.transformation.ActionItem;
import jetbrains.mps.openapi.editor.menus.transformation.CompletionActionItem;
import jetbrains.mps.openapi.editor.menus.transformation.SubMenu;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItemVisitor;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simon
 */
public class SubstituteActionsCollector {
  private final SNode mySourceNode;
  private final List<TransformationMenuItem> myMenuItems;
  private final SRepository myRepository;

  public SubstituteActionsCollector(SNode sourceNode, List<TransformationMenuItem> menuItems, SRepository repository) {
    mySourceNode = sourceNode;
    myMenuItems = menuItems;
    myRepository = repository;
  }

  public List<SubstituteAction> collect() {
    ArrayList<SubstituteAction> outItems = new ArrayList<>();
    collectItems(myMenuItems, outItems);
    return outItems;
  }

  private void collectItems(List<TransformationMenuItem> inItems, final List<SubstituteAction> outItems) {
    inItems.forEach(menuItem -> menuItem.accept(new TransformationMenuItemVisitor<Void>() {
      @Override
      public Void visit(ActionItem actionItem) {
        if (actionItem instanceof CompletionActionItem) {
          outItems.add(new CompletionActionItemAsSubstituteAction(((CompletionActionItem) actionItem), mySourceNode, myRepository));
        }
        return null;
      }

      @Override
      public Void visit(SubMenu subMenu) {
        collectItems(subMenu.getItems(), outItems);
        return null;
      }
    }));
  }
}
