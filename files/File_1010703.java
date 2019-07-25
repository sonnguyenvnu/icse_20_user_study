/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.lang.editor.generator.internal;

import jetbrains.mps.lang.editor.cellProviders.PropertyCellContext;
import jetbrains.mps.lang.editor.menus.substitute.DefaultSubstituteMenuLookup;
import jetbrains.mps.lang.editor.menus.transformation.SubstituteActionsCollector;
import jetbrains.mps.lang.editor.menus.transformation.SubstituteItemsCollector;
import jetbrains.mps.nodeEditor.cellMenu.CellContext;
import jetbrains.mps.nodeEditor.cellMenu.SubstituteInfoPartExt;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.nodeEditor.menus.EditorMenuTraceImpl;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteAction;
import jetbrains.mps.openapi.editor.menus.EditorMenuDescriptor;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuLookup;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.smodel.action.AbstractChildNodeSetter;
import jetbrains.mps.smodel.action.IChildNodeSetter;
import jetbrains.mps.smodel.action.NodeSubstituteActionWrapper;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Igor Alshannikov
 * Date: Nov 29, 2006
 */
public abstract class AbstractCellMenuPart_ReplaceNode_CustomNodeConcept extends AbstractChildNodeSetter implements SubstituteInfoPartExt,
    IChildNodeSetter {
  @Override
  public List<SubstituteAction> createActions(CellContext cellContext, EditorContext editorContext) {
    SNode node = cellContext.get(PropertyCellContext.EDITED_NODE);
    SNode parent = node.getParent();
    if (parent == null) {
      return Collections.emptyList();
    }

    SubstituteMenuLookup lookup = new DefaultSubstituteMenuLookup(LanguageRegistry.getInstance(editorContext.getRepository()), getReplacementConcept());
    SContainmentLink containmentLink = node.getContainmentLink();
    assert containmentLink != null;
    EditorMenuTraceImpl editorMenuTrace = new EditorMenuTraceImpl();
    editorMenuTrace.pushTraceInfo();
    editorMenuTrace.setDescriptor(createEditorMenuDescriptor(cellContext, editorContext));
    List<TransformationMenuItem> transformationItems = new SubstituteItemsCollector(parent, node, containmentLink, null,  editorContext, lookup, editorMenuTrace).collect();
    try {
      return new SubstituteActionsCollector(parent, transformationItems, editorContext.getRepository()).collect().stream().map(action -> new NodeSubstituteActionWrapper(action) {
        @Override
        public SNode substitute(@Nullable EditorContext context, String pattern) {
          String selectedCellId = getSelectedCellId(context);

          super.substitute(context, pattern);
          if (context != null) {
            //hack to find substituted node
            select(editorContext, selectedCellId, getNewNode(parent, editorContext));
          }
          return null;
        }
      }).collect(Collectors.toList());
    } finally {
      editorMenuTrace.popTraceInfo();
    }
  }

  private SNode getNewNode(SNode parentNode, EditorContext editorContext) {
    SNode result = editorContext.getSelectedNode();
    if (result == null) {
      return null;
    }

    SNode resultParent = result.getParent();

    while (resultParent != null) {
      if (resultParent == parentNode) {
        return result;
      }
      result = resultParent;
      resultParent = resultParent.getParent();
    }
    return null;
  }


  private void select(@Nullable EditorContext context, String selectedCellId, SNode result) {
    if (selectedCellId != null && context != null && result != null) {
      EditorCell toSelect = context.getEditorComponent().findCellWithId(result, selectedCellId);
      if (toSelect != null) {
        context.flushEvents();
        context.getSelectionManager().setSelection(toSelect);
        if (context.getSelectedCell() instanceof EditorCell_Label) {
          context.getSelectedCell().end();
        }
      }
    }
  }

  @Nullable
  private String getSelectedCellId(@Nullable EditorContext context) {
    String selectedCellId = null;
    if (context != null && context.getSelectedCell() != null) {
      selectedCellId = context.getSelectedCell().getCellId();
    }
    return selectedCellId;
  }

  protected abstract SAbstractConcept getReplacementConcept();


  /**
   * implements IChildNodeSetter
   */
  @Override
  public SNode doExecute(SNode parentNode, SNode oldNode, SNode newNode, @Nullable EditorContext editorContext) {
    SNodeUtil.replaceWithAnother(oldNode, newNode);
    return newNode;
  }

  protected EditorMenuDescriptor createEditorMenuDescriptor(CellContext cellContext, EditorContext editorContext) {
    return null;
  }
}
