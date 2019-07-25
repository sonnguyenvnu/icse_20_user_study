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
package jetbrains.mps.nodeEditor;

import jetbrains.mps.editor.runtime.impl.CellUtil;
import jetbrains.mps.editor.runtime.impl.cellActions.CellAction_DeleteEasily;
import jetbrains.mps.editor.runtime.impl.cellActions.CellAction_DeleteSPropertyOrNode;
import jetbrains.mps.editor.runtime.impl.cellActions.CellAction_DeleteSmart;
import jetbrains.mps.editor.runtime.impl.cellMenu.EnumSPropertySubstituteInfo;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.internal.collections.runtime.IterableUtils;
import jetbrains.mps.lang.editor.cellProviders.RefNodeListHandler;
import jetbrains.mps.lang.editor.cellProviders.SingleRoleCellProvider;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.nodeEditor.cellActions.CellAction_DeleteNode;
import jetbrains.mps.nodeEditor.cellActions.CellAction_DeleteNode.DeleteDirection;
import jetbrains.mps.nodeEditor.cellActions.CellAction_DeleteOnErrorSReference;
import jetbrains.mps.nodeEditor.cellActions.CellAction_DeleteSReference;
import jetbrains.mps.nodeEditor.cellLayout.CellLayout_Indent;
import jetbrains.mps.nodeEditor.cellMenu.BooleanSPropertySubstituteInfo;
import jetbrains.mps.nodeEditor.cellMenu.DefaultSChildSubstituteInfo;
import jetbrains.mps.nodeEditor.cellMenu.SReferenceSubstituteInfo;
import jetbrains.mps.nodeEditor.cellProviders.AbstractCellListHandler;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Error;
import jetbrains.mps.nodeEditor.cells.EditorCell_Property;
import jetbrains.mps.nodeEditor.cells.SPropertyAccessor;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.DefaultSubstituteInfo;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.menus.transformation.SNodeLocation.FromNode;
import jetbrains.mps.openapi.editor.menus.transformation.SNodeLocation.FromParentAndLink;
import jetbrains.mps.openapi.editor.menus.transformation.SPropertyInfo;
import jetbrains.mps.openapi.editor.update.AttributeKind;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.adapter.structure.types.SPrimitiveTypes;
import jetbrains.mps.smodel.presentation.ReferenceConceptUtil;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SDataType;
import org.jetbrains.mps.openapi.language.SEnumeration;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

/**
 * Semen Alperovich
 * 04 04, 2013
 */
public class DefaultEditor extends AbstractDefaultEditor {

  public DefaultEditor(@NotNull SConcept concept, boolean reflectiveRoot) {
    super(concept, reflectiveRoot);
  }

  protected void init() {
    assert getNode() != null && getConcept() != null;
    for (SProperty sProperty : getConcept().getProperties()) {
      if (!sProperty.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        addProperty(sProperty);
      }
    }

    for (SReferenceLink sReferenceLink : getConcept().getReferenceLinks()) {
      if (!sReferenceLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        addReferenceLink(sReferenceLink);
      }
    }

    for (SContainmentLink sContainmentLink : getConcept().getContainmentLinks()) {
      if (!sContainmentLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        addContainmentLink(sContainmentLink);
      }
    }
    super.init();
  }

