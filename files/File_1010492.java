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
package jetbrains.mps.persistence.registry;

import jetbrains.mps.smodel.adapter.ids.MetaIdHelper;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SContainmentLinkId;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.ids.SPropertyId;
import jetbrains.mps.smodel.adapter.ids.SReferenceLinkId;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Fraction of a structure model, sufficient to serialize/de-serialize given model/nodes.
 *
 * No thread safety is ensured, deemed to be populated from a single thread.
 * @author Artem Tikhomirov
 */
public class IdInfoRegistry {
  private final HashMap<SLanguageId, LangInfo> myLanguagesInUse;
  /*
   * In fact, just a view of myLanguagesInUse.values().getConceptsInUse().select(it -> map(it.getConceptId(), it))
   */
  private final HashMap<SConceptId, ConceptInfo> myRegistry;

  public IdInfoRegistry() {
    myRegistry = new HashMap<>();
    myLanguagesInUse = new HashMap<>();
  }

  /**
   * @return ordered set of languages known to this registry
   */
  public List<LangInfo> getLanguagesInUse() {
    ArrayList<LangInfo> rv = new ArrayList<>(myLanguagesInUse.values());
    Collections.sort(rv);
    return rv;
  }

  /**
   * Records a language (unless already known) in the collector
   * @return utility object that keeps language information essential for persistence
   */
  @NotNull
  public LangInfo registerLanguage(@NotNull SLanguageId lang, @NotNull String languageName) {
    LangInfo langInfo = myLanguagesInUse.get(lang);
    if (langInfo == null) {
      myLanguagesInUse.put(lang, langInfo = new LangInfo(lang, languageName));
    }
    return langInfo;
  }

  /**
   * Records a concept (unless already known) in the collector.
   * NOTE, concept's language has to be registered beforehand.
   * @return utility object that keeps concept information essential for persistence
   * @throws java.lang.IllegalArgumentException if concept to register comes from unknown language
   */
  @NotNull
  public ConceptInfo registerConcept(@NotNull SConceptId concept, @NotNull String conceptName) throws IllegalArgumentException {
    if (!knows(concept.getLanguageId())) {
      throw new IllegalArgumentException(String.format("Concept %s comes from a language not yet registered here", concept));
    }
    ConceptInfo ci = myRegistry.get(concept);
    if (ci != null) {
      return ci;
    }
    LangInfo li = myLanguagesInUse.get(concept.getLanguageId());
    ci = new ConceptInfo(concept, conceptName);
    li.register(ci);
    myRegistry.put(concept, ci);
    return ci;
  }

  public boolean knows(@NotNull SLanguageId lang) {
    return myLanguagesInUse.containsKey(lang);
  }

  public boolean knows(@NotNull SConceptId concept) {
    return myRegistry.containsKey(concept);
  }

  @Nullable
  public ConceptInfo get(@NotNull SConceptId concept) {
    return myRegistry.get(concept);
  }

  public ConceptInfo find(@NotNull SConcept concept) {
    final SConceptId id = MetaIdHelper.getConcept(concept);
    assert myRegistry.containsKey(id); // the way IdInfoCollector is built shall ensure concept of any node in a model is registered
    return myRegistry.get(id);
  }
  public PropertyInfo find(@NotNull SProperty property) {
    SPropertyId id = MetaIdHelper.getProperty(property);
    return myRegistry.get(id.getConceptId()).find(id);
  }
  public AssociationLinkInfo find(@NotNull SReferenceLink link) {
    SReferenceLinkId id = MetaIdHelper.getAssociation(link);
    return myRegistry.get(id.getConceptId()).find(id);
  }
  public AggregationLinkInfo find(@NotNull SContainmentLink link) {
    SContainmentLinkId id = MetaIdHelper.getAggregation(link);
    return myRegistry.get(id.getConceptId()).find(id);
  }

  /**
   * Treat set of known meta-objects as closed and assign string index values to each and every meta-object registered
   * @see ConceptInfo#getIndex()
   * @param indexEncoder translates internal identity integer value into an index for textual persistence
   */
  public void initializeIndexValues(@NotNull IndexEncoder indexEncoder) {
    HashSet<String> usedConceptIndexes = new HashSet<>();
    HashSet<String> usedPropertyIndexes = new HashSet<>();
    HashSet<String> usedAssociationIndexes = new HashSet<>();
    HashSet<String> usedAggregationIndexes = new HashSet<>();
    // iterate from language to ensure the same order (and same hash conflict resolution result) for subsequent runs
    for (LangInfo langInfo : getLanguagesInUse()) {
      for (ConceptInfo ci : langInfo.getConceptsInUse()) {
        fill(usedConceptIndexes, ci, indexEncoder);
        for (PropertyInfo pi : ci.getPropertiesInUse()) {
          fill(usedPropertyIndexes, pi, indexEncoder);
        }
        for (AssociationLinkInfo li : ci.getAssociationsInUse()) {
          fill(usedAssociationIndexes, li, indexEncoder);
        }
        for (AggregationLinkInfo li : ci.getAggregationsInUse()) {
          fill(usedAggregationIndexes, li, indexEncoder);
        }
      }
    }
  }

  private static void fill(HashSet<String> usedIndexes, BaseInfo bi, IndexEncoder indexEncoder) {
    int v = bi.internalKey();
    String s;
    do {
      s = indexEncoder.index(v);
      v++;
    } while (!usedIndexes.add(s));
    bi.setIndex(s);
  }

  public interface IndexEncoder {
    String index(int key);
  }
}
