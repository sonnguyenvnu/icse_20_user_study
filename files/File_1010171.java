/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
import jetbrains.mps.project.structure.modules.mappingpriorities.RuleType;
import jetbrains.mps.util.containers.MultiMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Graph of Groups, where each group constitutes one or more map configurations.
 *
 * Invariant, once #finalizeEdges has been invoked:
 *    foreach entry1 in myRulePriorityEntries {
 *      Exists entry2 in myRulePriorityEntries : entry1.sooner == entry2.later
 *    }
 *    In other words, for rules A < C, B < C we don't keep single edge AB < C, but two distinct edges.
 *    Groups are merged when there's 'strictly together' relation between them, and all rules including parts
 *    of the merged group are updated to use this new merged node.
 *    I.e. with A == B rule, AB < C (and, if no other rule present, 0 < AB) would replace both A < C and B < C rules.
 *    Dependencies outside of 'strictly together' group are kept as separate edges, i.e. B = D would yield
 *    A < C, BD < C (and, perhaps, 0 < BD) rules, not ABD < C or {A, BD} < C
 *
 *    As a most notable consequence, Group.includes and Group.hasCommonMappings() shall be used exclusively
 *    when dealing with incomplete coherent groups.
 * @author Artem Tikhomirov
 */
class PriorityGraph {
  // graph edges
  private final List<Entry> myRulePriorityEntries;
  private final Set<TemplateMappingConfiguration> myNonTrivialEdges;

  public PriorityGraph() {
    myRulePriorityEntries = new LinkedList<>();
    myNonTrivialEdges = new HashSet<>();
  }

  public void addEdge(TemplateMappingConfiguration tmc, Collection<TemplateMappingConfiguration> appliedSooner, MappingPriorityRule rule) {
    boolean isStrict = rule.getType() == RuleType.STRICTLY_BEFORE || rule.getType() == RuleType.STRICTLY_AFTER;
    final Group lowPriorityGroup = new Group(tmc);
    final Set<MappingPriorityRule> ruleSet = Collections.singleton(rule);
    for (TemplateMappingConfiguration sooner : appliedSooner) {
      myRulePriorityEntries.add(new Entry(new Group(sooner), lowPriorityGroup, isStrict, ruleSet));
    }
    myNonTrivialEdges.add(tmc);
  }

  public void finalizeEdges(Collection<TemplateMappingConfiguration> allMapConfigurations) {
    HashSet<TemplateMappingConfiguration> trivialEdges = new HashSet<>(allMapConfigurations);
    trivialEdges.removeAll(myNonTrivialEdges);
    for (TemplateMappingConfiguration tmc : trivialEdges) {
      myRulePriorityEntries.add(newTrivialEdge(new Group(tmc)));
    }
  }

