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
package jetbrains.mps.smodel.adapter.structure.concept;

import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.smodel.SNodeId.Regular;
import jetbrains.mps.smodel.adapter.ids.MetaIdFactory;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.ConceptRegistryUtil;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SInterfaceConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

public final class SInterfaceConceptAdapterById extends SInterfaceConceptAdapter implements SInterfaceConcept {
  public static final String INTERFACE_PREFIX = "i";
  private final SConceptId myConceptId;
  private final boolean myIsBootstrap;

  public SInterfaceConceptAdapterById(@NotNull SConceptId conceptId, @NotNull String fqname) {
    this(conceptId, fqname, false);
  }

  public SInterfaceConceptAdapterById(@NotNull SConceptId conceptId, @NotNull String fqname, boolean bootstrap) {
    super(fqname);
    myConceptId = conceptId;
    myIsBootstrap = bootstrap;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SInterfaceConceptAdapterById)) {
      return false;
    }
    SConceptId otherId = ((SInterfaceConceptAdapterById) obj).myConceptId;
    return myConceptId.equals(otherId);
  }

  @Override
  public int hashCode() {
    return (int) myConceptId.getIdValue();
  }

  @Override
  @Nullable
  public ConceptDescriptor getConceptDescriptor() {
    //this check is better to be moved to isValid as soon as we have ids in AbstractConceptAdapter
    if (myConceptId.equals(MetaIdFactory.INVALID_CONCEPT_ID)) {
      return null;
    }
    return ConceptRegistryUtil.getConceptDescriptor(myConceptId);
  }

  @NotNull
  public SConceptId getId() {
    return myConceptId;
  }

  @Override
  public String getQualifiedName() {
    if (RuntimeFlags.isMergeDriverMode() || myIsBootstrap) {
      return myFqName;
    }
    ConceptDescriptor cd = getConceptDescriptor();
    if (cd == null) {
      return myFqName; //invalid concept
    }
    return cd.getConceptFqName();
  }

  @Override
  protected boolean isSubConceptOfSpecial(@NotNull ConceptDescriptor thisDescriptor, ConceptDescriptor anotherDescriptor, SAbstractConcept anotherConcept) {
    return thisDescriptor.isAssignableTo(anotherDescriptor.getId());
  }

  @NotNull
  @Override
  public SLanguage getLanguage() {
    return MetaAdapterFactory.getLanguage(myConceptId.getLanguageId(), NameUtil.namespaceFromConceptFQName(myFqName));
  }

  @Override
  protected boolean isBootstrap() {
    return myIsBootstrap;
  }

  @Override
  protected SNode findInModel(SModel structureModel) {
    return structureModel.getNode(new Regular(myConceptId.getIdValue()));
  }

  @Override
  public String serialize() {
    return INTERFACE_PREFIX + ID_DELIM + myConceptId.serialize() + ID_DELIM + getQualifiedName();
  }

  public static SInterfaceConceptAdapterById deserialize(String s) {
    String marker = INTERFACE_PREFIX + ID_DELIM;
    if (!s.startsWith(marker)) {
      throw new FormatException("Serialized form should have prefix " + marker + ":" + s);
    }
    String data = s.substring(marker.length());
    String[] split = data.split(ID_DELIM);
    if (split.length != 2) {
      throw new FormatException("Serialized form should have 2 components: " + data);
    }
    SInterfaceConcept res = MetaAdapterFactory.getInterfaceConcept(SConceptId.deserialize(split[0]), split[1]);
    if (!(res instanceof SInterfaceConceptAdapterById)) {
      throw new FormatException("Type differs from requested: "+res.getClass().getName());
    }
    return (SInterfaceConceptAdapterById) res;
  }
}
