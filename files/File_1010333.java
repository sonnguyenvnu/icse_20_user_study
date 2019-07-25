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
package jetbrains.mps.smodel.adapter.structure.ref;

import jetbrains.mps.scope.Scope;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.constraints.ModelConstraints;
import jetbrains.mps.smodel.language.ConceptRegistry;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import jetbrains.mps.smodel.runtime.ReferenceDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.language.SScope;
import org.jetbrains.mps.openapi.model.SNode;

public abstract class SReferenceLinkAdapter implements SReferenceLink {
  public static final String ID_DELIM = ":";

  protected String myName;

  protected SReferenceLinkAdapter(@NotNull String name) {
    myName = name;
  }

  @NotNull
  @Override
  public String getName() {
    return getRoleName();
  }

  @Nullable
  public abstract ReferenceDescriptor getReferenceDescriptor();

  @Override
  public boolean isValid() {
    return getReferenceDescriptor() != null;
  }

  @NotNull
  public abstract SAbstractConcept getOwner();

  @Override
  public String getRole() {
    return getRoleName();
  }

  @Override
  public boolean isOptional() {
    ReferenceDescriptor rd = getReferenceDescriptor();
    if (rd == null) {
      return true;
    }

    return rd.isOptional();
  }

  @NotNull
  @Override
  public SAbstractConcept getTargetConcept() {
    ReferenceDescriptor rd = getReferenceDescriptor();
    if (rd == null) {
      return SNodeUtil.concept_BaseConcept;
    }

    SConceptId id = rd.getTargetConcept();
    ConceptDescriptor concept = ConceptRegistry.getInstance().getConceptDescriptor(id);
    return MetaAdapterFactory.getAbstractConcept(concept);
  }

  @Override
  public boolean isReference() {
    return true;
  }

  @Override
  public boolean isMultiple() {
    return false;
  }

  public SScope getScope(SNode referenceNode) {
    Scope scope = ModelConstraints.getReferenceDescriptor(referenceNode, this).getScope();
    return new SScopeAdapter(scope, referenceNode);
  }

  public SScope getScope(SNode contextNode, @Nullable SContainmentLink link, int index) {
    Scope scope = ModelConstraints.getReferenceDescriptor(contextNode, link, index, this).getScope();
    return new SScopeAdapter(scope, contextNode);
  }

  private static class SScopeAdapter implements SScope {
    private final SNode myContextNode;
    private final Scope myScope;

    private SScopeAdapter(@NotNull Scope scope, @NotNull SNode contextNode) {
      myScope = scope;
      myContextNode = contextNode;
    }

    public Iterable<SNode> getAvailableElements(@Nullable String prefix) {
      return myScope.getAvailableElements(prefix);
    }

    public boolean contains(SNode node) {
      return myScope.contains(node);
    }

    @Nullable
    public SNode resolve(@NotNull String string) {
      return myScope.resolve(myContextNode, string);
    }

    @Nullable
    public String getReferenceText(@NotNull SNode node) {
      return myScope.getReferenceText(myContextNode, node);
    }
  }

  @Override
  public String toString() {
    return getName();
  }

  public abstract String serialize();

  public static SReferenceLinkAdapter deserialize(String s) {
    if (s.startsWith(SReferenceLinkAdapterById.REF_PREFIX)) {
      return SReferenceLinkAdapterById.deserialize(s);
    } else if (s.startsWith(InvalidReferenceLink.INVALID_PREFIX)) {
      return InvalidReferenceLink.deserialize(s);
    } else {
      throw new FormatException("Illegal ref type: " + s);
    }
  }
}
