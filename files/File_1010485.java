/*
 * Copyright 2003-2018 JetBrains s.r.o.
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

import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SContainmentLinkId;
import jetbrains.mps.smodel.adapter.ids.SPropertyId;
import jetbrains.mps.smodel.adapter.ids.SReferenceLinkId;
import jetbrains.mps.smodel.runtime.ConceptKind;
import jetbrains.mps.smodel.runtime.StaticScope;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Tracks meta-information relevant to persistence of concept instances in a given model.
 * Keeps only meta-properties and meta-references actually employed in the model.
 *
 * Methods #find() provide access to information kept;
 * methods #addProperty(), #addLink() unconditionally add information about meta attribute to concept info,
 * while methods #registerProperty, #registerLink() perform a check if specified property is already registered.
 * I.e. from the code that operates with node instances (may encounter few uses of the same SProperty), use #registerProperty();
 * when the meta-info registry is read back (with single property element), use #addProperty();
 */
public final class ConceptInfo extends BaseInfo implements Comparable<ConceptInfo> {
  private final SConceptId myConcept;
  private final String myName;
  private final HashMap<SPropertyId, PropertyInfo> myProperties = new HashMap<>();
  private final HashMap<SReferenceLinkId, AssociationLinkInfo> myAssociations = new HashMap<>();
  private final HashMap<SContainmentLinkId, AggregationLinkInfo> myAggregations = new HashMap<>(8);
  private ConceptKind myKind = ConceptKind.NORMAL;
  private StaticScope myScope = StaticScope.GLOBAL;
  private SConceptId myStubCounterpart = null; // makes sense only for ConceptKind.IMPLEMENTATION_WITH_STUB

  /*package*/ ConceptInfo(@NotNull SConceptId concept, @NotNull String conceptName) {
    myConcept = concept;
    myName = conceptName;
  }
  public SConceptId getConceptId() {
    return myConcept;
  }
  public List<PropertyInfo> getPropertiesInUse() {
    ArrayList<PropertyInfo> rv = new ArrayList<>(myProperties.values());
    Collections.sort(rv);
    return rv;
  }
  public List<AssociationLinkInfo> getAssociationsInUse() {
    ArrayList<AssociationLinkInfo> rv = new ArrayList<>(myAssociations.values());
    Collections.sort(rv);
    return rv;
  }
  public List<AggregationLinkInfo> getAggregationsInUse() {
    ArrayList<AggregationLinkInfo> rv = new ArrayList<>(myAggregations.values());
    Collections.sort(rv);
    return rv;
  }
  public String getName() {
    return myName;
  }

  /**
   * Towards non-qualified concept names: meanwhile use in binary persistence only. Once it's ok, use this name as the only one (i.e. in xml persistence, too)
   */
  public String getBriefName() {
    return NameUtil.shortNameFromLongName(myName);
  }

  public StaticScope getScope() {
    return myScope;
  }

  public ConceptKind getKind() {
    return myKind;
  }

  @Nullable
  public SConceptId getStubCounterpart() {
    assert myKind == ConceptKind.IMPLEMENTATION_WITH_STUB;
    return myStubCounterpart;
  }
  public void setStubCounterpart(@Nullable SConceptId stub) {
    myStubCounterpart = stub;
  }
  public String constructStubConceptName() {
    return constructStubConceptName(myName);
  }
  private static String constructStubConceptName(@NotNull String originalConceptQualifiedName) {
    String ns = NameUtil.namespaceFromLongName(originalConceptQualifiedName);
    String sname = NameUtil.shortNameFromLongName(originalConceptQualifiedName);
    return ((ns == null || ns.isEmpty()) ? "" : ns + '.') + "Stub" + sname;
  }

  public boolean isImplementation() {
    return myKind == ConceptKind.IMPLEMENTATION || myKind == ConceptKind.IMPLEMENTATION_WITH_STUB;
  }