  public void replaceWeakEdgesWithStrict() {
    // inv: !weakEntry.isStrict && !weakEntry.isTrivial
    final ArrayDeque<Entry> weakEntries = new ArrayDeque<>();
    for (Entry entry : myRulePriorityEntries) {
      if (!entry.isStrict() && !entry.isTrivial()) {
        weakEntries.add(entry);
      }
    }
    if (Boolean.TRUE.booleanValue()) {
      for (Entry weak : weakEntries) {
        weak.makeStrict();
      }
      return;
    }
    /* sooner   later
     *     A <= B
     *     B  < C
     *     B <= D
     *     X  < A
     *     Y <= A
     */
    while (!weakEntries.isEmpty()) {
      Entry weak = weakEntries.removeFirst();
      myRulePriorityEntries.remove(weak); // weak edge will be replaced with new edges (either strong or weak)
      Collection<Entry> toAdd = new ArrayList<>();
      for (Entry entry : myRulePriorityEntries) {
        if (entry.isTrivial()) {
          // trivial edges are there just for graph completeness, and should not take part in transformations
          continue;
        }
        // entry dependency may be substituted for weak dependency
        final boolean substituteForSooner = entry.later().equals(weak.sooner());
        // entry depends on weak dependency
        final boolean dependsOnWeak = entry.sooner().equals(weak.later());
        if (!substituteForSooner && !dependsOnWeak) {
          continue;
        }
        final Entry newEntry;
        HashSet<MappingPriorityRule> mergedRules = new HashSet<>(entry.getRules());
        mergedRules.addAll(weak.getRules());
        if (substituteForSooner) {
          // A <= B; X < A, Y <= A   -->  add rules X < B, Y <= B
          newEntry = new Entry(entry.sooner(), weak.later(), entry.isStrict(), mergedRules);
        } else {
          assert dependsOnWeak;
          // A <= B; B < C, B <= D   --> add rules A < C, A <= D
          newEntry = new Entry(weak.sooner(), entry.later(), entry.isStrict(), mergedRules);
        }
        toAdd.add(newEntry);
        if (!newEntry.isStrict() && !newEntry.isTrivial()) {
          weakEntries.add(newEntry); // update queue with new edge to handle
        }
      }
      if (toAdd.isEmpty()) {  // if there's no change
        // neither lhs nor rhs of the weak edge is part of any other edge, it's safe to replace it with strict
        toAdd.add(weak.makeStrict());
        // A <= B without any dependant rules effectively means A < B
      } else {
        /* Start: A <= B, X < A, B < Y
         * toAdd:         X < B, A < Y
         * Decision to make: if there's relation between X and Y, we're all set
         *                   if not, we need a rule to tell A 'not later' B
         */
        // What new lhs and rhs groups did we get?
        HashSet<Group> addedSooner = new HashSet<>();
        HashSet<Group> addedLater = new HashSet<>();
        for (Entry e : toAdd) {
          addedSooner.add(e.sooner());
          addedLater.add(e.later());
        }
        addedSooner.remove(weak.sooner());
        addedLater.remove(weak.later());
        /*
         * If there are existing edges X < Z and Z < Y, we build a closure for addedSooner X={Z,Y}
         * to see it intersects with addedLater.
         * If any element in rhs appears as 'after' of any element in lhs in existing rules,
         * then this ensures weak dependency being processed is kept. If elements in rhs
         * are not in any relation with lhs elements, then we need explicit edge to record 'not later' knowledge
         * of the current weak edge.
         */
        TransitiveClosure<Group> closureBuilder = new TransitiveClosure<>();
        for (Entry e : myRulePriorityEntries) {
          if (!e.isTrivial()) {
            closureBuilder.feed(e.sooner(), e.later());
          }
        }
        // all elements to show up later than those we've added as 'sooner' for our weak.later()
        // iow, 'not later' than weak.later()
        HashSet<Group> closure = new HashSet<>();
        for (Group l : addedSooner) {
          closure.addAll(closureBuilder.closure(l));
        }
        // see if newly added later (than weak.sooner) elements are in relation with the closure
        closure.retainAll(addedLater);
        if (closure.isEmpty()) {
          // nope, can't get from addedSooner to addedLater
          toAdd.add(weak.makeStrict());
        }
      }
      myRulePriorityEntries.addAll(toAdd);
    }
  }

