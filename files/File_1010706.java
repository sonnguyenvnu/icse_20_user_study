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
package jetbrains.mps.lang.editor.menus.substitute;

import jetbrains.mps.nodeEditor.LanguageRegistryHelper;
import jetbrains.mps.openapi.editor.descriptor.EditorAspectDescriptor;
import jetbrains.mps.openapi.editor.descriptor.NamedMenuId;
import jetbrains.mps.openapi.editor.descriptor.SubstituteMenu;
import jetbrains.mps.openapi.editor.menus.substitute.SubstituteMenuLookup;
import jetbrains.mps.smodel.language.LanguageRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SLanguage;

import java.util.Collection;
import java.util.Collections;

/**
 * @author simon
 */
public class NamedSubstituteMenuLookup implements SubstituteMenuLookup {
  @NotNull
  private final LanguageRegistry myLanguageRegistry;
  @NotNull
  private final NamedMenuId myId;

  public NamedSubstituteMenuLookup(@NotNull LanguageRegistry languageRegistry, @NotNull SAbstractConcept concept, @NotNull String fqName) {
    this(languageRegistry, new NamedMenuId(concept, fqName));
  }

  public NamedSubstituteMenuLookup(@NotNull LanguageRegistry languageRegistry, @NotNull NamedMenuId id) {
    myLanguageRegistry = languageRegistry;
    myId = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NamedSubstituteMenuLookup that = (NamedSubstituteMenuLookup) o;

    return myLanguageRegistry.equals(that.myLanguageRegistry) && myId.equals(that.myId);
  }

  @Override
  public int hashCode() {
    return myId.hashCode();
  }

  @Override
  public String toString() {
    return myId.toString();
  }

  @NotNull
  @Override
  public Collection<SubstituteMenu> lookup(@NotNull Collection<SLanguage> usedLanguages) {
    EditorAspectDescriptor aspectDescriptor = LanguageRegistryHelper.getEditorAspectDescriptor(myLanguageRegistry, myId.getConcept().getLanguage());
    if (aspectDescriptor == null) {
      return Collections.emptyList();
    }
    return aspectDescriptor.getNamedSubstituteMenus(getId(), usedLanguages);
  }

  @NotNull
  protected NamedMenuId getId() {
    return myId;
  }
}
