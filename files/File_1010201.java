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
package jetbrains.mps.generator.template;

import jetbrains.mps.generator.impl.GenerationFailureException;
import jetbrains.mps.generator.impl.TemplateQueryException;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collection;

/**
 * Default implementation that executes queries without any further activity.
 *
 * XXX Note, evaluate methods account for any trouble in user code, and wrap them with {@link TemplateQueryException}.
 *
 * See {@link jetbrains.mps.generator.impl.template.QueryExecutor} for
 * considerations whether we shall keep QE/QEC indirection, or get another GQP provider that would wrap queries with
 * try/catch and unexpected error handling (wrapping could be conditional). I lean towards a distinct provider as it gives
 * more flexibility (can mix different wrappers) and fine-grained control for wrappers like performance tracer.
 * Evgeny Gryaznov, Feb 10, 2010
 */
public class DefaultQueryExecutionContext implements QueryExecutionContext {
  private final ITemplateGenerator myGenerator;

  public DefaultQueryExecutionContext(@NotNull ITemplateGenerator generator) {
    myGenerator = generator;
  }

  @Override
  public boolean evaluate(@NotNull InlineSwitchCaseCondition condition, @NotNull InlineSwitchCaseContext context) throws GenerationFailureException {
    try {
      return condition.check(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("condition of inline switch failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Override
  public boolean evaluate(@NotNull IfMacroCondition condition, @NotNull IfMacroContext context) throws GenerationFailureException {
    try {
    return condition.check(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("IF macro condition failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull MapNodeQuery query, @NotNull MapSrcMacroContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("mapping function failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Override
  public void execute(@NotNull MapPostProcessor codeBlock, @NotNull MapSrcMacroPostProcContext context) throws GenerationFailureException {
    try {
      codeBlock.invoke(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("post-processing query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull PropertyValueQuery query, @NotNull PropertyMacroContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("failed to evaluate property value", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull SourceNodeQuery query, @NotNull SourceSubstituteMacroNodeContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("source node query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull CallArgumentQuery query, @NotNull TemplateArgumentContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("Query for template call argument failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull VariableValueQuery query, @NotNull TemplateVarContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("VAR macro query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @NotNull
  @Override
  public Collection<SNode> evaluate(@NotNull SourceNodesQuery query, @NotNull SourceSubstituteMacroNodesContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("source nodes query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public SNode evaluate(@NotNull InsertMacroQuery query, @NotNull InsertMacroContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("INSERT macro query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Nullable
  @Override
  public Object evaluate(@NotNull ReferenceTargetQuery query, @NotNull ReferenceMacroContext context) throws GenerationFailureException {
    try {
      return query.evaluate(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("reference macro target query failed", t);
      ex.setQueryContext(context);
      throw ex;
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateReductionRule rule, TemplateContext context) throws GenerationException {
    try {
      return rule.apply(context);
    } catch (GenerationException ex) {
      throw ex;
    } catch (Throwable t) {
      GenerationFailureException ex = new GenerationFailureException("error applying reduction rule", t);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public boolean isApplicable(@NotNull TemplateRuleWithCondition rule, @NotNull TemplateContext context) throws GenerationFailureException {
    try {
      return rule.isApplicable(context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("error executing rule condition", t);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateRootMappingRule rule, TemplateContext context) throws GenerationException {
    try {
      return rule.apply(context);
    } catch (GenerationException ex) {
      throw ex;
    } catch (Throwable t) {
      GenerationFailureException ex = new GenerationFailureException("unexpected exception when applying root rule", t);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public Collection<SNode> applyRule(TemplateCreateRootRule rule, TemplateExecutionEnvironment environment) throws GenerationException {
    try {
      return rule.apply(environment);
    } catch (GenerationException ex) {
      throw ex;
    } catch (Throwable t) {
      GenerationFailureException ex = new GenerationFailureException("unexpected exception when applying create root rule", t);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public boolean applyRule(TemplateWeavingRule rule, TemplateContext context, SNode outputContextNode) throws GenerationException {
    try {
      return rule.apply(context.getEnvironment(), context, outputContextNode);
    } catch (GenerationException ex) {
      throw ex;
    } catch (Throwable t) {
      GenerationFailureException ex = new GenerationFailureException("unexpected exception when applying weaving rule", t);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public SNode getContextNode(TemplateWeavingRule rule, TemplateContext context) throws GenerationFailureException {
    try {
      return rule.getContextNode(context.getEnvironment(), context);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException("cannot evaluate rule context query", t);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(rule.getRuleNode());
      throw ex;
    }
  }

  @Override
  public void executeScript(TemplateMappingScript mappingScript, SModel model) throws GenerationFailureException {
    try {
      mappingScript.apply(model, myGenerator);
    } catch (GenerationFailureException ex) {
      throw ex;
    } catch (Throwable t) {
      TemplateQueryException ex = new TemplateQueryException(String.format("error executing script %s", mappingScript.getLongName()), t);
      ex.setTemplateModelLocation(mappingScript.getScriptNode());
      throw ex;
    }
  }
}
