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
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.ConceptRegistryUtil;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

public final class SConceptAdapterById extends SConceptAdapter implements SConcept {
  public static final String CONCEPT_PREFIX = "c";
  private final SConceptId myConceptId;
  private final boolean myIsBootstrap;

  public SConceptAdapterById(@NotNull SConceptId conceptId, @NotNull String fqname) {
    this(conceptId, fqname, false);
  }

  /**
   * @param bootstrap see BOOTSTRAP META OBJECTS javadoc for {@link jetbrains.mps.smodel.adapter.BootstrapAdapterFactory}
   */
  public SConceptAdapterById(@NotNull SConceptId conceptId, @NotNull String fqname, boolean bootstrap) {
    super(fqname);
    myConceptId = conceptId;
    myIsBootstrap = bootstrap;
  }

  @Override
  public boolean isAbstract() {
    ConceptDescriptor d = getConceptDescriptor();
    //isInterfaceConcept means this is a "fake concept" and needs to be considered as abstract
    return d == null || d.isAbstract() || d.isInterfaceConcept();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SConceptAdapterById)) {
      return false;
    }
    SConceptId otherId = ((SConceptAdapterById) obj).myConceptId;
    return myConceptId.equals(otherId);
  }

  @Override
  protected boolean isSubConceptOfSpecial(@NotNull ConceptDescriptor thisDescriptor, ConceptDescriptor anotherDescriptor, SAbstractConcept anotherConcept) {
    return thisDescriptor.isAssignableTo(anotherDescriptor.getId());
  }

  @Override
  public int hashCode() {
    return (int) myConceptId.getIdValue();
  }

  @Override
  public String getQualifiedName() {
    if (RuntimeFlags.isMergeDriverMode() || myIsBootstrap) {
      return myFqName;
    }
    ConceptDescriptor cd = getConceptDescriptor();
    if (cd == null) {
      //invalid concept
      return myFqName;
    }
    return cd.getConceptFqName();
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
    return CONCEPT_PREFIX + ID_DELIM + myConceptId.serialize() + ID_DELIM + getQualifiedName();
  }

  public static SConceptAdapterById deserialize(String s) {
    String marker = CONCEPT_PREFIX + ID_DELIM;
    if (!s.startsWith(marker)) {
      throw new FormatException("Serialized form should have prefix " + marker + ":" + s);
    }
    String data = s.substring(marker.length());
    String[] split = data.split(ID_DELIM);
    if (split.length != 2) {
      throw new FormatException("Serialized form should have 2 components: " + data);
    }
    SConcept res = MetaAdapterFactory.getConcept(SConceptId.deserialize(split[0]), split[1]);
    if (!(res instanceof SConceptAdapterById)) {
      throw new FormatException("Type differs from requested: " + res.getClass().getName());
    }
    return (SConceptAdapterById) res;
  }
}
