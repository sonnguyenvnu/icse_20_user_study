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
package jetbrains.mps.smodel.adapter.structure.link;

import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;
import jetbrains.mps.smodel.language.ConceptRegistry;
import jetbrains.mps.smodel.runtime.ConceptDescriptor;
import jetbrains.mps.smodel.runtime.LinkDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;

public abstract class SContainmentLinkAdapter implements SContainmentLink {
  public static final String ID_DELIM = ":";

  protected String myName;

  protected SContainmentLinkAdapter(@NotNull String name) {
    myName = name;
  }

  @Nullable
  public abstract LinkDescriptor getLinkDescriptor();

  @Override
  public boolean isValid() {
    return getLinkDescriptor() != null;
  }

  @NotNull
  @Override
  public String getName() {
    return getRoleName();
  }

  @Override
  public String getRole() {
    return getRoleName();
  }

  @Override
  public boolean isOptional() {
    LinkDescriptor ld = getLinkDescriptor();
    if (ld == null) {
      return true;
    }

    return ld.isOptional();
  }

  @NotNull
  public abstract org.jetbrains.mps.openapi.language.SAbstractConcept getOwner();

  @NotNull
  @Override
  public SAbstractConcept getTargetConcept() {
    LinkDescriptor ld = getLinkDescriptor();
    if (ld == null) {
      return SNodeUtil.concept_BaseConcept;
    }

    SConceptId id = ld.getTargetConcept();
    ConceptDescriptor concept = ConceptRegistry.getInstance().getConceptDescriptor(id);
    return MetaAdapterFactory.getAbstractConcept(concept);
  }

  @Override
  public boolean isReference() {
    return false;
  }

  @Override
  public boolean isMultiple() {
    LinkDescriptor ld = getLinkDescriptor();
    if (ld == null) {
      return true;
    }

    return ld.isMultiple();
  }

  public boolean isUnordered() {
    LinkDescriptor ld = getLinkDescriptor();
    if (ld == null) {
      return false;
    }

    return ld.isUnordered();
  }

  @Override
  public String toString() {
    return getName();
  }

  public abstract String serialize();

  public static SContainmentLinkAdapter deserialize(String s) {
    if (s.startsWith(SContainmentLinkAdapterById.LINK_PREFIX)) {
      return SContainmentLinkAdapterById.deserialize(s);
    } else if (s.startsWith(InvalidContainmentLink.INVALID_PREFIX)) {
      return InvalidContainmentLink.deserialize(s);
    } else {
      throw new FormatException("Illegal link type: " + s);
    }
  }
}
