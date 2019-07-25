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
package jetbrains.mps.persistence.binary;

import gnu.trove.TIntObjectHashMap;
import jetbrains.mps.persistence.MetaModelInfoProvider;
import jetbrains.mps.persistence.registry.ConceptInfo;
import jetbrains.mps.persistence.registry.IdInfoRegistry;
import jetbrains.mps.persistence.registry.LangInfo;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SContainmentLinkId;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.ids.SPropertyId;
import jetbrains.mps.smodel.adapter.ids.SReferenceLinkId;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.runtime.ConceptKind;
import jetbrains.mps.smodel.runtime.StaticScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link jetbrains.mps.smodel.persistence.def.v9.IdInfoReadHelper} counterpart for binary persistence.
 * FIXME consider refactoring to remove duplicating code (e.g. #isInterface or #isRequestedInterfaceOnly)
 * @author Artem Tikhomirov
 */
class ReadHelper {
  private final IdInfoRegistry myMetaInfo;
  private final MetaModelInfoProvider myMetaInfoProvider;
  private boolean myInterfaceOnly;
  private ConceptInfo myActualConcept;
  // TODO with indices being just a persistence position, shall use arrays instead
  private final TIntObjectHashMap<SConcept> myConcepts = new TIntObjectHashMap<>();
  private final TIntObjectHashMap<SProperty> myProperties = new TIntObjectHashMap<>();
  private final TIntObjectHashMap<SReferenceLink> myAssociations = new TIntObjectHashMap<>();
  private final TIntObjectHashMap<SContainmentLink> myAggregations = new TIntObjectHashMap<>();

  public ReadHelper(@NotNull MetaModelInfoProvider mmiProvider) {
    myMetaInfo = new IdInfoRegistry();
    myMetaInfoProvider = mmiProvider;
  }
  /**/void requestInterfaceOnly(boolean interfaceOnly) {
    myInterfaceOnly = interfaceOnly;
  }

  public boolean isRequestedInterfaceOnly() {
    return myInterfaceOnly;
  }

  public void withLanguage(SLanguageId lang, String name, int index) {
    final LangInfo langInfo = myMetaInfo.registerLanguage(lang, name);
    langInfo.setIntIndex(index);
    myMetaInfoProvider.setLanguageName(lang, name);
  }

  // @param stub is optional
  public void withConcept(SConceptId concept, String name, StaticScope scope, ConceptKind kind, SConceptId stub, int index) {
    myActualConcept = myMetaInfo.registerConcept(concept, name);
    myActualConcept.setImplementationKind(scope, kind);
    myActualConcept.setIntIndex(index);
    myConcepts.put(index, MetaAdapterFactory.getConcept(concept, name));
    myMetaInfoProvider.setConceptName(concept, name);
    myActualConcept.setStubCounterpart(stub);
    myMetaInfoProvider.setStubConcept(concept, stub);
  }

  public void property(SPropertyId property, String name, int index) {
    myActualConcept.addProperty(property, name).setIntIndex(index);
    myProperties.put(index, MetaAdapterFactory.getProperty(property, name));
    myMetaInfoProvider.setPropertyName(property, name);
  }

  public void association(SReferenceLinkId link, String name, int index) {
    myActualConcept.addLink(link, name).setIntIndex(index);
    myAssociations.put(index, MetaAdapterFactory.getReferenceLink(link, name));
    myMetaInfoProvider.setAssociationName(link, name);
  }

  public void aggregation(SContainmentLinkId link, String name, boolean unordered, int index) {
    myActualConcept.addLink(link, name, unordered).setIntIndex(index);
    myAggregations.put(index, MetaAdapterFactory.getContainmentLink(link, name));
    myMetaInfoProvider.setAggregationName(link, name);
  }

  public SConcept readConcept(int index) {
    return myConcepts.get(index);
  }

  public SProperty readProperty(int index) {
    return myProperties.get(index);
  }

  public SReferenceLink readAssociation(int index) {
    return myAssociations.get(index);
  }

  public SContainmentLink readAggregation(int index) {
    return myAggregations.get(index);
  }

  public boolean isInterface(@NotNull SConcept concept) {
    return ConceptKind.INTERFACE == myMetaInfo.find(concept).getKind();
  }


  /*package*/ List<SConceptId> getParticipatingConcepts() {
    ArrayList<SConceptId> rv = new ArrayList<>(100);
    for (LangInfo li : myMetaInfo.getLanguagesInUse()) {
      for (ConceptInfo ci : li.getConceptsInUse()) {
        // FIXME could I use myMetaInfo.registry.keySet() instead?
        rv.add(ci.getConceptId());
      }
    }
    return rv;
  }
}
