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
package jetbrains.mps.generator.impl.template;

import gnu.trove.TObjectIntHashMap;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;

import java.util.ArrayList;
import java.util.List;

/**
 * Facility to use in templates that records used meta-objects, assigns them integer value,
 * and facilitates generation of array of meta-object proxies to be accessed by integer index.
 *
 * Deemed to be used from single thread or synchronized externally, no synchronization/access control here.
 * @author Artem Tikhomirov
 */
public class MetaObjectGenerationHelper {
  private final ArrayList<SConcept> myConcepts = new ArrayList<>();
  private final TObjectIntHashMap<SConcept> myConceptKeys = new TObjectIntHashMap<>();

  private final ArrayList<SProperty> myProperties = new ArrayList<>();
  private final TObjectIntHashMap<SProperty> myPropertyKeys = new TObjectIntHashMap<>();

  private final ArrayList<SReferenceLink> myAssociations = new ArrayList<>();
  private final TObjectIntHashMap<SReferenceLink> myAssociationKeys = new TObjectIntHashMap<>();

  private final ArrayList<SContainmentLink> myAggregations = new ArrayList<>();
  private final TObjectIntHashMap<SContainmentLink> myAggregationKeys = new TObjectIntHashMap<>();

  public int record(SConcept concept) {
    return doRecord(concept, myConcepts, myConceptKeys);
  }

  public int record(SProperty property) {
    return doRecord(property, myProperties, myPropertyKeys);
  }

  public int record(SReferenceLink link) {
    return doRecord(link, myAssociations, myAssociationKeys);
  }

  public int record(SContainmentLink link) {
    return doRecord(link, myAggregations, myAggregationKeys);
  }

  private static <T> int doRecord(T element, List<T> ordered, TObjectIntHashMap<T> map) {
    if (map.contains(element)) {
      return map.get(element);
    }
    int rv = ordered.size();
    map.put(element, rv);
    ordered.add(element);
    return rv;

  }

  public SConcept[] getConcepts() {
    return myConcepts.toArray(new SConcept[0]);
  }

  public SProperty[] getProperties() {
    return myProperties.toArray(new SProperty[0]);
  }

  public SReferenceLink[] getAssociationLinks() {
    return myAssociations.toArray(new SReferenceLink[0]);
  }

  public SContainmentLink[] getAggregationLinks() {
    return myAggregations.toArray(new SContainmentLink[0]);
  }
}