  // pre: groups in coherentMappings do not intersect
  public void updateWithCoherent(List<Group> coherentMappings, PriorityConflicts conflicts) {
    // if any of 'coherent' mappings happens before another group, make this group dependant from all coherent mappings.
    // if there's no mapping that establish relation for coherent mapping (i.e. only 'trivial' mappings), replace these trivial mappings with single
    // one with the coherent group
    Collection<Entry> toRemove = new HashSet<>();
    for (Group g : coherentMappings) {
      Collection<Entry> hiPriCoherentToAdd = new HashSet<>();
      Collection<Entry> loPriCoherentToAdd = new HashSet<>();
      boolean coherentGroupNeedsTrivialEdge = true;
      for (Entry entry : myRulePriorityEntries) {
        final boolean soonerMatches = g.includes(entry.sooner());
        final boolean laterMatches = g.includes(entry.later());
        if (!soonerMatches && !laterMatches) {
          continue;
        }
        if (soonerMatches && laterMatches) {
          if (entry.isStrict()) {
            // same TMC on both sides of the strict rule
            conflicts.registerCoherentWithStrict(g, entry.sooner(), entry.getRules());
          }
          toRemove.add(entry); // no reason to keep AB <= AB entry;
          // if there would be no other edge with coherent group, coherentGroupNeedsTrivialEdge ensures coherent group doesn't get lost
          continue;
        }
        toRemove.add(entry);
        if (soonerMatches) {
          // coherent group matches sooner/hi-pri side
          // introduce a new edge, from entry's later to coherent group
          hiPriCoherentToAdd.add(new Entry(g, entry.later(), entry.isStrict(), entry.getRules()));
        }
        if (laterMatches) {
          // coherent group matches low-pri side.
          // There's little value replacing 'element of coherent'->empty. with 'coherent group'->empty, unless there are no other rules.
          // I use coherentGroupNeedsTrivialEdge to track if there's an entry 'coherent'->non-empty
          if (!entry.isTrivial()) {
            loPriCoherentToAdd.add(new Entry(entry.sooner(), g, entry.isStrict(), entry.getRules()));
            coherentGroupNeedsTrivialEdge = false;
          }
        }
      }

      HashSet<Entry> toAdd = new HashSet<>();
      // Remove duplicates, A<X, B<X, C<X, {ABC} is replaced with single {ABC} < X instead of 3 equivalent edges
      MultiMap<Group, Entry> groupByLater = new MultiMap<>();
      for (Entry e : hiPriCoherentToAdd) {
        assert e.sooner().equals(g);
        groupByLater.putValue(e.later(), e);
      }
      for (Group loPri : groupByLater.keySet()) {
        Set<MappingPriorityRule> involvedRules = new HashSet<>();
        boolean atLeastOneStrict = false; // A < X, B <= X, {AB} - strict edge if there's at least 1 strict edge
        for (Entry e : groupByLater.get(loPri)) {
          involvedRules.addAll(e.getRules());
          atLeastOneStrict |= e.isStrict();
        }
        toAdd.add(new Entry(g, loPri, atLeastOneStrict, involvedRules));
      }
      // Remove duplicates, X<A, X<B, X<C, {ABC} is replaced with single X < {ABC} instead of 3 equivalent edges
      MultiMap<Group, Entry> groupBySooner = new MultiMap<>();
      for (Entry e : loPriCoherentToAdd) {
        assert e.later().equals(g);
        groupBySooner.putValue(e.sooner(), e);
      }
      for (Group hiPri : groupBySooner.keySet()) {
        Set<MappingPriorityRule> involvedRules = new HashSet<>();
        boolean atLeastOneStrict = false; // X < A, X <= B, {AB} - strict edge if there's at least 1 strict edge
        for (Entry e : groupBySooner.get(hiPri)) {
          involvedRules.addAll(e.getRules());
          atLeastOneStrict |= e.isStrict();
        }
        toAdd.add(new Entry(hiPri, g, atLeastOneStrict, involvedRules));
      }
      //
      //
      if (coherentGroupNeedsTrivialEdge) {
        toAdd.add(newTrivialEdge(g));
      }
      myRulePriorityEntries.addAll(toAdd);
      myRulePriorityEntries.removeAll(toRemove);
      toRemove.clear();
    }
  }


  public Collection<Group> getGroupsNotInDependency() {
    HashSet<Group> rv = new HashSet<>();
    // all groups that appear at 'sooner' side of rules
    HashSet<Group> allSoonerGroups = new HashSet<>(myRulePriorityEntries.size() * 2);
    // there might be multiple dependency edges from a single node, no need to check same node more than once
    HashSet<Group> uniqueLaterGroups = new HashSet<>(myRulePriorityEntries.size() * 2);
    for (Entry e : myRulePriorityEntries) {
      if (!e.isTrivial()) {
        allSoonerGroups.add(e.sooner());
      }
      uniqueLaterGroups.add(e.later());
    }
    for (Group candidate : uniqueLaterGroups) {
      if (!allSoonerGroups.contains(candidate)) {
        rv.add(candidate);
      }
    }
    return rv;
  }

