/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.findUsages;

import jetbrains.mps.smodel.ConceptDescendantsCache;
import org.jetbrains.mps.openapi.model.EditableSModel;
import org.jetbrains.mps.openapi.util.ProgressMonitor;
import org.jetbrains.mps.openapi.util.SubProgressKind;
import jetbrains.mps.util.IterableUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;
import org.jetbrains.mps.openapi.persistence.FindUsagesParticipant;
import org.jetbrains.mps.openapi.util.Consumer;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

class InstancesSearchType extends SearchType<SNode, SAbstractConcept> {
  private final boolean myExact;

  InstancesSearchType(boolean exact) {
    myExact = exact;
  }

  @Override
  public void search(Set<SAbstractConcept> elements, SearchScope scope, Consumer<SNode> consumer, @NotNull ProgressMonitor monitor) {
    assert !elements.contains(null);

    Collection<FindUsagesParticipant> participants = PersistenceFacade.getInstance().getFindUsagesParticipants();

    monitor.start("Finding usages...", participants.size() + 5);
    try {
      Set<SAbstractConcept> queryConcepts = new HashSet<>(elements);
      if (!myExact) {
        for (SAbstractConcept concept : elements) {
          queryConcepts.addAll(ConceptDescendantsCache.getInstance().getDescendants(concept));
        }
      }
      monitor.advance(1);

      Collection<SModel> current = new LinkedHashSet<>();
      Collection<SModel> simpleSearch = new LinkedHashSet<>();
      for (SModel m : IterableUtil.asCollection(scope.getModels())) {
        if (m instanceof EditableSModel && ((EditableSModel) m).isChanged()) {
          simpleSearch.add(m);
        } else {
          current.add(m);
        }
      }

      for (FindUsagesParticipant participant : participants) {
        final Set<SModel> next = new HashSet<>(current);
        participant.findInstances(current, queryConcepts, consumer, next::remove);
        current = next;
        monitor.advance(1);
      }

      ProgressMonitor subMonitor = monitor.subTask(4, SubProgressKind.DEFAULT);
      subMonitor.start("", current.size() + simpleSearch.size());
      showNoFastFindTipIfNeeded(current);
      current.addAll(simpleSearch);
      for (SModel m : current) {
        subMonitor.step(m.getName().getSimpleName());
        FindUsagesUtil.collectInstances(m, queryConcepts, consumer, monitor);
        if (monitor.isCanceled()) {
          break;
        }
        subMonitor.advance(1);
      }
      subMonitor.done();
    } finally {
      monitor.done();
    }
  }
}
