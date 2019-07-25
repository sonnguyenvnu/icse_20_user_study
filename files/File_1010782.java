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
package jetbrains.mps.nodeEditor.menus.transformation;

import jetbrains.mps.lang.editor.menus.transformation.DefaultTransformationMenuLookup;
import jetbrains.mps.lang.editor.menus.transformation.InUsedLanguagesPredicate;
import jetbrains.mps.nodeEditor.menus.CachingPredicate;
import jetbrains.mps.nodeEditor.menus.CanBeChildPredicate;
import jetbrains.mps.nodeEditor.menus.CanBeParentPredicate;
import jetbrains.mps.nodeEditor.menus.EditorMenuTraceImpl;
import jetbrains.mps.nodeEditor.menus.MenuItemFactory;
import jetbrains.mps.nodeEditor.menus.MenuUtil;
import jetbrains.mps.nodeEditor.menus.RecursionSafeMenuItemFactory;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCellContext;
import jetbrains.mps.openapi.editor.descriptor.EditorAspectDescriptor;
import jetbrains.mps.openapi.editor.menus.EditorMenuTrace;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.transformation.SNodeLocation;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuContext;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuLookup;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A default implementation of {@link TransformationMenuContext}. Uses {@link RecursionSafeMenuItemFactory} to protect against endless recursion.
 */
public class DefaultTransformationMenuContext implements TransformationMenuContext {
  @NotNull
  private final MenuItemFactory<TransformationMenuItem, TransformationMenuContext, TransformationMenuLookup> myMenuItemFactory;
  @NotNull
  private final String myMenuLocation;
  @NotNull
  private final EditorContext myEditorContext;

  @NotNull
  private final SNodeLocation myNodeLocation;

  private final EditorMenuTrace myEditorMenuTrace;

  private Predicate<SAbstractConcept> mySuitableForConstraintsPredicate;

  private Set<EditorMenuItemCustomizer> myEditorMenuItemCustomizers;

  private Set<TransformationMenuLookup> myUsedLookups = new HashSet<>();

  private static final Logger LOG = Logger.getLogger(DefaultTransformationMenuContext.class);

  @NotNull
  public static DefaultTransformationMenuContext createInitialContextForCell(@NotNull EditorCell cell, @NotNull String menuLocation) {
    EditorContext editorContext = cell.getContext();
    SNodeLocation nodeLocation = nodeLocationFromCell(cell);
    return new DefaultTransformationMenuContext(new RecursionSafeMenuItemFactory<>(new DefaultTransformationMenuItemFactory(MenuUtil.getUsedLanguages(
        nodeLocation.getContextNode()))), menuLocation, editorContext, nodeLocation, new EditorMenuTraceImpl());
  }

  @NotNull
  private static SNodeLocation nodeLocationFromCell(@NotNull EditorCell cell) {
    EditorCell cellWithLocation = cell;
    while (cellWithLocation != null) {
      final EditorCellContext cellContext = cellWithLocation.getCellContext();
      if (cellContext != null) {
        final SNodeLocation cellNodeLocation = cellContext.getNodeLocation();
        if (cellNodeLocation != null) {
          return cellNodeLocation;
        }
      }
      if (cellWithLocation.isBig()) {
        break;
      }
      cellWithLocation = cellWithLocation.getParent();
    }

    //todo should we remove this?
    SNode cellNode = cell.getSNode();
    if (cellNode == null) {
      throw new IllegalArgumentException("cell should have a node: " + cell);
    }

    if (!(cell.getSRole() instanceof SContainmentLink)) {
      return new SNodeLocation.FromNode(cellNode);
    }

    SContainmentLink link;
    if (!cell.isBig() && (link = ((SContainmentLink) cell.getSRole())) != null && cell.isSelectable()) {
      // FIXME This is a hacky way to determine whether the cell is a no-target cell.
      //
      // We assume here that if the cell had a role specified and was selectable then that the cell is a no-target placeholder cell (called "empty cell" in the
      // editor). This is because cells that correspond to existing children don't have a role set, and while their parent collection does have a role, it is
      // not selectable.
      //
      // Currently there is no way to tell at runtime whether a cell is a no-target cell; it is assumed that this information should only be used at generation
      // time to change the generated code, but the current design of the menus does not allow this.
      return new SNodeLocation.FromParentAndLink(cellNode, link);
    }

    return new SNodeLocation.FromNode(cellNode);
  }


