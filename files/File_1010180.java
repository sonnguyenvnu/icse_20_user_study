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
package jetbrains.mps.generator.impl;

import jetbrains.mps.generator.GenerationCanceledException;
import jetbrains.mps.generator.IGeneratorLogger;
import jetbrains.mps.generator.impl.GeneratorUtilEx.ConsequenceDispatch;
import jetbrains.mps.generator.impl.interpreted.TemplateCall;
import jetbrains.mps.generator.impl.query.GeneratorQueryProvider;
import jetbrains.mps.generator.impl.query.InlineSwitchCaseCondition;
import jetbrains.mps.generator.impl.query.QueryKey;
import jetbrains.mps.generator.impl.query.QueryKeyImpl;
import jetbrains.mps.generator.impl.query.QueryProviderBase;
import jetbrains.mps.generator.impl.template.QueryExecutor;
import jetbrains.mps.generator.runtime.GenerationException;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.runtime.TemplateDeclaration;
import jetbrains.mps.generator.runtime.TemplateDeclarationKey;
import jetbrains.mps.generator.runtime.TemplateExecutionEnvironment;
import jetbrains.mps.generator.template.InlineSwitchCaseContext;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Handles rule consequences.
 *
 * @author Artem Tikhomirov
 */
public abstract class RuleConsequenceProcessor {
  /*package*/ RuleConsequenceProcessor() {
  }

  /**
   * Factory method for rule consequences
   */
  public static RuleConsequenceProcessor prepare(@NotNull SNode ruleConsequence) {
    return new ConsequenceHandler().dispatch(ruleConsequence);
  }

  @NotNull
  public abstract List<SNode> processRuleConsequence(@NotNull TemplateContext context)
      throws GenerationFailureException, GenerationCanceledException, DismissTopMappingRuleException, AbandonRuleInputException;

  private static class InlineSwitch extends RuleConsequenceProcessor {
    @NotNull
    private final SNode mySwitchNode;
    private volatile CaseRuntime[] myCases;

    InlineSwitch(@NotNull SNode inlineSwitchNode) {
      mySwitchNode = inlineSwitchNode;
    }

    @NotNull
    @Override
    public List<SNode> processRuleConsequence(@NotNull TemplateContext context)
        throws GenerationFailureException, GenerationCanceledException, DismissTopMappingRuleException, AbandonRuleInputException {
      final QueryExecutor queryExecutor = context.getEnvironment().getQueryExecutor();
      for (CaseRuntime switchCase : getCases(context.getEnvironment())) {
        if (queryExecutor.evaluate(switchCase.condition, new InlineSwitchCaseContext(context, switchCase.nodeReference))) {
          return switchCase.consequence.processRuleConsequence(context);
        }
      }
      SNode defaultConsequence = RuleUtil.getInlineSwitch_defaultConsequence(mySwitchNode);
      if (defaultConsequence == null) {
        GenerationFailureException ex = new GenerationFailureException("no default consequence in switch");
        ex.setTemplateContext(context);
        ex.setTemplateModelLocation(mySwitchNode.getReference());
        throw ex;
      } else {
        RuleConsequenceProcessor rcp = RuleConsequenceProcessor.prepare(defaultConsequence);
        return rcp.processRuleConsequence(context);
      }
    }

    private CaseRuntime[] getCases(GeneratorQueryProvider.Source qps) {
      CaseRuntime[] rv = myCases;
      if (rv == null) {
        ArrayList<CaseRuntime> l = new ArrayList<>();
        for (SNode switchCase : RuleUtil.getInlineSwitch_case(mySwitchNode)) {
          SNode caseConditionNode = RuleUtil.getInlineSwitch_caseCondition(switchCase);
          final InlineSwitchCaseCondition condition;
          if (caseConditionNode != null) {
            QueryKey identity = new QueryKeyImpl(switchCase.getReference(), caseConditionNode.getNodeId());
            condition = qps.getQueryProvider(mySwitchNode.getReference()).getInlineSwitchCaseCondition(identity);
          } else {
            condition = new QueryProviderBase.Missing(switchCase);
          }
          SNode caseConsequence = RuleUtil.getInlineSwitch_caseConsequence(switchCase);
          RuleConsequenceProcessor rcp = RuleConsequenceProcessor.prepare(caseConsequence);
          l.add(new CaseRuntime(condition, rcp, switchCase.getReference()));
        }
        if (myCases == null) {
          myCases = rv = l.toArray(new CaseRuntime[0]);
        } else {
          rv = myCases;
        }
      }
      return rv;
    }

