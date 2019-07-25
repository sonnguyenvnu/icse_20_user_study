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

import jetbrains.mps.nodeEditor.menus.EditorMenuTraceImpl;
import jetbrains.mps.nodeEditor.menus.substitute.DefaultSubstituteMenuContext;
import jetbrains.mps.nodeEditor.menus.substitute.DefaultSubstituteMenuContextBuilder;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.descriptor.EditorAspectDescriptor;
import jetbrains.mps.openapi.editor.menus.EditorMenuTrace;
import jetbrains.mps.openapi.editor.menus.style.EditorMenuItemCustomizer;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuContext;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuItem;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuLookup;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuContext;
import jetbrains.mps.openapi.editor.menus.transformation.TransformationMenuItem;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author simon
 */
public class SubstituteItemsCollector {
  private final SNode myParent;
  private final SNode myCurrentChild;
  private final SContainmentLink myContainmentLink;
  private final EditorContext myEditorContext;
  private final SubstituteMenuLookup myMenuLookup;
  private SAbstractConcept myTargetConcept;
  private EditorMenuTrace myEditorMenuTrace;
  private Collection<EditorMenuItemCustomizer> myCustomizers;

  public SubstituteItemsCollector(@NotNull SNode parentNode, @Nullable SNode currentChild, @Nullable SContainmentLink containmentLink,
      @NotNull EditorContext editorContext, @Nullable SubstituteMenuLookup menuLookup) {
    this(parentNode, currentChild, containmentLink, null, editorContext, menuLookup);
  }

  public SubstituteItemsCollector(@NotNull SNode parentNode, @Nullable SNode currentChild, @Nullable SContainmentLink containmentLink, @Nullable SAbstractConcept targetConcept,
      @NotNull EditorContext editorContext, @Nullable SubstituteMenuLookup menuLookup) {
    this(parentNode, currentChild, containmentLink, targetConcept, editorContext, menuLookup, new EditorMenuTraceImpl());
  }

  public SubstituteItemsCollector(@NotNull SNode parentNode, @Nullable SNode currentChild, @Nullable SContainmentLink containmentLink, @Nullable SAbstractConcept targetConcept,
                                  @NotNull EditorContext editorContext, @Nullable SubstituteMenuLookup menuLookup, EditorMenuTrace editorMenuTrace) {
    myParent = parentNode;
    myCurrentChild = currentChild;
    myContainmentLink = containmentLink;
    myTargetConcept = targetConcept;
    myEditorContext =  editorContext;
    myMenuLookup = menuLookup;
    myEditorMenuTrace = editorMenuTrace;
    myCustomizers = new HashSet<>();
    LanguageRegistry.getInstance(myEditorContext.getRepository()).withAvailableLanguages(languageRuntime -> {
      EditorAspectDescriptor aspect = languageRuntime.getAspect(EditorAspectDescriptor.class);
      if (aspect != null) {
        Collection<EditorMenuItemCustomizer> editorMenuItemCustomizers = aspect.getEditorMenuItemCustomizers();
        myCustomizers.addAll(editorMenuItemCustomizers);
      }
    });
  }

  public SubstituteItemsCollector(@NotNull TransformationMenuContext transformationMenuContext, SubstituteMenuLookup menuLookup){
    myParent = transformationMenuContext.getNodeLocation().getParent();
    myCurrentChild = transformationMenuContext.getNodeLocation().getChild();
    myContainmentLink = transformationMenuContext.getNodeLocation().getContainmentLink();
    myTargetConcept = transformationMenuContext.getNodeLocation().getTargetConcept();
    myEditorContext =  transformationMenuContext.getEditorContext();
    myEditorMenuTrace = transformationMenuContext.getEditorMenuTrace();
    myCustomizers = transformationMenuContext.getCustomizers();
    myMenuLookup = menuLookup;

  }

  public List<TransformationMenuItem> collect() {
    DefaultSubstituteMenuContext substituteMenuContext = new DefaultSubstituteMenuContextBuilder(myParent, myEditorContext)
                                                                    .setContainmentLink(myContainmentLink)
                                                                    .setTargetConcept(myTargetConcept)
                                                                    .setCurrentChild(myCurrentChild)
                                                                    .setEditorMenuTrace(myEditorMenuTrace)
                                                                    .setCustomizers(myCustomizers)
                                                                    .createDefaultSubstituteMenuContext();
    return substituteMenuContext.createItems(myMenuLookup).stream().
        map(item -> convert(item, substituteMenuContext)).
        collect(Collectors.toList());
  }

  protected TransformationMenuItem convert(SubstituteMenuItem item, SubstituteMenuContext context) {
    return new DefaultSubstituteMenuItemAsActionItem(item, context);
  }
}
