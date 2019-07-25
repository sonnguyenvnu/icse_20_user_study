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
package jetbrains.mps.nodeEditor.assist;

import jetbrains.mps.openapi.editor.assist.ContextAssistant;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.ModelAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for figuring out which menu to show and where, and showing/hiding the assistant.
 */
class EditorContextAssistants {
  private final ModelAccess myModelAccess;
  private final SelectionMenuProvider myMenuProvider;
  private final SelectionManager mySelectionManager;
  private final List<ContextAssistant> myContextAssistants = new ArrayList<>();

  private ContextAssistant myActiveAssistant;
  private List<TransformationMenuItem> myActiveMenuItems;

  EditorContextAssistants(SelectionMenuProvider menuProvider, SelectionManager selectionManager, ModelAccess modelAccess) {
    myModelAccess = modelAccess;
    myMenuProvider = menuProvider;
    mySelectionManager = selectionManager;
  }

  public ContextAssistant getActiveAssistant() {
    return myActiveAssistant;
  }

  List<TransformationMenuItem> getActiveMenuItems() {
    return myActiveMenuItems;
  }

  void hideMenu() {
    if (myActiveAssistant == null) {
      return;
    }
    myActiveAssistant.hideMenu();
    myActiveAssistant = null;
    myActiveMenuItems = null;
  }

  void update() {
    myModelAccess.runReadAction(() -> {
      Selection selection = mySelectionManager.getSelection();
      ContextAssistant newAssistant = selection == null ? null : new AncestorOrSmallCellContextAssistantFinder(myContextAssistants).findAssistant(selection);

      List<TransformationMenuItem> newItems = newAssistant == null ? Collections.emptyList() : myMenuProvider.getMenuItems(selection);
      hideMenu();

      if (newAssistant == null || newItems.isEmpty()) {
        return;
      }

      showMenu(newAssistant, newItems);
    });
  }

  private void showMenu(@NotNull ContextAssistant newAssistant, @NotNull List<TransformationMenuItem> newItems) {
    assert myActiveAssistant == null;
    myActiveAssistant = newAssistant;
    myActiveMenuItems = newItems;
    myActiveAssistant.showMenu(newItems);
  }

  boolean hasRegisteredAssistants() {
    return myContextAssistants.size() > 0;
  }

  void register(ContextAssistant assistant) {
    myContextAssistants.add(assistant);
  }

  void unregister(ContextAssistant assistant) {
    boolean wasRemoved = myContextAssistants.remove(assistant);
    assert wasRemoved : "trying to unregister not registered context assistant";
    if (assistant == myActiveAssistant) {
      hideMenu();
    }
  }
}