    private static class CaseRuntime {
      public final SNodeReference nodeReference;
      public final RuleConsequenceProcessor consequence;
      public final InlineSwitchCaseCondition condition;
      public CaseRuntime(InlineSwitchCaseCondition condition, RuleConsequenceProcessor rcp, SNodeReference nodeRef) {
        this.condition = condition;
        this.consequence = rcp;
        this.nodeReference = nodeRef;
      }
    }
  }

  private static class AbandonRuleControlFlowConsequence extends RuleConsequenceProcessor {
    private final SNodeReference myLocation;

    public AbandonRuleControlFlowConsequence(SNodeReference location) {
      myLocation = location;
    }

    @NotNull
    @Override
    public List<SNode> processRuleConsequence(@NotNull TemplateContext context) throws AbandonRuleInputException {
      AbandonRuleInputException ex = new AbandonRuleInputException();
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(myLocation);
      throw ex;
    }
  }

  private static class DismissRuleControlFlowConsequence extends RuleConsequenceProcessor {
    private final SNodeReference myLocation;
    private final DismissTopMappingRuleException.MessageType myMessageType;
    private final String myText;

    public DismissRuleControlFlowConsequence(SNodeReference location, DismissTopMappingRuleException.MessageType messageType, String text) {
      myLocation = location;
      myMessageType = messageType;
      myText = text;
    }

    @NotNull
    @Override
    public List<SNode> processRuleConsequence(@NotNull TemplateContext context) throws DismissTopMappingRuleException {
      DismissTopMappingRuleException ex = new DismissTopMappingRuleException(myMessageType, myText);
      ex.setTemplateContext(context);
      ex.setTemplateModelLocation(myLocation);
      throw ex;
    }
  }

  private static class BadConsequence extends RuleConsequenceProcessor {
    private final SNode myConsequence;
    private final String myMessage;

    public BadConsequence(@NotNull SNode consequence, @NotNull String message) {
      myConsequence = consequence;
      myMessage = message;
    }

    @NotNull
    @Override
    public List<SNode> processRuleConsequence(@NotNull TemplateContext context) throws GenerationFailureException {
      IGeneratorLogger log = context.getEnvironment().getLogger();
      log.error(myConsequence.getReference(), myMessage, GeneratorUtil.describeInput(context));
      GenerationFailureException ex = new GenerationFailureException(myMessage);
      ex.setTemplateModelLocation(myConsequence.getReference());
      ex.setTemplateContext(context);
      throw ex;
    }
  }

  private static class TemplateDeclarationReference extends RuleConsequenceProcessor {
    private final TemplateDeclarationKey myTemplateDeclaration;
    private final TemplateCall myTemplateCall;
    private final SNodeReference myCallSite;
    private TemplateDeclaration myTemplate;

    /**
     * If we invoked generated external template from non-generated generator, we use TEE.applyTemplate() or its replacement here,
     * which would load proper TemplateDeclaration (either from model or generated code)?
     * @param ruleConsequence call site for a template identified by {@code templateDeclaration} argument, supplies arguments for template invocation.
     * @param templateDeclaration identifies template to invoke
     */
    public TemplateDeclarationReference(@NotNull SNode ruleConsequence, @NotNull TemplateDeclarationKey templateDeclaration) {
      myTemplateDeclaration = templateDeclaration;
      myTemplateCall = new TemplateCall(ruleConsequence);
      myCallSite = ruleConsequence.getReference();
    }