  public void dropEdgesOf(Collection<Group> groups) {
    for (Iterator<Entry> it = myRulePriorityEntries.iterator(); it.hasNext(); ) {
      if (groups.contains(it.next().later())) {
        it.remove();
      }
    }
    for (Entry entry : myRulePriorityEntries) {
      if (groups.contains(entry.sooner())) {
        entry.makeTrivial();
      }
    }
  }

  // XXX next methods (including, but not limited to those with PriorityConflicts) cry for edge iterator
  // (either external or internal), so that we can decouple graph and alg impl from error checking

  void checkSelfLocking(PriorityConflicts conflicts) {
    for (Entry edge : myRulePriorityEntries) {
      if (edge.sooner().equals(edge.later())) {
        if (edge.isStrict()) {
          conflicts.registerSelfLock(edge.sooner(), edge.later(), edge.getRules());
        }
        // remove self-lock
        edge.makeTrivial();
      }
    }
  }

  void checkLowPrioLocksTopPrio(PriorityConflicts conflicts) {
    ArrayList<Entry> toDrop = new ArrayList<>();
    for (Entry edge : myRulePriorityEntries) {
      if (edge.isTrivial()) {
        continue;
      }
      if (edge.later().isTopPriority() && !edge.sooner().isTopPriority()) {
        conflicts.registerLoPriLocksHiPri(edge.sooner(), edge.later(), edge.getRules());
        toDrop.add(edge);
      }
    }
    myRulePriorityEntries.removeAll(toDrop);
  }

  /**
   * Cycle of weak rules A <= B, B <= C, C <= A  is transformed into a single 'same step' group {ABC}.
   */
  Collection<Group> removeWeakCycles() {
    CycleDetector cd = new CycleDetector();
    for (Entry edge : myRulePriorityEntries) {
      if (edge.isTrivial() || edge.isStrict()) {
        continue;
      }
      cd.feed(edge);
    }
    ArrayList<Group> rv = new ArrayList<>();
    HashSet<Entry> toDrop = new HashSet<>();
    Collection<Cycle> cycles = cd.detect();
    for (Cycle c : cycles) {
      rv.add(new Group(c.elements));
      toDrop.addAll(c.edges);
    }
    myRulePriorityEntries.removeAll(toDrop);
    return rv;
  }

  void reportEdgesLeft(PriorityConflicts conflicts) {
    CycleDetector cd = new CycleDetector();
    HashSet<MappingPriorityRule> rules = new HashSet<>();
    for (Entry edge : myRulePriorityEntries) {
      if (edge.isTrivial()) {
        continue;
      }
      cd.feed(edge);
      rules.addAll(edge.getRules());
    }
    conflicts.registerLeftovers(rules);
    for (Cycle c : cd.detect()) {
      conflicts.registerCycle(c);
    }
  }

  public boolean isEmpty() {
    return myRulePriorityEntries.isEmpty();
  }

  public void dump() {
    for (Entry entry : myRulePriorityEntries) {
      System.out.println(entry);
    }
  }

  private static Entry newTrivialEdge(Group g) {
    return new Entry(new Group(), g, false, Collections.emptyList());
  }

  // Edge of dependency graph
  private static class Entry {
    private Group myLaterGroup;
    private Group mySoonerGroup;
    private boolean myStrict;
    // rules this relation originates from
    private final Set<MappingPriorityRule> myRules;

    public Entry(Group highPriorityGroup, Group lowPriorityGroup, boolean strict, Collection<MappingPriorityRule> rules) {
      myLaterGroup = lowPriorityGroup;
      mySoonerGroup = highPriorityGroup;
      myStrict = strict;
      myRules = new HashSet<>(rules);
    }

    public Group later() {
      return myLaterGroup;
    }

    public Group sooner() {
      return mySoonerGroup;
    }

    public Set<MappingPriorityRule> getRules() {
      return myRules;
    }

    public boolean isStrict() {
      return myStrict;
    }

    // Trivial edge is auxiliary, merely a mechanism to complete graph and to report all the vertices during topological sorting
    public boolean isTrivial() {
      return mySoonerGroup.isEmpty();
    }

