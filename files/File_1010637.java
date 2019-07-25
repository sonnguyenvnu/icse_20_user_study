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
package jetbrains.mps.lang.typesystem.runtime;

import jetbrains.mps.errors.IRuleConflictWarningProducer;
import jetbrains.mps.logging.Logger;
import org.apache.log4j.LogManager;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.typesystem.inference.SubtypingManager;
import jetbrains.mps.typesystem.inference.TypeChecker;
import jetbrains.mps.util.CollectionUtil;

import java.util.*;

public class OverloadedOperationsManager {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(OverloadedOperationsManager.class));

  private RuleSet<IOverloadedOpsTypesProvider> myOperationsToTypeProviders =
      new RuleSet<>();

  private TypeChecker myTypeChecker;

  public OverloadedOperationsManager(TypeChecker typeChecker) {
    myTypeChecker = typeChecker;
  }

  public void addOverloadedOperationsTypeProvider(IOverloadedOpsTypesProvider provider) {
    Set<IOverloadedOpsTypesProvider> providers = CollectionUtil.set(provider);
    addOverloadedOperationsTypeProviders(providers);
  }

  public void addOverloadedOperationsTypeProviders(Set<IOverloadedOpsTypesProvider> providers) {
    myOperationsToTypeProviders.addRuleSetItem(providers);
  }

  public SNode getOperationType(SNode operation, SNode leftOperandType, SNode rightOperandType) {
    return getOperationType(operation, leftOperandType, rightOperandType, IRuleConflictWarningProducer.NULL);
  }

  public SNode getOperationType(SNode operation, SNode leftOperandType, SNode rightOperandType, IRuleConflictWarningProducer warningProducer) {
    Set<IOverloadedOpsTypesProvider> operationsTypesProviderSet = myOperationsToTypeProviders.getRules(operation);
    if (operationsTypesProviderSet.isEmpty()) {
      return null;
    }
    SubtypingManager subtypingManager = myTypeChecker.getSubtypingManager();
    List<IOverloadedOpsTypesProvider> filteredProviders = new ArrayList<>();
    for (IOverloadedOpsTypesProvider provider : operationsTypesProviderSet) {
      //first applicable method is from base class, second is custom
      if (provider.isApplicable(subtypingManager, leftOperandType, rightOperandType) &&
        provider.isApplicable(subtypingManager, operation, leftOperandType, rightOperandType)) {
        filteredProviders.add(provider);
      }
    }
    final boolean[] severalRules = new boolean[]{false};
    final IOverloadedOpsTypesProvider[] matchedProviders = new IOverloadedOpsTypesProvider[2];
    Collections.sort(filteredProviders, (o1, o2) -> {
      int i = o1.compareTo(o2);
      if (i == 0) {
        severalRules[0] = true;
        matchedProviders[0] = o1;
        matchedProviders[1] = o2;
      }
      return i;
    });
    if (severalRules[0]) {
      matchedProviders[0].reportConflict(warningProducer);
      matchedProviders[1].reportConflict(warningProducer);
    }
    for (IOverloadedOpsTypesProvider provider : filteredProviders) {
      SNode result = provider.getOperationType(operation, leftOperandType, rightOperandType);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  public void clear() {
    myOperationsToTypeProviders = new RuleSet<>();
  }
}
