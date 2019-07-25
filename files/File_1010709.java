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

import jetbrains.mps.nodeEditor.menus.MenuUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * @author simon
 */
public class InUsedLanguagesPredicate implements Predicate<SAbstractConcept> {
  private final SModel myModel;
  private final Collection<SLanguage> myUsedLanguages;

  public InUsedLanguagesPredicate(SModel model) {
    myModel = model;
    myUsedLanguages = model == null ? null : MenuUtil.getUsedLanguages(myModel);
  }

  @Override
  public boolean test(@Nullable SAbstractConcept concept) {
    return concept == null || myModel == null || myUsedLanguages.contains(concept.getLanguage());
  }
}
