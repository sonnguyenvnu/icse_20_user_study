/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.newTypesystem;

import gnu.trove.THashSet;
import jetbrains.mps.lang.pattern.util.IMatchModifier;
import jetbrains.mps.lang.pattern.util.MatchingUtil;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.lang.typesystem.runtime.HUtil;
import jetbrains.mps.newTypesystem.state.Equations;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.smodel.SModelUtil_new;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.typesystem.inference.EquationInfo;
import jetbrains.mps.typesystemEngine.util.LatticeUtil;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TypesUtil {

  public static boolean isVariable(SNode node) {
    return HUtil.isRuntimeTypeVariable(node);
  }

  public static boolean canBeVariable(SNode node) {
    return isVariable(node) || LatticeUtil.isMeet(node) && hasVariablesInside(node);
  }

  public static boolean hasVariablesInside(SNode node) {
    if (node == null) {
      return false;
    }
    if (TypesUtil.isVariable(node)) {
      return true;
    }
    for (SNode child : node.getChildren()) {
      if (TypesUtil.hasVariablesInside(child)) {
        return true;
      }
    }
    for (SNode referent : getNodeReferents(node)) {
      if (referent != null && TypesUtil.isVariable(referent)) {
        return true;
      }
    }
    return false;
  }

  public static int depth(SNode sNode) {
    int childDepth = 0;
    for (SNode child : sNode.getChildren()) {
      int depth = depth(child);
      if (childDepth < depth) {
        childDepth = depth;
      }
    }
    if (!SNodeOperations.isInstanceOf(sNode, SNodeUtil.concept_SNodeType)) return childDepth + 1;
    if (sNode.getReference(SNodeUtil.ref_SNodeType_concept) == null) return childDepth + 1;
    return childDepth + 2;
  }


  public static List<SNode> getVariables(SNode node, State state) {
    List<SNode> result = new LinkedList<>();
    getVariablesInside(node, result, state);
    return result;
  }

  private static void getVariablesInside(SNode node, List<SNode> result, State state) {
    if (state != null) {
      node = state.getRepresentative(node);
    }
    if (node == null) {
      return;
    }
    if (isVariable(node)) {
      result.add(node);
      return;
    }
    for (SNode child : node.getChildren()) {
      getVariablesInside(child, result, state);
    }
    for (SNode referent : getNodeReferents(node)) {
      if (state != null) {
        referent = state.getRepresentative(referent);
      }
      if (referent != null && isVariable(referent)) {
        result.add(referent);
      }
    }
  }

  @NotNull
  public static List<SNode> getNodeReferents(@NotNull SNode node) {
    final List<SNode> result = new ArrayList<>();
    for (SReference ref : node.getReferences()) {
      result.add(ref.getTargetNode());
    }
    return result;
  }

  public static boolean match(SNode left, SNode right) {
    return match(left, right, null);
  }

  public static boolean match(SNode left, SNode right, /*out*/ Collection<Pair<SNode, SNode>> matchingPairs) {
    if (left == right) return true;
    if (left == null || right == null) return false;

    if (TypesUtil.isVariable(left) || TypesUtil.isVariable(right)) {
      if (matchingPairs != null) {
        matchingPairs.add(new Pair<>(left, right));
      }
      return true;
    }

    MatchingNodesCollector matchingNodesCollector = new MatchingNodesCollector();
    boolean match = MatchingUtil.matchNodes(left, right, matchingNodesCollector, false);
    if (match && matchingPairs != null) {
      matchingPairs.addAll(matchingNodesCollector.myMatchingPairs);
    }

    return match;
  }

  public static boolean matchExpandingJoinAndMeet(SNode left, SNode right, /*out*/ Collection<Pair<SNode, SNode>> matchingPairs) {
    if (match(left, right, matchingPairs)) return true;

    if (LatticeUtil.isJoin(left)) {
      for (SNode arg : LatticeUtil.getJoinArguments(left)) {
        if (match(arg, right, matchingPairs)) return true;
      }
    }
    if (LatticeUtil.isMeet(left)) {
      for (SNode arg : LatticeUtil.getMeetArguments(left)) {
        if (!match(arg, right, matchingPairs)) return false;
      }
      return true;
    }
    if (LatticeUtil.isJoin(right)) {
      for (SNode arg : LatticeUtil.getJoinArguments(right)) {
        if (match(left, arg, matchingPairs)) return true;
      }
    }
    if (LatticeUtil.isMeet(right)) {
      for (SNode arg : LatticeUtil.getMeetArguments(right)) {
        if (!match(left, arg, matchingPairs)) return false;
      }
      return true;
    }

    return false;
  }

  @Deprecated
  public static boolean match(SNode left, SNode right, Equations equations, @Nullable EquationInfo info) {
    THashSet<Pair<SNode, SNode>> matchingPairs = new THashSet<>();
    boolean match = match(left, right, matchingPairs);
    if (match && equations != null) {
      equations.addEquations(matchingPairs, info);
    }
    return match;
  }

  public static SNode cleanupMeet(SNode type) {
    // Dirty hack to avoid meet type to appear inside fully reified type
    Set<SNode> newArgs = new THashSet<>();
    final List<SNode> arguments = LatticeUtil.getMeetArguments(type);
    boolean addTheRest = false;
    for (SNode arg : arguments) {
      if (arg != null && (addTheRest || !SNodeOperations.isInstanceOf(arg, SNodeUtil.concept_VoidType))) {
        newArgs.add(arg);
      } else {
        addTheRest = true;
      }
    }
    if (newArgs.size() != arguments.size()) {
      type = LatticeUtil.meetNodes(newArgs);
    }
    return type;
  }

  private static class MatchingNodesCollector implements IMatchModifier {
    private final Set<Pair<SNode, SNode>> myMatchingPairs = new THashSet<>();

    @Override
    public boolean accept(SNode node1, SNode node2) {
      return TypesUtil.isVariable(node1) || TypesUtil.isVariable(node2);
    }

    @Override
    public boolean acceptList(List<SNode> nodes1, List<SNode> nodes2) {
      return false;
    }

    @Override
    public void performAction(SNode node1, SNode node2) {
      myMatchingPairs.add(new Pair<>(node1, node2));
    }

    @Override
    public void performGroupAction(List<SNode> nodes1, List<SNode> nodes2) {
    }
  }

  public static SNode createRuntimeErrorType() {
    return SModelUtil_new.instantiateConceptDeclaration(SNodeUtil.concept_RuntimeErrorType, null, null, false);
  }

}

