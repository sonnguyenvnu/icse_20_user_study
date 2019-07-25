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
package jetbrains.mps.smodel.adapter.structure.link;

import jetbrains.mps.smodel.adapter.structure.FormatException;
import jetbrains.mps.smodel.adapter.structure.concept.InvalidConcept;
import jetbrains.mps.smodel.runtime.LinkDescriptor;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * See InvalidConcept doc
 */
public final class InvalidContainmentLink extends SContainmentLinkAdapter {
  public static final java.lang.String INVALID_PREFIX = "i";
  @NotNull
  private final String myConcept;

  public InvalidContainmentLink(@Nullable String concept, @NotNull String name) {
    super(name);
    if (concept != null) {
      myConcept = concept;
    } else {
      //name is better to be a valid id. May be important on serialization
      myConcept = "UnknownConceptWithLink" + NameUtil.capitalize(name);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof InvalidContainmentLink)) {
      return false;
    }
    String otherId = ((InvalidContainmentLink) obj).myName;
    return myName.equals(otherId);
  }

  @Override
  public int hashCode() {
    return myName.hashCode();
  }

  @Override
  public String getRoleName() {
    return myName;
  }

  @Override
  @Nullable
  public LinkDescriptor getLinkDescriptor() {
    return null;
  }

  @Nullable
  @Override
  public SNode getDeclarationNode() {
    return null;
  }

  @NotNull
  @Override
  public SAbstractConcept getOwner() {
    return new InvalidConcept(myConcept);
  }

  @Override
  public String serialize() {
    return INVALID_PREFIX + ID_DELIM + myConcept + "." + myName;
  }

  public static InvalidContainmentLink deserialize(String s) {
    String marker = INVALID_PREFIX + ID_DELIM;
    if (!s.startsWith(marker)) {
      throw new FormatException("Invalid link should have prefix " + marker + ":" + s);
    }
    String data = s.substring(marker.length());
    String[] split = data.split("\\.");
    assert split.length == 2 : s;
    return new InvalidContainmentLink(split[0], split[1]);
  }
}
