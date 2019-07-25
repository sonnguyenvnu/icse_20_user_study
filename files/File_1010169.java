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
package jetbrains.mps.generator.impl.plan;

import jetbrains.mps.generator.runtime.TemplateMappingConfiguration;
import jetbrains.mps.project.structure.modules.mappingpriorities.MappingPriorityRule;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.util.CollectionUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author evgeny, 3/10/11
 * @author Artem Tikhomirov
 */
public class PartitioningSolver {

  private final PriorityGraph myPriorityGraph;
  /*
   *   Each entry defines a set of mappings, which should be applied together.
   */
  private final List<Group> mySameStepGroups = new ArrayList<>();
  private final PriorityConflicts myConflicts;
  private final Set<TemplateMappingConfiguration> myConfigurations = new LinkedHashSet<>();

  public PartitioningSolver(@NotNull PriorityConflicts priorityConflicts) {
    myConflicts = priorityConflicts;
    myPriorityGraph = new PriorityGraph();
  }

  public void prepare(Collection<TemplateMappingConfiguration> mc) {
    myConfigurations.addAll(mc);
  }

  public Set<TemplateMappingConfiguration> getKnownMapConfigs() {
    return Collections.unmodifiableSet(myConfigurations);
  }

  public void registerCoherent(Collection<TemplateMappingConfiguration> coherentMappings, MappingPriorityRule rule) {
    ArrayList<Group> groups = new ArrayList<>(coherentMappings.size());
    for (TemplateMappingConfiguration m : coherentMappings) {
      groups.add(new Group(m));
    }
    boolean withConflicts = false;
    for (int i = 1, x = groups.size(); i < x; i++) {
      final Group prev = groups.get(i - 1);
      final Group curr = groups.get(i);
      if (prev.isTopPriority() != curr.isTopPriority()) {
        myConflicts.registerCoherentPriorityMix(prev, curr, rule);
        withConflicts = true;
      }
    }
    if (withConflicts) {
      return; // drop set of conflicting coherent rules
    }
    mySameStepGroups.add(new Group(groups));
  }

  // TMC from hiPrio are executed first, then TMC from loPrio are executed
  public void establishDependency(Collection<TemplateMappingConfiguration> hiPrio, Collection<TemplateMappingConfiguration> loPrio, MappingPriorityRule rule) {
    // map: lo-pri mapping -> {hi-pri mapping, .... , hi-pri mapping }
    loPrio = CollectionUtil.subtract(loPrio, hiPrio);

    for (TemplateMappingConfiguration lesserPriMapping : loPrio) {
      myPriorityGraph.addEdge(lesserPriMapping, hiPrio, rule);
    }
  }

  List<GenerationPhase> solve() {
    myPriorityGraph.finalizeEdges(myConfigurations);

    myPriorityGraph.checkSelfLocking(myConflicts);
    myPriorityGraph.checkLowPrioLocksTopPrio(myConflicts);

    final Collection<Group> cycles = myPriorityGraph.removeWeakCycles();
    mySameStepGroups.addAll(cycles);

    List<Group> coherentMappings = joinSameStepMappings();
    myPriorityGraph.updateWithCoherent(coherentMappings, myConflicts);

    myPriorityGraph.replaceWeakEdgesWithStrict();

    boolean topPriorityGroup = false;
    ArrayDeque<Collection<Group>> stack = new ArrayDeque<>();
    while (!myPriorityGraph.isEmpty()) {
      Collection<Group> step = myPriorityGraph.getGroupsNotInDependency();
      if (step.isEmpty()) {
        // non-empty graph but no independent groups
        myPriorityGraph.reportEdgesLeft(myConflicts);
        break;
      }
      for (Iterator<Group> it = step.iterator(); it.hasNext(); ) {
        if (it.next().isTopPriority() != topPriorityGroup) {
          it.remove();
        }
      }
      if (step.isEmpty()) {
        if (topPriorityGroup) {
          myPriorityGraph.reportEdgesLeft(myConflicts);
          break;
        }
        topPriorityGroup = true;
      } else {
        stack.push(step);
        myPriorityGraph.dropEdgesOf(step);
      }
    }
    ArrayList<GenerationPhase> rv = new ArrayList<>(stack.size());
    while (!stack.isEmpty()) {
      rv.add(new GenerationPhase(stack.pop()));
    }
    return rv;
  }

  /**
   * Process groups of 'run together' to join intersecting into a single group
   */
  private List<Group> joinSameStepMappings() {
    ArrayList<Group> rv = new ArrayList<>(mySameStepGroups.size());
    ArrayList<Group> toMerge = new ArrayList<>();
    LinkedList<Group> queue = new LinkedList<>(mySameStepGroups);
    while (!queue.isEmpty()) {
      Group head = queue.removeFirst();
      for (Iterator<Group> it = queue.iterator(); it.hasNext(); ) {
        Group g = it.next();
        if (head.hasCommonMappings(g)) {
          // the way mySameStepGroups are checked for same topPri setting at construction ensures single group is consistent
          // and hence two intersecting groups can't fail this
          assert head.isTopPriority() == g.isTopPriority();
          toMerge.add(g);
          it.remove();
        }
      }
      if (toMerge.isEmpty()) {
        // no groups with common mappings, add current group as is
        rv.add(head);
      } else {
        // get a new group that combines all mappings and rules of the same step
        toMerge.add(head);
        // there are chances there are more groups, that didn't intersect with head, but
        // intersect with one of merged, and we need to check for these again
        queue.add(new Group(toMerge));
        toMerge.clear();
      }
    }
    return rv;
  }

  static void sort(List<TemplateMappingConfiguration> mappingSet) {
    Collections.sort(mappingSet, (o1, o2) -> SNodePointer.serialize(o1.getMappingNode()).compareTo((SNodePointer.serialize(o2.getMappingNode()))));
  }
}
