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

import jetbrains.mps.editor.runtime.descriptor.EditorBuilderEnvironment;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.editor.runtime.style.StyleImpl;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.nodeEditor.cells.EditorCell_Basic;
import jetbrains.mps.nodeEditor.cells.EditorCell_Collection;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Property;
import jetbrains.mps.nodeEditor.cells.ModelAccessor;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCellFactory;
import jetbrains.mps.openapi.editor.style.Style;
import jetbrains.mps.openapi.editor.style.StyleAttribute;
import jetbrains.mps.openapi.editor.update.UpdateSession;
import jetbrains.mps.openapi.editor.update.AttributeKind;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.adapter.structure.types.SPrimitiveTypes;
import jetbrains.mps.util.EqualUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

import java.awt.Color;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public abstract class AbstractDefaultEditor extends DefaultNodeEditor implements EditorBuilderEnvironment {
  private static final String NAME_NAME = "name";
  private static final int NAME_PRIORITY = 10000;
  private static final String IDENTIFIER_NAME = "identifier";
  private static final int IDENTIFIER_PRIORITY = 1700;
  private static final int NAME_ADD_PRIORITY = 1000;
  private static final String QUALIFIED_NAME = "qualified";
  private static final int QUALIFIED_PRIORITY = 200;
  private static final Color FIRST_LABEL_BACKGROUND_COLOR = new Color(107, 142, 20, 100);

  private SNode mySNode;
  private SConcept myConcept;
  private EditorContext myEditorContext;

  private final boolean myReflectiveRoot;

  private Deque<EditorCell_Collection> collectionStack = new LinkedList<>();
  private int currentCollectionIdNumber = 0;
  private int currentConstantIdNumber = 0;

  private Collection<SProperty> myProperties = new LinkedHashSet<>();
  private Collection<SReferenceLink> myReferenceLinks = new LinkedHashSet<>();
  private Collection<SContainmentLink> myContainmentLinks = new LinkedHashSet<>();


  public AbstractDefaultEditor(@NotNull SConcept concept, boolean reflectiveRoot) {
    myConcept = concept;
    myReflectiveRoot = reflectiveRoot;
  }

  @Override
  public EditorCell createEditorCell(EditorContext editorContext, SNode node) {
    myEditorContext = editorContext;
    mySNode = node;
    assert myConcept.equals(mySNode.getConcept());
    init();
    SProperty nameProperty = getNameProperty();
    EditorCell_Collection mainCellCollection = pushCollection();
    mainCellCollection.setBig(true);
    mainCellCollection.setCellContext(getCellFactory().getCellContext());
    addLabel(camelToLabel(myConcept.getName()));
    addStyle(StyleAttributes.TEXT_BACKGROUND_COLOR, FIRST_LABEL_BACKGROUND_COLOR);
    if (nameProperty != null) {
      getProperties().remove(nameProperty);
      addPropertyCell(nameProperty);
    }
    addReferences();
    if (!getContainmentLinks().isEmpty() || !getProperties().isEmpty() || isAttribute()) {
      addPropertiesAndChildren();
    }
    popCollection();
    return mainCellCollection;
  }

  protected void init() {
    assert mySNode != null && myConcept != null;
    for (SProperty sProperty : mySNode.getProperties()) {
      if (!sProperty.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        myProperties.add(sProperty);
      }
    }

    for (SReference sReference : mySNode.getReferences()) {
      SReferenceLink link = sReference.getLink();
      assert link != null : "Null meta-link from node: " + this.mySNode + ", role: " + sReference.getRole();
      if (!link.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        myReferenceLinks.add(link);
      }
    }

    for (SNode child : this.mySNode.getChildren()) {
      SContainmentLink containmentLink = child.getContainmentLink();
      assert containmentLink != null : "Null meta-containmentLink returned for the child of node: " + this.mySNode + ", child: " + child;
      if (!containmentLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
        myContainmentLinks.add(containmentLink);
      }
    }
  }

  protected void addProperty(SProperty property) {
    myProperties.add(property);
  }

  protected void addContainmentLink(SContainmentLink containmentLink) {
    myContainmentLinks.add(containmentLink);
  }

  protected void addReferenceLink(SReferenceLink link) {
    myReferenceLinks.add(link);
  }

  private SProperty getNameProperty() {
    SProperty nameProperty = null;
    int maxPriority = 0;
    for (SProperty property : getProperties()) {
      if (property.getType() != SPrimitiveTypes.STRING) {
        continue;
      }
      String propertyName = property.getName();
      int propertyPriority = getPropertyPriority(propertyName);
      if (maxPriority < propertyPriority) {
        maxPriority = propertyPriority;
        nameProperty = property;
      }
    }
    return nameProperty;
  }

  private int getPropertyPriority(@NotNull String propertyName) {
    if (NAME_NAME.equals(propertyName)) {
      return NAME_PRIORITY;
    }
    int priority = 0;
    if (propertyName.toLowerCase().contains(IDENTIFIER_NAME)) {
      priority += IDENTIFIER_PRIORITY;
    }
    if (propertyName.toLowerCase().contains(NAME_NAME)) {
      priority += NAME_ADD_PRIORITY;
    }
    if (propertyName.toLowerCase().contains(QUALIFIED_NAME)) {
      priority += QUALIFIED_PRIORITY;
    }
    return priority;
  }

  private void addPropertiesAndChildren() {
    addLabel("{");
    addStyle(StyleAttributes.MATCHING_LABEL, "body-brace");
    addNewLine();
    pushCollection();
    setIndent(collectionStack.peek());
    addProperties();
    addLabel("");
    addNewLine();
    addChildren();
    if (myReflectiveRoot) {
      addAttributedEntity();
    }
    popCollection();
    addLabel("}");
    addStyle(StyleAttributes.MATCHING_LABEL, "body-brace");
  }

  protected Collection<SProperty> getProperties() {
    return myProperties;
  }

  protected Collection<SReferenceLink> getReferenceLinks() {
    return myReferenceLinks;
  }

  protected Collection<SContainmentLink> getContainmentLinks() {
    return myContainmentLinks;
  }

  protected abstract void addPropertyCell(final SProperty property);

  protected abstract void addChildCell(final SContainmentLink referenceLink);

  protected abstract void addReferenceCell(final SReferenceLink referenceLink);

  private void addProperties() {
    for (SProperty property : getProperties()) {
      addRoleLabel(property.getName(), "property");
      addPropertyCell(property);
      addNewLine();
    }
  }

  private void addReferences() {
    for (SReferenceLink reference : getReferenceLinks()) {
      addRoleLabel(reference.getName(), "reference");
      addReferenceCell(reference);
    }
  }

  private void addChildren() {
    for (SContainmentLink link : getContainmentLinks()) {
      addRoleLabel(link.getName(), "link");
      addNewLine();
      addChildCell(link);
      addNewLine();
    }
  }

  private void addAttributedEntity() {
    if (AttributeOperations.isNodeAttribute(mySNode)) {
      addAttributedCell("attributed node:", AttributeKind.NODE);
    } else if (AttributeOperations.isPropertyAttribute(mySNode)) {
      addAttributedCell("attributed property:", AttributeKind.PROPERTY);
    } else if (AttributeOperations.isLinkAttribute(mySNode)) {
      addAttributedCell("attributed reference:", AttributeKind.REFERENCE);
    }
  }

  private void addAttributedCell(String label, AttributeKind attributeKind) {
    addLabel(label);
    addNewLine();
    EditorCell editorCell = myEditorContext.getEditorComponent().getUpdater().getCurrentUpdateSession().getAttributedCell(attributeKind, mySNode);
    addCell(editorCell);
    setIndent(editorCell);
    addNewLine();
  }

  private boolean isAttribute() {
    return AttributeOperations.isNodeAttribute(mySNode) || AttributeOperations.isPropertyAttribute(mySNode) || AttributeOperations.isLinkAttribute(mySNode);
  }

  private void addRoleLabel(String role, String type) {
    if (role == null) {
      role = "<no " + type + ">";
    }
    addLabel(camelToLabel(role));
    addLabel(":");
  }

  protected void addLabel(String label) {
    addLabel(label, false);
  }

  protected void addLabel(String label, boolean editable) {
    EditorCell_Collection cellCollection = collectionStack.peek();
    EditorCell_Constant childLabel = new EditorCell_Constant(getEditorContext(), mySNode, label, editable);
    childLabel.setCellId("constant_" + currentConstantIdNumber);
    cellCollection.addEditorCell(childLabel);
    currentConstantIdNumber++;
  }

  protected void addCell(EditorCell cell) {
    collectionStack.peek().addEditorCell(cell);
  }

  private String camelToLabel(String text) {
    StringBuilder sb = new StringBuilder();
    char[] cs = text.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if (Character.isUpperCase(cs[i])) {
        if (sb.length() > 0) {
          sb.append(' ');
        }
        if (i + 1 < cs.length && Character.isLowerCase(cs[i + 1])) {
          sb.append(Character.toLowerCase(cs[i]));
          continue;
        }
        while (i + 1 < cs.length && !(Character.isLowerCase(cs[i + 1]))) {
          sb.append(cs[i]);
          i++;
        }
        if (i + 1 < cs.length) {
          i--;
          continue;
        }
      }
      sb.append(cs[i]);
    }
    return sb.toString();
  }

  protected EditorCell createReferentEditorCell(EditorContext editorContext, SReferenceLink link, final SNode targetNode) {
    EditorCell_Property result = new EditorCell_Property(editorContext, new ModelAccessor() {
      public String getText() {
        String name = targetNode.getName();
        if (name != null) {
          return name;
        }
        return targetNode.getPresentation();
      }

      public void setText(String s) {
      }

      public boolean isValidText(String s) {
        return EqualUtil.equals(s, getText());
      }
    }, targetNode);
    result.setSRole(link);
    result.setReferenceCell(true);
    return result;
  }

  protected void setSemanticNodeToCells(jetbrains.mps.openapi.editor.cells.EditorCell rootCell, SNode semanticNode) {
    if (!(rootCell instanceof EditorCell_Basic) || semanticNode == null) {
      return;
    }
    ((EditorCell_Basic) rootCell).setSNode(semanticNode);
    if (rootCell instanceof jetbrains.mps.openapi.editor.cells.EditorCell_Collection) {
      for (EditorCell child : ((jetbrains.mps.openapi.editor.cells.EditorCell_Collection) rootCell)) {
        setSemanticNodeToCells(child, semanticNode);
      }
    }
  }


  private EditorCell_Collection pushCollection() {
    EditorCell_Collection newCollection = EditorCell_Collection.createIndent2(getEditorContext(), mySNode);
    collectionStack.push(newCollection);
    return newCollection;
  }

  private EditorCell_Collection popCollection() {
    if (collectionStack.isEmpty()) {
      return null;
    }
    EditorCell_Collection result = collectionStack.pop();
    result.setCellId("collection_" + currentCollectionIdNumber);
    currentCollectionIdNumber++;
    if (!collectionStack.isEmpty()) {
      collectionStack.peek().addEditorCell(result);
    }
    return result;
  }

  protected void addNewLine() {
    addStyle(getLastCell(), StyleAttributes.INDENT_LAYOUT_NEW_LINE);
  }

  private EditorCell getLastCell() {
    EditorCell_Collection collection = collectionStack.peek();
    EditorCell lastCell = collection;
    if (!collection.isEmpty()) {
      lastCell = collection.lastCell();
    }
    return lastCell;
  }

  protected void setIndent(EditorCell cell) {
    addStyle(cell, StyleAttributes.INDENT_LAYOUT_INDENT);
  }

  protected <T> void addStyle(StyleAttribute<T> attribute, T value) {
    addStyle(getLastCell(), attribute, value);
  }

  protected <T> void addStyle(EditorCell cell, StyleAttribute<T> attribute, T value) {
    Style style = new StyleImpl();
    style.set(attribute, value);
    cell.getStyle().putAll(style);
  }

  protected void addStyle(EditorCell cell, StyleAttribute<Boolean> attribute) {
    addStyle(cell, attribute, true);
  }

  public static AbstractDefaultEditor createEditor(SNode node, boolean reflectiveRoot) {
    SConcept concept = node.getConcept();
    boolean reflectiveEditorReadonly = EditorSettings.getInstance().isReflectiveEditorReadonly();
    return concept.isValid() && !reflectiveEditorReadonly ? new DefaultEditor(concept, reflectiveRoot) : new ReadOnlyDefaultEditor(concept, reflectiveRoot);
  }

  protected SConcept getConcept() {
    return myConcept;
  }

  public EditorContext getEditorContext() {
    return myEditorContext;
  }

  @Override
  public SNode getNode() {
    return mySNode;
  }

  @Override
  public EditorCellFactory getCellFactory() {
    return getUpdateSession().getCellFactory();
  }

  @Override
  public UpdateSession getUpdateSession() {
    return getEditorContext().getEditorComponent().getUpdater().getCurrentUpdateSession();
  }
}