  /**
   * @return <code>true</code> iff has both appropriate kind and knows stub concept (absence of stub concept is treated as implementation)
   */
  public boolean isImplementationWithStub() {
    // treat ImplementationWithStub without actual stub as mere Implementation
    return myKind == ConceptKind.IMPLEMENTATION_WITH_STUB && myStubCounterpart != null;
  }

  /**
   * @return value suitable for nodeInfo attribute of node element, text that describes concept's InterfacePart/ImplementationPart kind (ConceptKind) and StaticScope
   */
  @NotNull
  public String getImplementationKindText() {
    // see Util9.genNodeInfo(PersistenceRegistry.getInstance().getModelEnvironmentInfo(), node)
    // XXX perhaps, shall refactor ImplKind into dedicated subclass that holds both serialize and parse code
    char[] res = new char[]{'n', 'g'};
    switch (myKind) {
      case INTERFACE: res[0] = 'i'; break;
      case IMPLEMENTATION: res[0] = 'l'; break;
      case IMPLEMENTATION_WITH_STUB: res[0] = 's'; break;
    }
    switch (myScope) {
      case ROOT: res[1] = 'r'; break;
      case NONE: res[1] = 'n'; break;
    }
    return new String(res);
  }

  public void setImplementationKind(StaticScope scope, ConceptKind kind) {
    myKind = kind;
    myScope = scope;
  }
  public void parseImplementationKind(@NotNull String kind) {
    switch (kind.charAt(0)) {
      case 'i' : myKind = ConceptKind.INTERFACE; break;
      case 'l' : myKind = ConceptKind.IMPLEMENTATION; break;
      case 's' : myKind = ConceptKind.IMPLEMENTATION_WITH_STUB; break;
      default: myKind = ConceptKind.NORMAL;
    }
    switch (kind.charAt(1)) {
      case 'r' : myScope = StaticScope.ROOT; break;
      case 'n' : myScope = StaticScope.NONE; break;
      default: myScope = StaticScope.GLOBAL;
    }
  }

  public PropertyInfo addProperty(SPropertyId propertyId, String name) {
    assert !myProperties.containsKey(propertyId);
    PropertyInfo rv = new PropertyInfo(propertyId, name);
    myProperties.put(propertyId, rv);
    return rv;
  }
  public AssociationLinkInfo addLink(SReferenceLinkId linkId, String roleName) {
    assert !myAssociations.containsKey(linkId);
    AssociationLinkInfo rv = new AssociationLinkInfo(linkId, roleName);
    myAssociations.put(linkId, rv);
    return rv;
  }
  public AggregationLinkInfo addLink(SContainmentLinkId linkId, String roleName, boolean unordered) {
    assert !myAggregations.containsKey(linkId);
    final AggregationLinkInfo l = new AggregationLinkInfo(linkId, roleName);
    l.setUnordered(unordered);
    myAggregations.put(linkId, l);
    return l;
  }
  public boolean knows(@NotNull SPropertyId property) {
    return myProperties.containsKey(property);
  }
  public boolean knows(@NotNull SReferenceLinkId link) {
    return myAssociations.containsKey(link);
  }
  public boolean knows(@NotNull SContainmentLinkId link) {
    return myAggregations.containsKey(link);
  }

  /*package*/ PropertyInfo find(@NotNull SPropertyId id) {
    assert myProperties.containsKey(id);
    return myProperties.get(id);
  }
  /*package*/ AssociationLinkInfo find(@NotNull SReferenceLinkId id) {
    assert myAssociations.containsKey(id);
    return myAssociations.get(id);
  }
  /*package*/ AggregationLinkInfo find(@NotNull SContainmentLinkId id) {
    assert myAggregations.containsKey(id);
    return myAggregations.get(id);
  }

  @Override
  /*package*/ int internalKey() {
    return ltoi(myConcept.getIdValue());
  }

  @Override
  public int compareTo(@NotNull ConceptInfo o) {
    return  unsigned(internalKey()) - unsigned(o.internalKey());
  }
}
