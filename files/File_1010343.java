/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.smodel;

import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SContainmentLinkId;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.ids.SPropertyId;
import jetbrains.mps.smodel.adapter.ids.SReferenceLinkId;
import jetbrains.mps.util.Pair;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.HashMap;
import java.util.Map;

public class DebugRegistry implements CoreComponent {
  private static DebugRegistry INSTANCE;

  public static DebugRegistry getInstance() {
    return INSTANCE;
  }

  //------------ init/dispose/listening

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }

    INSTANCE = this;
  }

  @Override
  public void dispose() {
    INSTANCE = null;
  }


  private Map<org.jetbrains.mps.openapi.model.SModelReference, String> myModels =
      new HashMap<>();
  private Map<SModuleReference, String> myModules = new HashMap<>();

  private Map<SPropertyId, Pair<SConceptId, String>> myProperties = new HashMap<>();
  private Map<SReferenceLinkId, Pair<SConceptId, String>> myLinks = new HashMap<>();
  private Map<SContainmentLinkId, Pair<SConceptId, String>> myChildRoles = new HashMap<>();
  private Map<SConceptId, String> myConcepts = new HashMap<>();
  private Map<SLanguageId, String> myLanguages = new HashMap<>();

  public synchronized String getModelName(org.jetbrains.mps.openapi.model.SModelReference modelId) {
    return myModels.get(modelId);
  }

  public synchronized String getModuleName(SModuleReference moduleId) {
    return myModules.get(moduleId);
  }

  public synchronized String getPropertyName(SPropertyId propertyId) {
    Pair<SConceptId, String> pair = myProperties.get(propertyId);
    return pair == null ? null : pair.o2;
  }

  public synchronized String getRefName(SReferenceLinkId linkId) {
    Pair<SConceptId, String> pair = myLinks.get(linkId);
    return pair == null ? null : pair.o2;
  }

  public synchronized String getLinkName(SContainmentLinkId linkId) {
    Pair<SConceptId, String> pair = myChildRoles.get(linkId);
    return pair == null ? null : pair.o2;
  }

  //fqName
  public synchronized String getConceptName(SConceptId conceptId) {
    return myConcepts.get(conceptId);
  }

  public synchronized String getLanguageName(SLanguageId languageId) {
    return myLanguages.get(languageId);
  }

  public synchronized void addModelName(org.jetbrains.mps.openapi.model.SModelReference modelId, String name) {
    myModels.put(modelId, name);
  }

  public synchronized void addModuleName(SModuleReference moduleId, String name) {
    myModules.put(moduleId, name);
  }

  public synchronized void addPropertyName(SPropertyId propertyId, String name) {
    myProperties.put(propertyId, new Pair<>(propertyId.getConceptId(), name));
  }

  public synchronized void addRefName(SReferenceLinkId linkId, String name) {
    myLinks.put(linkId, new Pair<>(linkId.getConceptId(), name));
  }

  public synchronized void addLinkName(SContainmentLinkId linkId, String name) {
    myChildRoles.put(linkId, new Pair<>(linkId.getConceptId(), name));
  }

  //fqName
  public synchronized void addConceptName(SConceptId conceptId, String name) {
    myConcepts.put(conceptId, name);
  }

  public synchronized void addLanguageName(SLanguageId languageId, String name) {
    myLanguages.put(languageId, name);
  }
}
