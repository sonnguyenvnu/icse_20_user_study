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
package jetbrains.mps.smodel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.model.SReference;

import java.util.HashSet;
import java.util.Set;

/**
 * Facility to collect actual external dependencies, like languages, concepts, and other models for a given model or set of nodes.
 * Walks a model and gathers information about nodes according to configuration.
 * By default, collects languages in use and references to external models.
 *
 * JFI, there's {@code ModelDependencyUpdate} in [project] that deals with import changes
 * @author Artem Tikhomirov
 */
public final class ModelDependencyScanner {

  private final Set<SLanguage> myUsedLanguages;
  private final Set<org.jetbrains.mps.openapi.model.SModelReference> myCrossModelReferences;
  private final Set<SConcept> myConcepts;
  private boolean myNeedLanguages = true;
  private boolean myNeedCrossModel = true;
  private boolean myNeedConcepts = false;

  public ModelDependencyScanner() {
    myUsedLanguages = new HashSet<>();
    myCrossModelReferences = new HashSet<>();
    myConcepts = new HashSet<>();
  }

  /**
   * Default: <code>true</code>
   */
  public ModelDependencyScanner usedLanguages(boolean collectUsedLanguages) {
    myNeedLanguages = collectUsedLanguages;
    return this;
  }

  /**
   * Default: <code>true</code>
   */
  public ModelDependencyScanner crossModelReferences(boolean crossModelReferences) {
    myNeedCrossModel = crossModelReferences;
    return this;
  }

  /**
   * Default: <code>false</code>
   */
  public ModelDependencyScanner usedConcepts(boolean collectConcepts) {
    myNeedConcepts = collectConcepts;
    return this;
  }

  @NotNull
  public Set<SLanguage> getUsedLanguages() {
    return myUsedLanguages;
  }

  @NotNull
  public Set<org.jetbrains.mps.openapi.model.SModelReference> getCrossModelReferences() {
    return myCrossModelReferences;
  }

  @NotNull
  public Set<SConcept> getConcepts() {
    return myConcepts;
  }

  /**
   * Iterate over all nodes of the given model
   * @return <code>this</code> for convenience
   */
  public ModelDependencyScanner walk(@NotNull SModel model) {
    return walk(SNodeUtil.getDescendants(model));
  }

  /**
   * Iterate over given nodes.
   * IMPORTANT! This method doesn't look into children of supplied nodes, it takes parameter 'literally' and visits
   * only nodes specified. To visit complete tree, consider using {@link SNodeUtil#getDescendants(SNode)}.
   *
   * @return <code>this</code> for convenience
   */
  public ModelDependencyScanner walk(@NotNull Iterable<org.jetbrains.mps.openapi.model.SNode> nodes) {
    HashSet<org.jetbrains.mps.openapi.model.SModelReference> allRefTargets = new HashSet<>();
    // collection of input nodes is not restricted to come from a single model,
    // hence we track models of nodes we iterate through to exclude them later from the set of cross-model.
    HashSet<org.jetbrains.mps.openapi.model.SModelReference> sourceModels = new HashSet<>();
    for (SNode n : nodes) {
      if (myNeedConcepts) {
        myConcepts.add(n.getConcept());
      }
      if (myNeedLanguages) {
        myUsedLanguages.add(n.getConcept().getLanguage());
      }
      if (myNeedCrossModel) {
        final org.jetbrains.mps.openapi.model.SModel sourceModel = n.getModel();
        if (sourceModel != null) {
          sourceModels.add(sourceModel.getReference());
        }
        for (SReference ref : n.getReferences()) {
          final org.jetbrains.mps.openapi.model.SModelReference targetModel = ref.getTargetSModelReference();
          if (targetModel != null) {
            allRefTargets.add(targetModel);
          }
        }
      }
    }
    if (myNeedCrossModel) {
      allRefTargets.removeAll(sourceModels);
      myCrossModelReferences.addAll(allRefTargets);
    }
    return this;
  }
}
