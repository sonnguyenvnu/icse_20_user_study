/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.generator.impl.interpreted;

import jetbrains.mps.generator.impl.GeneratorUtil;
import jetbrains.mps.generator.impl.RuleConsequenceProcessor;
import jetbrains.mps.generator.impl.RuleUtil;
import jetbrains.mps.generator.impl.TemplateProcessingFailureException;
import jetbrains.mps.generator.impl.query.QueryKey;
import jetbrains.mps.generator.impl.query.QueryKeyImpl;
import jetbrains.mps.generator.impl.query.QueryProviderBase;
import jetbrains.mps.generator.impl.query.ReductionRuleCondition;
import jetbrains.mps.generator.runtime.GenerationException;
import jetbrains.mps.generator.runtime.ReductionRuleBase;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.runtime.TemplateReductionRule;
import jetbrains.mps.generator.runtime.TemplateRuleWithCondition;
import jetbrains.mps.generator.template.ReductionRuleQueryContext;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.smodel.adapter.MetaAdapterByDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;

public class TemplateReductionRuleInterpreted extends ReductionRuleBase implements TemplateReductionRule, TemplateRuleWithCondition {

  private final SNode myRuleNode;
  private final String myMappingName;
  private final RuleConsequenceProcessor myRuleConsequence;
  private ReductionRuleCondition myCondition;

  public TemplateReductionRuleInterpreted(SNode ruleNode) {
    super(new SNodePointer(ruleNode), MetaAdapterByDeclaration.getConcept(RuleUtil.getBaseRuleApplicableConcept(ruleNode)), RuleUtil.getBaseRuleApplyToConceptInheritors(ruleNode));
    myRuleNode = ruleNode;
    myMappingName = RuleUtil.getBaseRuleLabel(ruleNode);
    final SNode rc = RuleUtil.getReductionRuleConsequence(ruleNode);
    myRuleConsequence = rc == null ? null : RuleConsequenceProcessor.prepare(rc);
  }

  @Override
  public boolean isApplicable(@NotNull TemplateContext context) throws GenerationException {
    if (myCondition == null) {
      SNode condition = RuleUtil.getBaseRuleCondition(myRuleNode);
      if (condition != null) {
        QueryKey identity = new QueryKeyImpl(getRuleNode(), condition.getNodeId());
        myCondition = context.getEnvironment().getQueryProvider(getRuleNode()).getReductionRuleCondition(identity);
      } else {
        myCondition = new QueryProviderBase.Defaults();
      }
    }
    return myCondition.check(new ReductionRuleQueryContext(context, getRuleNode()));
  }

  @Override
  @NotNull
  public Collection<SNode> apply(@NotNull TemplateContext context) throws GenerationException {
    if (myRuleConsequence == null) {
      throw new TemplateProcessingFailureException(myRuleNode, "no rule consequence", GeneratorUtil.describeInput(context));
    }
    return myRuleConsequence.processRuleConsequence(context.subContext(myMappingName));
  }
}
