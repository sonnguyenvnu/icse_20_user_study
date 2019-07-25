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
package jetbrains.mps.generator.impl.template;

import jetbrains.mps.generator.impl.GenerationFailureException;
import jetbrains.mps.generator.impl.query.CallArgumentQuery;
import jetbrains.mps.generator.impl.query.IfMacroCondition;
import jetbrains.mps.generator.impl.query.InlineSwitchCaseCondition;
import jetbrains.mps.generator.impl.query.InsertMacroQuery;
import jetbrains.mps.generator.impl.query.MapNodeQuery;
import jetbrains.mps.generator.impl.query.MapPostProcessor;
import jetbrains.mps.generator.impl.query.PropertyValueQuery;
import jetbrains.mps.generator.impl.query.ReferenceTargetQuery;
import jetbrains.mps.generator.impl.query.SourceNodeQuery;
import jetbrains.mps.generator.impl.query.SourceNodesQuery;
import jetbrains.mps.generator.impl.query.VariableValueQuery;
import jetbrains.mps.generator.runtime.GenerationException;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.runtime.TemplateCreateRootRule;
import jetbrains.mps.generator.runtime.TemplateExecutionEnvironment;
import jetbrains.mps.generator.runtime.TemplateMappingScript;
import jetbrains.mps.generator.runtime.TemplateReductionRule;
import jetbrains.mps.generator.runtime.TemplateRootMappingRule;
import jetbrains.mps.generator.runtime.TemplateRuleWithCondition;
import jetbrains.mps.generator.runtime.TemplateWeavingRule;
import jetbrains.mps.generator.template.IfMacroContext;
import jetbrains.mps.generator.template.InlineSwitchCaseContext;
import jetbrains.mps.generator.template.InsertMacroContext;
import jetbrains.mps.generator.template.MapSrcMacroContext;
import jetbrains.mps.generator.template.MapSrcMacroPostProcContext;
import jetbrains.mps.generator.template.PropertyMacroContext;
import jetbrains.mps.generator.template.QueryExecutionContext;
import jetbrains.mps.generator.template.ReferenceMacroContext;
import jetbrains.mps.generator.template.SourceSubstituteMacroNodeContext;
import jetbrains.mps.generator.template.SourceSubstituteMacroNodesContext;
import jetbrains.mps.generator.template.TemplateArgumentContext;
import jetbrains.mps.generator.template.TemplateVarContext;
import jetbrains.mps.util.performance.IPerformanceTracer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.Collection;

/**
 * Evgeny Gryaznov, May 13, 2010
 */
public class QueryExecutionContextWithTracing implements QueryExecutionContext {

  private final QueryExecutionContext wrapped;
  private final IPerformanceTracer tracer;

  /**
   * @param wrapped never null
   * @param tracer never null
   */
  public QueryExecutionContextWithTracing(QueryExecutionContext wrapped, IPerformanceTracer tracer) {
    this.wrapped = wrapped;
    this.tracer = tracer;
  }

  private static String taskName(String name, SNodeReference ruleNode) {
    if (ruleNode == null) {
      return name;
    }
    return name + ':' + ruleNode.getModelReference().getName().getLongName();
  }

  @Override
  public boolean evaluate(@NotNull InlineSwitchCaseCondition condition, @NotNull InlineSwitchCaseContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("check condition(with context)", context.getTemplateReference()));
      return wrapped.evaluate(condition, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public boolean evaluate(@NotNull IfMacroCondition condition, @NotNull IfMacroContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("check if condition", context.getTemplateReference()));
      return wrapped.evaluate(condition, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull MapNodeQuery query, @NotNull MapSrcMacroContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("map-src node macro", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public void execute(@NotNull MapPostProcessor codeBlock, @NotNull MapSrcMacroPostProcContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("map-src postproc", context.getTemplateReference()));
      wrapped.execute(codeBlock, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull PropertyValueQuery query, @NotNull PropertyMacroContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName(String.format("property macro(name: %s)", query.getProperty()), null));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull SourceNodeQuery query, @NotNull SourceSubstituteMacroNodeContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("evaluate source node", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @NotNull
  @Override
  public Collection<SNode> evaluate(@NotNull SourceNodesQuery query, @NotNull SourceSubstituteMacroNodesContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("evaluate source nodes", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull InsertMacroQuery query, @NotNull InsertMacroContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("insert node query", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull ReferenceTargetQuery query, @NotNull ReferenceMacroContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("referent target", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull CallArgumentQuery query, @NotNull TemplateArgumentContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("evaluate template argument query", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull VariableValueQuery query, @NotNull TemplateVarContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("evaluate variable value query", context.getTemplateReference()));
      return wrapped.evaluate(query, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateReductionRule rule, TemplateContext context) throws GenerationException {
    try {
      String taskName = taskName(String.format("trying to apply rule(%s)", rule.getApplicableConcept()), rule.getRuleNode());
      tracer.push(taskName);
      return wrapped.applyRule(rule, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public boolean isApplicable(@NotNull TemplateRuleWithCondition rule, @NotNull TemplateContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("check condition", rule.getRuleNode()));
      return wrapped.isApplicable(rule, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateRootMappingRule rule, TemplateContext context) throws GenerationException {
    try {
      tracer.push(taskName(String.format("root mapping rule(%s)", rule.getApplicableConcept()), rule.getRuleNode()));
      return wrapped.applyRule(rule, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateCreateRootRule rule, TemplateExecutionEnvironment environment) throws GenerationException {
    try {
      tracer.push(taskName("create root rule", rule.getRuleNode()));
      return wrapped.applyRule(rule, environment);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public boolean applyRule(TemplateWeavingRule rule, TemplateContext context, SNode outputContextNode) throws GenerationException {
    try {
      tracer.push(taskName("weave rule", rule.getRuleNode()));
      return wrapped.applyRule(rule, context, outputContextNode);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public SNode getContextNode(TemplateWeavingRule rule, TemplateContext context) throws GenerationFailureException {
    try {
      tracer.push(taskName("context for weaving", rule.getRuleNode()));
      return wrapped.getContextNode(rule, context);
    } finally {
      tracer.pop();
    }
  }

  @Override
  public void executeScript(TemplateMappingScript mappingScript, SModel model) throws GenerationFailureException {
    try {
      tracer.push(taskName(String.format("mapping script (%s)", mappingScript.getLongName()), mappingScript.getScriptNode()));
      wrapped.executeScript(mappingScript, model);
    } finally {
      tracer.pop();
    }
  }
}
