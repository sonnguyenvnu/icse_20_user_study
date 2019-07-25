/*
 * Copyright 2003-2012 JetBrains s.r.o.
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
package jetbrains.mps.newTypesystem.state;

import jetbrains.mps.lang.typesystem.runtime.HUtil;
import jetbrains.mps.lang.typesystem.runtime.ICheckingRule_Runtime;
import jetbrains.mps.lang.typesystem.runtime.IsApplicableStatus;
import jetbrains.mps.smodel.SModelUtil_new;
import jetbrains.mps.smodel.SNodeUtil;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.typesystem.inference.EquationInfo;
import jetbrains.mps.typesystem.inference.InequalitySystem;
import jetbrains.mps.typesystem.inference.TypeCheckingContext;

/**
 * User: fyodor
 * Date: 12/3/12
 */
public class HoleState extends State {

  private SNode myHole;
  private InequalitySystem myInequalitySystem = null;

  public HoleState(TypeCheckingContext tcc) {
    super(tcc);
  }

  public void initHole(SNode hole) {
    SNode holeVar = typeOf(hole, null);
    SNode holeType = SModelUtil_new.instantiateConceptDeclaration(SNodeUtil.concept_RuntimeHoleType, null, null, false);
    getNodeMaps().addNodeToType(hole, holeVar, null);
    getEquations().addEquation(holeVar, holeType, null);
    myHole = hole;
    myInequalitySystem = new InequalitySystem(holeType, this);
  }

  public InequalitySystem getInequalitySystem() {
    return myInequalitySystem;
  }

  public void disposeHole() {
    myInequalitySystem = null;
    myHole = null;
  }

  @Override
  public void clear(boolean clearDiff) {
    super.clear(clearDiff);
    if (myInequalitySystem != null) {
      //reset hole
      SNode hole = myHole;
      disposeHole();
      initHole(hole);
    }
  }

  @Override
  public void applyRuleToNode(SNode node, ICheckingRule_Runtime rule, IsApplicableStatus status) {
    if (myHole != null && myHole == node) {
      return;
    }
    super.applyRuleToNode(node, rule, status);
  }

  @Override
  public boolean addEquation(SNode left, SNode right, EquationInfo info) {
    if (myInequalitySystem != null) {
      if (HUtil.isRuntimeHoleType(left)) {
        myInequalitySystem.addEquation(left);
        return true;
      }
      if (HUtil.isRuntimeHoleType(right)) {
        myInequalitySystem.addEquation(right);
        return true;
      }
    }
    return super.addEquation(left, right, info);
  }

  @Override
  public void addInequality(SNode subType, SNode superType, boolean isWeak, boolean check, EquationInfo info, boolean lessThan) {
    if (myInequalitySystem != null) {
      if (HUtil.isRuntimeHoleType(subType)) {
        myInequalitySystem.addSupertype(superType, isWeak);
        return;
      }
      if (HUtil.isRuntimeHoleType(superType)) {
        myInequalitySystem.addSubtype(subType, isWeak);
        return;
      }
    }
    super.addInequality(subType, superType, isWeak, check, info, lessThan);
  }

  @Override
  public void addComparable(SNode left, SNode right, boolean isWeak, boolean inference, EquationInfo info) {
    if (myInequalitySystem != null) {
      if (HUtil.isRuntimeHoleType(right)) {
        myInequalitySystem.addComparable(left, isWeak);
        return;
      }
      if (HUtil.isRuntimeHoleType(left)) {
        myInequalitySystem.addComparable(right, isWeak);
        return;
      }
    }
    super.addComparable(left, right, isWeak, inference, info);
  }
}
