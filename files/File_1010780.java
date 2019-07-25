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
package jetbrains.mps.nodeEditor.menus;

import jetbrains.mps.smodel.constraints.ModelConstraints;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.function.Predicate;

/**
 * @author simon
 */
public class CanBeParentPredicate implements Predicate<SAbstractConcept> {
  @Nullable
  private final SNode myParentNode;

  @Nullable
  private final SContainmentLink myContainmentLink;

  @Deprecated
  @ToRemove(version = 2017.2)
  public CanBeParentPredicate(@Nullable SNode parentNode, @Nullable SContainmentLink link, @NotNull SRepository repository) {
    this(parentNode, link);
  }

  public CanBeParentPredicate(@Nullable SNode parentNode, @Nullable SContainmentLink containmentLink) {
    myParentNode = parentNode;
    myContainmentLink = containmentLink;
  }

  @Override
  public boolean test(@Nullable SAbstractConcept concept) {
    if (myParentNode == null) {
      return true;
    }
    if (concept == null) {
      return true;
    }
    return (myContainmentLink == null || ModelConstraints.canBeParent(myParentNode, concept, myContainmentLink,  null)) &&
        ModelConstraints.canBeAncestor(myParentNode, concept, myContainmentLink, null);
  }
}