  @Override
  protected void addPropertyCell(SProperty property) {
    getCellFactory().pushCellContext();
    try {
      getCellFactory().setPropertyInfo(new SPropertyInfo(getNode(), property));
      EditorCell_Property editorCell = new EditorCell_Property(getEditorContext(), new SPropertyAccessor(getNode(), property, false, false), getNode());
      getUpdateSession().registerCleanDependency(editorCell, new Pair<>(new SNodePointer(getNode()), property.getName()));
      editorCell.setDefaultText("<no " + property.getName() + ">");
      if (editorCell.getCellId() == null) {
        editorCell.setCellId("property_" + property);
      }
      editorCell.setAction(CellActionType.DELETE, new CellAction_DeleteSPropertyOrNode(getNode(), property, DeleteDirection.FORWARD));
      editorCell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteSPropertyOrNode(getNode(), property, DeleteDirection.BACKWARD));
      final SDataType type = property.getType();
      if (type == SPrimitiveTypes.BOOLEAN) {
        editorCell.setSubstituteInfo(new BooleanSPropertySubstituteInfo(getNode(), property, getEditorContext()));
      }
      if (type instanceof SEnumeration) {
        editorCell.setSubstituteInfo(new EnumSPropertySubstituteInfo(getNode(), property, getEditorContext()));
      }
      addCellWithRole(IterableUtils.first(AttributeOperations.getPropertyAttributes(getNode(), property)), AttributeKind.PROPERTY, editorCell);
    } finally {
      getCellFactory().popCellContext();
    }
  }

  @Override
  protected void addChildCell(final SContainmentLink link) {
    if (link.isMultiple()) {
      AbstractCellListHandler handler = new ListHandler(getNode(), link, getEditorContext());
      EditorCell editorCell = handler.createCells(new CellLayout_Indent(), false);
      editorCell.setSRole(handler.getElementSRole());
      addStyle(editorCell, StyleAttributes.INDENT_LAYOUT_CHILDREN_NEWLINE);
      setIndent(editorCell);
      addCell(editorCell);
    } else {
      SingleRoleCellProvider provider = new SingleRoleCellProvider(link, getEditorContext()) {

        @Override
        protected EditorCell createEmptyCell() {

          getCellFactory().pushCellContext();
          getCellFactory().setNodeLocation(new FromParentAndLink(getNode(), myContainmentLink));
          try {
            EditorCell emptyCell = super.createEmptyCell();
            emptyCell.setSubstituteInfo(new DefaultSChildSubstituteInfo(getNode(), link, getEditorContext()));
            emptyCell.setSRole(link);
            emptyCell.setCellId("empty_" + link.getName());
            return emptyCell;
          } finally {
            getCellFactory().popCellContext();
          }
        }

        @Override
        public EditorCell createChildCell(SNode child) {
          getCellFactory().pushCellContext();
          getCellFactory().setNodeLocation(new FromNode(child));
          try {
            EditorCell cell = super.createChildCell(child);
            cell.setAction(CellActionType.DELETE, new CellAction_DeleteSmart(getNode(), myContainmentLink, child));
            cell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteSmart(getNode(), myContainmentLink, child));
            cell.setSubstituteInfo(new DefaultSChildSubstituteInfo(getNode(), child, link, getEditorContext()));
            if (cell.getSRole() == null) {
              cell.setSRole(link);
            }
            return cell;
          } finally {
            getCellFactory().popCellContext();
          }
        }

        @NotNull
        @Override
        public SNode getNode() {
          return DefaultEditor.this.getNode();
        }
      };
      EditorCell cell = provider.createCell();
      setIndent(cell);
      addCell(cell);
    }
  }

  @Override
  protected void addReferenceCell(final SReferenceLink referenceLink) {
    SReference reference = getNode().getReference(referenceLink);
    if (reference == null) {
      String noTargetText = "<no " + referenceLink.getName() + ">";
      jetbrains.mps.nodeEditor.cells.EditorCell_Label noRefCell = referenceLink.isOptional() ?
                                                                  new EditorCell_Constant(getEditorContext(), getNode(), "") :
                                                                  new EditorCell_Error(getEditorContext(), getNode(), noTargetText);
      noRefCell.setText("");
      noRefCell.setEditable(true);
      noRefCell.setDefaultText(noTargetText);

      noRefCell.setAction(CellActionType.DELETE, new CellAction_DeleteEasily(getNode(), DeleteDirection.FORWARD));
      noRefCell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteEasily(getNode(), DeleteDirection.BACKWARD));

      noRefCell.setCellId("empty_" + referenceLink.getName());
      noRefCell.setReferenceCell(true);
      noRefCell.setSubstituteInfo(new SReferenceSubstituteInfo(noRefCell, referenceLink));
      noRefCell.setSRole(referenceLink);

      setIndent(noRefCell);
      addCell(noRefCell);
    } else {
      final SNode referentNode = reference.getTargetNode();
      if (referentNode == null || referentNode.getModel() == null) {
        //todo do we need this?
        String resolveInfo = ((jetbrains.mps.smodel.SReference) reference).getResolveInfo();
        EditorCell errorCell = createErrorCell(resolveInfo != null ? resolveInfo : "?" + referenceLink.getName() + "?", referenceLink);
        errorCell.setCellId("error_" + referenceLink.getName());
        addCell(errorCell);
      } else {
        EditorCell cell = getUpdateSession().updateReferencedNodeCell(() -> createReferentEditorCell(getEditorContext(), referenceLink, referentNode), referentNode, referenceLink);
        //todo what is that?
        CellUtil.setupIDeprecatableStyles(referentNode, cell);
        setSemanticNodeToCells(cell, getNode());

        //todo rewrite cell actions
        cell.setAction(CellActionType.DELETE, new CellAction_DeleteSReference(getNode(), referenceLink));
        cell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteSReference(getNode(), referenceLink));
        cell.setSubstituteInfo(new SReferenceSubstituteInfo(cell, referenceLink));
        if (cell.getCellId() == null) {
          cell.setCellId("reference_" + referenceLink.getName());
        }
        //todo attributes
        addCellWithRole(IterableUtils.first(AttributeOperations.getLinkAttributes(getNode(), referenceLink)), AttributeKind.REFERENCE, cell);
      }
    }

  }


  protected EditorCell createErrorCell(String error, SReferenceLink link) {
    EditorCell_Error errorCell = new EditorCell_Error(getEditorContext(), getNode(), error, true);
    if (!link.isOptional()) {
      if (ReferenceConceptUtil.getCharacteristicReference(getNode().getConcept()) != null) {
        errorCell.setAction(CellActionType.DELETE, new CellAction_DeleteNode(getNode(), DeleteDirection.FORWARD));
        errorCell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteNode(getNode(), DeleteDirection.BACKWARD));
        return errorCell;
      }
    }

    //todo rewrite cell actions
    errorCell.setAction(CellActionType.DELETE, new CellAction_DeleteOnErrorSReference(getNode(), link));
    errorCell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteOnErrorSReference(getNode(), link));
    return errorCell;
  }

  private void addCellWithRole(SNode attributeConcept, AttributeKind attributeKind, EditorCell editorCell) {
    EditorCell roleAttributeCell = createRoleAttributeCell(attributeConcept, attributeKind, editorCell);
    if (roleAttributeCell != null) {
      addCell(roleAttributeCell);
    } else {
      addCell(editorCell);
    }
  }

  private EditorCell createRoleAttributeCell(SNode attributeConcept, AttributeKind attributeKind, EditorCell editorCell) {
    if (attributeConcept != null) {
      EditorManager manager = EditorManager.getInstanceFromContext(getEditorContext());
      if (manager != null) {
        return manager.createNodeRoleAttributeCell(attributeConcept, attributeKind, editorCell);
      }
    }
    return null;
  }

  private static class ListHandler extends RefNodeListHandler {

    private final SContainmentLink myLink;
    private final SNode myOwnerNode;

    ListHandler(SNode ownerNode, SContainmentLink link, EditorContext context) {
      super(context);
      myLink = link;
      myOwnerNode = ownerNode;
    }

    public EditorCell createNodeCell(SNode elementNode) {
      EditorCell elementCell = getUpdateSession().updateChildNodeCell(elementNode);
      this.installElementCellActions(getNode(), elementNode, elementCell);
      return elementCell;
    }

    @Override
    public SContainmentLink getSLink() {
      return myLink;
    }

    @Override
    public SAbstractConcept getChildSConcept() {
      return myLink.getTargetConcept();
    }

    @Override
    public SNode getNode() {
      return myOwnerNode;
    }

    public EditorCell createEmptyCell() {
      getCellFactory().pushCellContext();
      getCellFactory().setNodeLocation(new FromParentAndLink(getNode(), myLink));
      try {
        EditorCell emptyCell = super.createEmptyCell();
        this.installElementCellActions(getNode(), null, emptyCell);
        return emptyCell;
      } finally {
        getCellFactory().popCellContext();
      }
    }

    public void installElementCellActions(SNode listOwner, SNode elementNode, EditorCell elementCell) {
      if (elementCell.getUserObject(AbstractCellListHandler.ELEMENT_CELL_ACTIONS_SET) == null) {
        elementCell.putUserObject(AbstractCellListHandler.ELEMENT_CELL_ACTIONS_SET, AbstractCellListHandler.ELEMENT_CELL_ACTIONS_SET);
        if (elementNode != null) {
          elementCell.setAction(CellActionType.DELETE, new CellAction_DeleteNode(elementNode, DeleteDirection.FORWARD));
          elementCell.setAction(CellActionType.BACKSPACE, new CellAction_DeleteNode(elementNode, DeleteDirection.BACKWARD));
        }
        if (elementCell.getSubstituteInfo() == null || elementCell.getSubstituteInfo() instanceof DefaultSubstituteInfo) {
          elementCell.setSubstituteInfo(new DefaultSChildSubstituteInfo(listOwner, elementNode, myLink, getEditorContext()));
        }
      }
    }
  }
}
