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
package jetbrains.mps.newTypesystem.relations;

import jetbrains.mps.newTypesystem.SubtypingUtil;
import jetbrains.mps.newTypesystem.TypesUtil;
import jetbrains.mps.newTypesystem.state.State;
import jetbrains.mps.newTypesystem.state.blocks.RelationBlock;
import jetbrains.mps.newTypesystem.state.blocks.RelationKind;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.typesystem.inference.EquationInfo;
import jetbrains.mps.typesystemEngine.util.LatticeUtil;

import java.util.*;

public class SubTypingRelation extends AbstractRelation {

  @Override
  public boolean accept(RelationKind kind) {
    return !kind.isComparable();
  }

  @Override
  public boolean solve(SNode node, Set<SNode> leftTypes, Set<SNode> rightTypes, State state, Map<SNode, RelationBlock> typesToBlocks) {
    SNode result = null;
    EquationInfo info = null;
    if (!leftTypes.isEmpty()) {
      final LinkedList<SNode> leftTypesList = new LinkedList<>();
      for (SNode lt: leftTypes) {
        if (LatticeUtil.isMeet(lt)) {
          lt = TypesUtil.cleanupMeet(lt);
        }
        leftTypesList.add(lt);
      }
      result = SubtypingUtil.createLeastCommonSupertype(leftTypesList, state.getTypeCheckingContext());
      if (LatticeUtil.isMeet(result)) {
        result = TypesUtil.cleanupMeet(result);
      }
      RelationBlock block = typesToBlocks.get(result);
      info = (block != null) ? block.getEquationInfo() : typesToBlocks.get(leftTypes.iterator().next()).getEquationInfo();
    } else if (!rightTypes.isEmpty()) {
      result = createMeet(rightTypes);
      RelationBlock block = typesToBlocks.get(result);
      info = (block != null) ? block.getEquationInfo() : typesToBlocks.get(rightTypes.iterator().next()).getEquationInfo();
    }
    if (TypesUtil.isVariable(result) && TypesUtil.isVariable(node) && result.getName().equals(node.getName())) return false;
    return result != null && state.addEquation(node, result, info);
  }

  private SNode createMeet(Set<SNode> rightTypes) {
    List<SNode> types = new LinkedList<>(rightTypes);
    if (types.size() > 1) {
      types = SubtypingUtil.eliminateSuperTypes(types);
    }
    return LatticeUtil.meetNodes(new LinkedHashSet<>(types));
  }
}