  private DefaultTransformationMenuContext(
      @NotNull MenuItemFactory<TransformationMenuItem, TransformationMenuContext, TransformationMenuLookup> menuItemFactory,
      @NotNull String menuLocation,
      @NotNull EditorContext editorContext, @NotNull SNodeLocation nodeLocation,
      @NotNull EditorMenuTrace editorMenuTrace) {
    myMenuItemFactory = menuItemFactory;
    myMenuLocation = menuLocation;
    myEditorContext = editorContext;
    myNodeLocation = nodeLocation;
    myEditorMenuTrace = editorMenuTrace;
    myEditorMenuItemCustomizers = new HashSet<>();
    LanguageRegistry.getInstance(myEditorContext.getRepository()).withAvailableLanguages(languageRuntime -> {
      EditorAspectDescriptor aspect = languageRuntime.getAspect(EditorAspectDescriptor.class);
      if (aspect != null) {
        Collection<EditorMenuItemCustomizer> editorMenuItemCustomizers = aspect.getEditorMenuItemCustomizers();
        myEditorMenuItemCustomizers.addAll(editorMenuItemCustomizers);
      }
    });
  }

  @NotNull
  private Predicate<SAbstractConcept> createSuitableForConstraintsPredicate(@NotNull SNodeLocation nodeLocation) {
    final SContainmentLink containmentLink = nodeLocation.getContainmentLink();
    Predicate<SAbstractConcept> predicate = new CanBeParentPredicate(nodeLocation.getParent(), containmentLink);
    if (nodeLocation.getParent() != null) {
      predicate = predicate.and(new CanBeChildPredicate(nodeLocation.getParent(), containmentLink));
    }
    predicate = predicate.and(new InUsedLanguagesPredicate(getModel()));
    return predicate;
  }

  @NotNull
  @Override
  public String getMenuLocation() {
    return myMenuLocation;
  }

  @NotNull
  public SNodeLocation getNodeLocation() {
    return myNodeLocation;
  }

  @NotNull
  @Override
  public EditorContext getEditorContext() {
    return myEditorContext;
  }

  @Override
  public Collection<EditorMenuItemCustomizer> getCustomizers() {
    return Collections.unmodifiableSet(myEditorMenuItemCustomizers);
  }

  @NotNull
  @Override
  public TransformationMenuContext with(@Nullable SNodeLocation nodeLocation, @Nullable String menuLocation) {
    if (nodeLocation == null) {
      nodeLocation = myNodeLocation;
    }

    if (menuLocation == null) {
      menuLocation = myMenuLocation;
    }

    if (myNodeLocation.equals(nodeLocation) && myMenuLocation.equals(menuLocation)) {
      return this;
    }

    return new DefaultTransformationMenuContext(myMenuItemFactory, menuLocation, myEditorContext, nodeLocation, myEditorMenuTrace);
  }


  @Override
  public Predicate<SAbstractConcept> getConstraintsCheckingPredicate() {
    if (mySuitableForConstraintsPredicate == null) {
      mySuitableForConstraintsPredicate =
          new CachingPredicate<>(createSuitableForConstraintsPredicate(myNodeLocation));
    }
    return mySuitableForConstraintsPredicate;
  }

  @NotNull
  @Override
  public List<TransformationMenuItem> createItems(@Nullable TransformationMenuLookup menuLookup) {
    if (menuLookup == null) {
      menuLookup = new DefaultTransformationMenuLookup(LanguageRegistry.getInstance(myEditorContext.getRepository()),
                                                       myNodeLocation.getContextNode().getConcept());
    }
    if (myUsedLookups.contains(menuLookup)) {
      LOG.info("Lookup + " + menuLookup + " was already used within this context. Return empty collection to prevent items duplication");
      return Collections.emptyList();
    }
    myUsedLookups.add(menuLookup);

    return myMenuItemFactory.createItems(this, menuLookup);
  }

  /**
   * Creates items from menus looked up by {@code menuLookup}. If all the menus are inapplicable, returns a fallback menu (see {@link ImplicitMenuLookup}).
   */
  @NotNull
  public List<TransformationMenuItem> createItemsWithFallback(@Nullable TransformationMenuLookup menuLookup) {
    SNode contextNode = myNodeLocation.getContextNode();
    SConcept contextNodeConcept = contextNode.getConcept();
    if (menuLookup == null) {
      menuLookup = new DefaultTransformationMenuLookup(LanguageRegistry.getInstance(myEditorContext.getRepository()), contextNodeConcept);
    }

    if (!MenuUtil.isMenuApplicableToLocation(menuLookup, myMenuLocation, contextNode)) {
      menuLookup = new ImplicitMenuLookup(contextNodeConcept);
    }

    return createItems(menuLookup);
  }

  @NotNull
  @Override
  public EditorMenuTrace getEditorMenuTrace() {
    return myEditorMenuTrace;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getNode(), getEditorContext());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DefaultTransformationMenuContext that = (DefaultTransformationMenuContext) o;

    return getNode().equals(that.getNode()) && getEditorContext().equals(that.getEditorContext()) && getMenuLocation().equals(that.getMenuLocation());
  }
}
