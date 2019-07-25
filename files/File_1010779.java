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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.function.Predicate;

/**
 * @author simon
 */
public class CanBeChildPredicate implements Predicate<SAbstractConcept> {
  @NotNull
  private final SNode myParentNode;

  @Nullable
  private final SContainmentLink myContainmentLink;

  public CanBeChildPredicate(@NotNull SNode parentNode, @Nullable SContainmentLink link) {
    myParentNode = parentNode;
    myContainmentLink = link;
  }

  @Override
  public boolean test(@Nullable SAbstractConcept concept) {
    return concept == null || ModelConstraints.canBeChild(myParentNode, concept, myContainmentLink, null);
  }
}