    @NotNull
    @Override
    public List<SNode> processRuleConsequence(@NotNull TemplateContext context)
        throws GenerationFailureException, GenerationCanceledException, DismissTopMappingRuleException, AbandonRuleInputException {
      // FIXME pretty much the same code is in CallMacro, shall unify.
      TemplateExecutionEnvironment env = context.getEnvironment();
      if (myTemplateCall.argumentsMismatch()) {
        env.getLogger().error(myCallSite, "number of arguments doesn't match myTemplate", GeneratorUtil.describeInput(context));
      }

      if (myTemplate == null) {
        // XXX don't care to ensure single initialization in case I ever get here in parallel threads.
        //     I expect no state in the possibly decorated TD instance, hence don't care to pay the price of an extra instance
        myTemplate = env.findTemplate(myTemplateDeclaration, myCallSite);
      }

      try {
        Collection<SNode> rv = myTemplate.apply(env, myTemplateCall.prepareCallContext(context));
        return new ArrayList<>(rv);
      } catch (GenerationFailureException | GenerationCanceledException | DismissTopMappingRuleException | AbandonRuleInputException ex) {
        throw ex;
      } catch (GenerationException ex) {
        throw new GenerationFailureException(ex);
      }
    }
  }

  private static class ConsequenceHandler implements ConsequenceDispatch {
    private RuleConsequenceProcessor myConsequence;

    @NotNull
    public RuleConsequenceProcessor dispatch(@NotNull SNode ruleConsequence) {
      myConsequence = null;
      GeneratorUtilEx.dispatchRuleConsequence(ruleConsequence, this);
      if (myConsequence == null) {
        assert false; // can't happen provided there's no mistake in GeneratorUtilEx.dispatchRuleConsequence
        myConsequence = new BadConsequence(ruleConsequence, "unknown consequence kind");
      }
      return myConsequence;
    }

    @Override
    public void inlineSwitch(SNode ruleConsequence) {
      myConsequence = new InlineSwitch(ruleConsequence);
    }

    @Override
    public void inlineTemplateWithContext(SNode ruleConsequence) {
      SNode templateContainer = RuleUtil.getInlineTemplateWithContext_contentNode(ruleConsequence);
      if (templateContainer != null) {
        myConsequence = new TemplateContainer(templateContainer);
      } else {
        myConsequence = new BadConsequence(ruleConsequence, "error processing template consequence: no 'template'");
      }
    }

    @Override
    public void inlineTemplate(SNode ruleConsequence) {
      SNode templateNode = RuleUtil.getInlineTemplate_templateNode(ruleConsequence);
      if (templateNode != null) {
        myConsequence = new TemplateContainer(new Pair<>(templateNode, null));
      } else {
        myConsequence = new BadConsequence(ruleConsequence, "no template node");
      }
    }

    @Override
    public void templateDeclarationReference(SNode ruleConsequence) {
      // node<TemplateDeclarationReference> ruleConsequence, concept TemplateDeclarationReference implements ITemplateCall
      SNode templateDeclNode = RuleUtil.getTemplateCall_Template(ruleConsequence);
      if (templateDeclNode == null) {
        myConsequence = new BadConsequence(ruleConsequence, "error processing template consequence: no 'template'");
      } else {
        myConsequence = new TemplateDeclarationReference(ruleConsequence, TemplateIdentity.fromSourceNode(templateDeclNode));
      }
    }

    @Override
    public void weaveEach(SNode ruleConsequence) {
      myConsequence = new BadConsequence(ruleConsequence, "weaveEach is not expected here");
    }

    @Override
    public void abandonInput(SNode ruleConsequence) {
      myConsequence = new AbandonRuleControlFlowConsequence(ruleConsequence.getReference());
    }

    @Override
    public void dismissTopRule(SNode ruleConsequence) {
      SNode message = RuleUtil.getDismissTopRule_message(ruleConsequence);
      DismissTopMappingRuleException.MessageType messageType = GeneratorUtilEx.getGeneratorMessage_kind(message);
      String text = GeneratorUtilEx.getGeneratorMessage_text(message);
      myConsequence = new DismissRuleControlFlowConsequence(ruleConsequence.getReference(), messageType, text);
    }

    @Override
    public void unknown(SNode ruleConsequence) {
      myConsequence = new BadConsequence(ruleConsequence, "unsupported rule consequence");
    }
  }
}
