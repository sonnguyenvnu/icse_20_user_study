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
package jetbrains.mps.smodel.adapter.structure.concept;

import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.language.InvalidLanguage;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SInterfaceConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collections;

/**
 * This adapter means that MPS needed to load some concept but was unable to find it.
 * Methods of this class are not intended to be executed (though for now it might not be that way)
 * It will be ok if we throw an exception from methods except isValid sometimes. This can be done when we'll be able
 * to handle missing languages correctly in every subsystem
 */
public class InvalidConcept extends SAbstractConceptAdapter implements SConcept, SInterfaceConcept {
  public static final String INVALID_PREFIX = "invalid";

  public InvalidConcept(@NotNull String fqname) {
    super(fqname);
  }

  @Override
  public boolean isAbstract() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof InvalidConcept)) {
      return false;
    }
    return myFqName.equals(((InvalidConcept) obj).myFqName);
  }

  @Override
  public int hashCode() {
    return myFqName.hashCode();
  }

  @Override
  public String getQualifiedName() {
    return myFqName;
  }

  @Override
  @Nullable
  public ConceptDescriptor getConceptDescriptor() {
    return null;
  }

  @NotNull
  @Override
  public SLanguage getLanguage() {
    return new InvalidLanguage(NameUtil.namespaceFromConceptFQName(myFqName));
  }

  @Override
  protected SNode findInModel(SModel structureModel) {
    return null;
  }

  @Nullable
  @Override
  public SConcept getSuperConcept() {
    return null;
  }

  @Override
  public Iterable<SInterfaceConcept> getSuperInterfaces() {
    return Collections.emptyList();
  }

  @Override
  public boolean isRootable() {
    return false;
  }

  @Override
  protected boolean isSubConceptOfSpecial(@NotNull ConceptDescriptor thisDescriptor, ConceptDescriptor anotherDescriptor, SAbstractConcept anotherConcept) {
    return false;
  }

  @Override
  public String serialize() {
    return INVALID_PREFIX + ID_DELIM + myFqName;
  }

  public static InvalidConcept deserialize(String s) {
    String marker = INVALID_PREFIX + ID_DELIM;
    if (!s.startsWith(marker)) {
      throw new FormatException("Invalid concept should have prefix " + marker + ":" + s);
    }
    return new InvalidConcept(s.substring(marker.length()));
  }
}