    public void makeTrivial() {
      mySoonerGroup = new Group();
    }

    public Entry makeStrict() {
      myStrict = true;
      return this;
    }

    @Override
    public String toString() {
      return String.format("%s %s %s", mySoonerGroup, isStrict() ? '<' : '\u2264', myLaterGroup);
    }

  }

  static class CycleDetector {
    private MultiMap<Group, Entry> soonerToEntry = new MultiMap<>();
    private TransitiveClosure<Group> soonerToLater = new TransitiveClosure<>();

    public void feed(Entry edge) {
      soonerToEntry.putValue(edge.sooner(), edge);
      soonerToLater.feed(edge.sooner(), edge.later());
    }

    Collection<Cycle> detect() {
      HashSet<Cycle> rv = new HashSet<>();
      for (Group g : soonerToEntry.keySet()) {
        // build closure of all possible rhs (later) elements
        // i.e. for A <= B, B <= C, C <= D, A <= X and given A, builds A := B, C, D, X
        Set<Group> rhsClosure = soonerToLater.closure(g);
        if (!rhsClosure.contains(g)) {
          continue;
        }
        HashSet<Group> actualCycleParticipants = new HashSet<>();
        HashSet<Entry> toDrop = new HashSet<>();
        for (Group cycleElementCandidate : rhsClosure) {
          boolean isActualCycleElement = false;
          // element in the rhsClosure not necessarily part of the cycle,
          // e.g. A <= B, B <= A, A <= C, rhcClosure is B,C,A, but C is not in the cycle
          for (Entry edge : soonerToEntry.get(cycleElementCandidate)) {
            assert rhsClosure.contains(edge.sooner()); // that's the way we've built soonerToEntry
            if (rhsClosure.contains(edge.later())) {
              // both sides of the rule are in rhsClosure, edge is part of the cycle then
              toDrop.add(edge);
              isActualCycleElement = true;
            }
          }
          if (isActualCycleElement) {
            actualCycleParticipants.add(cycleElementCandidate);
          }
        }
        assert !actualCycleParticipants.isEmpty(); // rhsClosure.contains(g) ensures there's indeed a cycle (at least one-element, A <= A)
        rv.add(new Cycle(actualCycleParticipants, toDrop));
      }
      return rv;
    }
  }

  static class Cycle {
    public final Set<Group> elements; // all graph nodes that build up a cycle
    public final Set<Entry> edges; // edges involved into the cycle
    public Cycle(Set<Group> elements, Set<Entry> edges) {
      this.elements = elements;
      this.edges = edges;
    }

    // Rules involved in cycle inception
    public Collection<MappingPriorityRule> getRules() {
      HashSet<MappingPriorityRule> rv = new HashSet<>();
      for (Entry edge : edges) {
        rv.addAll(edge.getRules());
      }
      return rv;
    }

    @Override
    public int hashCode() {
      return elements.hashCode() + edges.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Cycle)) {
        return false;
      }
      Cycle o = ((Cycle) obj);
      return elements.equals(o.elements) && edges.equals(o.edges);
    }
  }

  /**
   * For a transitive relation, builds a closure of elements. The closure doesn't contain the starting one, unless there's a cycle.
   * E.g. fed with elements: AxB, BxC, CxD, produces for A: {B,C,D}, for C: {D}
   * With another element, DxA, produces for A: {B,C,D,A}, for C:{D,A,B,C}
   */
  private static class TransitiveClosure<T> {
    private final MultiMap<T,T> myMap = new MultiMap<>();
    public void feed(T left, T right) {
      myMap.putValue(left, right);
    }
    public Set<T> closure(T element) {
      HashSet<T> rhsClosure = new HashSet<>();
      ArrayDeque<T> rhsQueue = new ArrayDeque<>();
      rhsQueue.addAll(myMap.get(element));
      while (!rhsQueue.isEmpty()) {
        T rhs = rhsQueue.removeFirst();
        if (rhsClosure.add(rhs)) {
          rhsQueue.addAll(myMap.get(rhs));
        }
      }
      return rhsClosure;
    }
  }
}
