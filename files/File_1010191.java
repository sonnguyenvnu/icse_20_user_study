/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
import jetbrains.mps.generator.GenerationTrace;
import jetbrains.mps.generator.GenerationTracerUtil;
import jetbrains.mps.generator.IGeneratorLogger;
import jetbrains.mps.generator.impl.interpreted.TemplateCall;
import jetbrains.mps.generator.impl.query.GeneratorQueryProvider;
import jetbrains.mps.generator.impl.reference.PostponedReference;
import jetbrains.mps.generator.impl.reference.ReferenceInfo_Macro;
import jetbrains.mps.generator.impl.reference.ReferenceInfo_Template;
import jetbrains.mps.generator.runtime.GenerationException;
import jetbrains.mps.generator.runtime.NodePostProcessor;
import jetbrains.mps.generator.runtime.NodeWeaveFacility;
import jetbrains.mps.generator.runtime.NodeWeaveFacility.WeaveContext;
import jetbrains.mps.generator.runtime.ReferenceResolver;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.runtime.TemplateDeclaration;
import jetbrains.mps.generator.runtime.TemplateDeclaration2;
import jetbrains.mps.generator.runtime.TemplateDeclarationKey;
import jetbrains.mps.generator.runtime.TemplateExecutionEnvironment;
import jetbrains.mps.generator.runtime.TemplateModel;
import jetbrains.mps.generator.runtime.TemplateModel2;
import jetbrains.mps.generator.runtime.TemplateReductionRule;
import jetbrains.mps.generator.runtime.TemplateRuleWithCondition;
import jetbrains.mps.generator.runtime.TemplateSwitchMapping;
import jetbrains.mps.generator.template.ITemplateProcessor;
import jetbrains.mps.generator.template.QueryExecutionContext;
import jetbrains.mps.generator.trace.RuleTrace;
import jetbrains.mps.generator.trace.RuleTrace2;
import jetbrains.mps.generator.trace.TraceFacility;
import jetbrains.mps.smodel.CopyUtil;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.textgen.trace.TracingUtil;
import jetbrains.mps.util.containers.ConcurrentHashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Evgeny Gryaznov, 11/10/10
 */
public class TemplateExecutionEnvironmentImpl implements TemplateExecutionEnvironment {
  private final TemplateGenerator generator;
  private final QueryExecutionContext myExecutionContext;
  private final ITemplateProcessor myTemplateProcessor;
  private final ReductionTrack myReductionTrack;
  /**
   * Input nodes coming from a model other than input model (or no model at all), e.g. if
   * input node query follows a reference from an input model to some outer model.
   * We track these nodes, including children, to facilitate reference resolution (i.e. if there's
   * a reference in an input model pointing somewhere to subtree of a foreign node, we redirect
   * that reference to the copied counterpart). Generally, this approach might not be everyone's
   * desire, but it's the way it was so far.
   * This collection used to be shared between generation threads, now it's per-thread for few reasons:
   *   first, it's a performance hog the moment there are a lot of 'external' inputs;
   *   next, it's wrong to assume any specific ordering of node processing, and we could rely only on nodes
   *   already processed in this thread only.
   */
  private final Set<SNode> myAdditionalInputNodes = new HashSet<>();


  // although it's possible to instantiate ReductionTrack here (we've got generator in TP),
  // I plan to separate TEE and RT so that they are independent
  public TemplateExecutionEnvironmentImpl(@NotNull TemplateProcessor templateProcessor, @NotNull QueryExecutionContext executionContext, @NotNull ReductionTrack reductionTrack) {
    this.generator = templateProcessor.getGenerator();
    myExecutionContext = executionContext;
    myTemplateProcessor = templateProcessor;
    myReductionTrack = reductionTrack;
  }

  @Override
  public SModel getOutputModel() {
    return generator.getOutputModel();
  }

  @NotNull
  @Override
  public SNode createOutputNode(@NotNull SConcept concept) {
    return generator.getOutputModel().createNode(concept);
  }

  @NotNull
  @Override
  public TemplateGenerator getGenerator() {
    return generator;
  }


  @NotNull
  @Override
  public GenerationTrace getTrace() {
    return generator.getTrace();
  }

  @Override
  public IGeneratorLogger getLogger() {
    return generator.getLogger();
  }

  @NotNull
  @Override
  public GeneratorQueryProvider getQueryProvider(@NotNull SNodeReference ruleNode) {
    return generator.getQueryProvider(ruleNode);
  }

  @NotNull
  @Override
  public QueryExecutionContext getQueryExecutor() {
    return myExecutionContext;
  }

  @NotNull
  @Override
  public ITemplateProcessor getTemplateProcessor() {
    return myTemplateProcessor;
  }


  @Override
  @NotNull
  public List<SNode> copyNodes(@NotNull Iterable<SNode> inputNodes, @NotNull SNodeReference templateNode, @NotNull String templateId,
      @NotNull TemplateContext ctx) throws GenerationCanceledException, GenerationFailureException {
    trackIfForeign(inputNodes);
    List<SNode> outputNodes = generator.copyNodes(inputNodes, ctx, templateId, this);
    if (!outputNodes.isEmpty()) {
      generator.checkIsExpectedLanguage(outputNodes, templateNode, ctx);
    }
    return outputNodes;
  }

  // to use from FullCopyFacility
  /*package*/ boolean isForeignNode(SNode node) {
    // no synchronized access any more as it's accessed from a single thread only
    return myAdditionalInputNodes.contains(node);
  }

  private void trackIfForeign(Iterable<SNode> inputNodes) {
    for (SNode inputNode : inputNodes) {
      SModel model = inputNode.getModel();
      if (model != generator.getInputModel() || model == null) {
        if (!myAdditionalInputNodes.contains(inputNode)) {
          for (SNode n : SNodeUtil.getDescendants(inputNode, null, true)) {
            myAdditionalInputNodes.add(n);
          }
        }
      }
    }
  }


  @Override
  public SNode insertNode(SNode child, SNodeReference templateNode, TemplateContext templateContext) {
    generator.checkIsExpectedLanguage(Collections.singletonList(child), templateNode, templateContext);
    return new ChildAdopter(generator).adopt(child, templateContext);
  }

  @Nullable
  @Override
  public Collection<SNode> trySwitch(SNodeReference _switch, TemplateContext context) throws GenerationException {
    FastRuleFinder rf = generator.getRuleManager().getSwitchRules(_switch);
    Collection<SNode> outputNodes = tryToReduce(rf, context);
    if (outputNodes != null) {
      // XXX it seems odd we do not do TracingUtil.fillOriginalNode(context.getInput(), outputNodes.get(0), false)
      // to record actual origin for the switch outcome. This ruins scenario like
      // Rule X -> $SWITCH$ for x.refY
      // where X is recorded as origin for whatever outcome SWITCH reports.
      // However, if we do fill proper original node here, this ruins existing scenario, where bl.collections got rules
      // for AbstractCreator, which switch by type of the creator, so that generated new LingHashSet() points not to 'new linkhashset<>'
      // creator node, but to value of ((TypeDerivable) parent).deriveType, which might be field declaration
      if (outputNodes.size() == 1 && context.getInputName() != null) {
        SNode reducedNode = outputNodes.iterator().next();
        // register copied node
        generator.registerMappingLabel(context.getInput(), context.getInputName(), reducedNode);
      }
      generator.recordTransformInputTrace(context.getInput(), outputNodes);
      return outputNodes;
    }

    // try the default case
    TemplateSwitchMapping current = generator.getSwitch(_switch);
    if (current != null) {
      outputNodes = current.applyDefault(this, _switch, context.getInputName(), context); // FIXME TSM.applyDefault without explicit mappingLabel
      generator.recordTransformInputTrace(context.getInput(), outputNodes);
      return outputNodes;
    }
    // no switch-case found for the inputNode - continue with templateNode under the $switch$
    return null;
  }

  // XXX Now that I've checked old API works with both new interpreted and old generated templates,
  //     shall check new API works with old generated templates. Then, change generated code to match new API
  //     and check new API works ok.
  //   ? how to check old API works fine with new generated code? Try to use new API from within applyTemplates()?
  //     What if I regenerate first, to see if old api works with newly generated code?
  // It's more important to check how new API works with old templates as it's a case we likely to face.
  //     Old code either invokes same old code, or new one, interpreted or generated. For MPS-distributed generated
  //     templates, there'd be no assert in loadTemplates and proper TemplateDeclaration2.getParameterNames to
  //     handle arguments, i.e. the path already verified for interpreted.
  // For a new invocation API to tell whether template is new or old, we can either use newly introduced method in TM to
  //     access TD (missing method means loadTemplates have to be used, or a 'version' field in TD along with
  //     relaxed assert (no-op implementation) and flagged TC to ignore

  @Override
  public Collection<SNode> applyTemplate(@NotNull SNodeReference templateDeclaration, @NotNull SNodeReference templateNode, @NotNull TemplateContext context, Object... arguments) throws GenerationException {
    // (1) applyTemplate() invoked from interpreter (can change this by not using the method from interpreter)
    //     (a) invoked template declaration is new and expects arguments in TC (e.g. interpreted TD)
    //     (b) invoked template declaration is old and expects args at construction time, changes TC itself
    //         can assume TC comes with proper values and flag TC to ignore subsequent subContext(Map).
    //         caller can supply arguments[] of a proper length with null values.
    // (2) applyTemplate() invoked from generated code or code that doesn't pass values through TC
    //     (a) invoked template declaration is new and expects arguments in TC (e.g. interpreted TD
    //         CAN I DO ANYTHING, provided I have no idea about parameter names? Can add a method to tell names for migration purposes
    //           i.e. need to tell old TD from a new one. An interface with getParameterNames():String[] as indicator?
    //           mangle TC with names and values
    //     (b) invoked template declaration is old (means cross-compiled-generator template call) and expects args at construction time, changes TC itself
    TemplateDeclaration templateDeclarationInstance = loadTemplateDeclaration(templateDeclaration, templateNode, context, arguments);
    if (templateDeclarationInstance == null) {
      return Collections.emptyList();
    }
    if (templateDeclarationInstance instanceof TemplateDeclaration2) {
      TemplateCall callSite = new TemplateCall(((TemplateDeclaration2) templateDeclarationInstance).getParameterNames(), arguments);
      if (callSite.argumentsMismatch()) {
        getLogger().error(templateDeclaration, "number of arguments doesn't match template", GeneratorUtil.describeInput(context));
        // fall-though, as it's the way it was in TemplateDeclarationInterpreted
      }
      // context may keep a mapping label (e.g. from outer $INCLUDE$ label template)
      context = callSite.prepareCallContext(context);
    }
    return templateDeclarationInstance.apply(this, context);
  }

  @NotNull
  @Override
  public TemplateDeclaration findTemplate(@NotNull TemplateDeclarationKey templateDeclaration, @NotNull SNodeReference callSite) {
    class BadTemplateDeclaration implements TemplateDeclaration {
      private final String myMessage;
      private boolean myErrorReported = false;

      /*package*/ BadTemplateDeclaration(String message) {
        myMessage = message;
      }


      @Override
      public SNodeReference getTemplateNode() {
        return templateDeclaration.getSourceNode();
      }

      @Override
      public Collection<SNode> apply(@NotNull TemplateExecutionEnvironment environment, @NotNull TemplateContext context) throws GenerationException {
        reportError(context);
        return Collections.emptyList();
      }

      @Override
      public Collection<SNode> weave(@NotNull WeaveContext context, @NotNull NodeWeaveFacility weaveFacility) throws GenerationException {
        reportError(weaveFacility.getTemplateContext());
        return Collections.emptyList();
      }

      private void reportError(TemplateContext context) {
        if (myErrorReported) {
          return;
        }
        myErrorReported = true;
        getLogger().error(callSite, myMessage,
                          GeneratorUtil.describeIfExists(context.getInput(), "input"),
                          GeneratorUtil.describe(callSite, "call site"),
                          GeneratorUtil.describe(getTemplateNode(), "template declaration"));
      }
    }
    TemplateModel templateModel = generator.getGenerationPlan().getTemplateModel(templateDeclaration.getSourceModel());
    if (templateModel == null) {
      String m = "Template model %s not found. Cannot apply template declaration, try to check & regenerate affected generators";
      return new BadTemplateDeclaration(String.format(m, templateDeclaration.getSourceModel().getName()));
    }
    final TemplateDeclaration templateInstance;
    final boolean legacyTD;
    if (templateModel instanceof TemplateModel2) {
      templateInstance = ((TemplateModel2) templateModel).loadTemplate(templateDeclaration);
      legacyTD = false;
    } else {
      // XXX here I expect no template would have more than 10 arguments. In legacy generated templates, there's code
      //     that access arguments by index, therefore I can't use empty array here
      Object[] fakeEmptyArgs = new Object[10];
      templateInstance = templateModel.loadTemplate(templateDeclaration.getSourceNode(), fakeEmptyArgs);
      legacyTD = true;
    }
    if (templateInstance == null) {
      String m = "Could not find '%s'. Cannot apply template declaration, try to check & regenerate affected generators";
      return new BadTemplateDeclaration(String.format(m, templateDeclaration.describe()));
    }

    if (!legacyTD) {
      // XXX may want to wrap with a tracing decorator
      return templateInstance;
    }
    return new TemplateDeclaration() {
      @Override
      public SNodeReference getTemplateNode() {
        return templateDeclaration.getSourceNode();
      }

      @Override
      public Collection<SNode> apply(@NotNull TemplateExecutionEnvironment environment, @NotNull TemplateContext context) throws GenerationException {
        if (context instanceof DefaultTemplateContext) {
          ((DefaultTemplateContext) context).engageIgnoreNullVariablesHack();
        }
        return templateInstance.apply(environment, context);
      }

      @Override
      public Collection<SNode> weave(@NotNull WeaveContext context, @NotNull NodeWeaveFacility weaveFacility) throws GenerationException {
        // FWIW, I believe there were no cross-model weaving calls, and interpreted templates never used TD.weave() but had interpreted nodes directly.
        // Besides, weaving of templates with arguments seems to be of elusive probability
        // Here we are in new API, i.e. invoked from the code that had never before weaved any external template, therefore, we shall never get here with MPS code
        // Nevertheless, for a small chance of an clients interpreted generator that weaves cross-model generated template and use this with 2018.3 without
        // regeneration of a code (e.g. mbeddr client on 2018.3 with interpreted generator (i.e. new API in use) weaves a compiled template from 2018.2-built mbeddr distro)
        // we still have this support
        TemplateContext tc = weaveFacility.getTemplateContext();
        if (tc instanceof DefaultTemplateContext) {
          ((DefaultTemplateContext) tc).engageIgnoreNullVariablesHack();
        }
       return templateInstance.weave(context, weaveFacility);
      }
    };
  }

  /*package*/ TemplateDeclaration loadTemplateDeclaration(@NotNull SNodeReference templateDeclaration, @NotNull SNodeReference templateNode, @NotNull TemplateContext context, Object... arguments) {
    TemplateModel templateModel = generator.getGenerationPlan().getTemplateModel(templateDeclaration.getModelReference());
    TemplateDeclaration templateDeclarationInstance = templateModel == null ? null : templateModel.loadTemplate(templateDeclaration, arguments);
    if (templateModel == null || templateDeclarationInstance == null) {
      String msg = "%s not found: cannot apply template declaration, try to check & regenerate affected generators";
      getLogger().error(templateNode, String.format(msg, templateModel == null ? "template model" : "declaration"),
          GeneratorUtil.describeIfExists(context.getInput(), "input"),
          GeneratorUtil.describe(templateNode, "template"),
          GeneratorUtil.describe(templateDeclaration, "template declaration"));
      return null;
    }
    return templateDeclarationInstance;
  }

  @Override
  public TemplateDeclarationKey createTemplateKey(String modelRef, String nodeId, String templateName) {
    /*
     * Need to create a key for external template both in TemplateDeclarationBase and ReductionRuleBase subclasses, hence
     * have to placed this method into a shared location. Besides, would be great to have proper access to PersistenceFacade, which is possible here
     * Perhaps, shall introduce few copies of findTemplate() method, one to take these strings, and another to take TD instance and wrap it with a trace facility?
     * Why I didn't do it right away: (a) the idea behind TDK was to keep 1 single key, (b) few related methods urge me to group them into ApplyFacility I've
     * just deleted. Need to make up my mind
     */
    PersistenceFacade pf = PersistenceFacade.getInstance();
    return TemplateIdentity.fromPointer(new SNodePointer(pf.createModelReference(modelRef), pf.createNodeId(nodeId)), templateName);
  }

  @Override
  public void nodeCopied(TemplateContext context, SNode outputNode, String templateNodeId) {
    generator.nodeCopied(context, outputNode, templateNodeId);
  }

  @Override
  public void registerLabel(SNode inputNode, SNode outputNode, String mappingLabel) {
    generator.registerMappingLabel(inputNode, mappingLabel, outputNode);
  }

  @Override
  public void registerLabel(SNode inputNode, Iterable<SNode> outputNodes, String mappingLabel) {
    for (SNode outputNode : outputNodes) {
      generator.registerMappingLabel(inputNode, mappingLabel, outputNode);
    }
  }

  @Override
  public void resolveInTemplateLater(@NotNull SNode outputNode, @NotNull SReferenceLink role, SNodeReference sourceNode, String templateNodeId, String resolveInfo, TemplateContext context) {
    ReferenceInfo_Template refInfo = new ReferenceInfo_Template(sourceNode, templateNodeId, resolveInfo, context);
    new PostponedReference(role, outputNode, refInfo).registerWith(generator);
  }

  @Override
  public void resolve(@NotNull ReferenceResolver resolver) {
    ReferenceInfo_Macro refInfo = new ReferenceInfo_Macro(resolver);
    new PostponedReference(resolver.getReferenceRole(), resolver.getOutputNode(), refInfo).registerWith(generator);
  }

  @Override
  public void postProcess(@NotNull NodePostProcessor postProcessor) {
    generator.getDelayedChanges().add(postProcessor);
  }

  @NotNull
  @Override
  public NodeWeaveFacility prepareWeave(@NotNull WeaveContext context, @NotNull SNodeReference templateNode) {
    return new NodeWeaveSupport(context, templateNode, this);
  }

  // Internal API, perhaps, shall be part of ExecutionEnvironmentInternal iface

  void blockReductionsForCopiedNode(SNode inputNode, SNode outputNode) {
    myReductionTrack.blockReductionsForCopiedNode(inputNode, outputNode);
  }

  @Nullable
  Collection<SNode> tryToReduce(@NotNull SNode inputNode) throws GenerationFailureException, GenerationCanceledException {
    FastRuleFinder rf = generator.getRuleManager().getReductionRules();
    Collection<SNode> outputNodes = tryToReduce(rf, new DefaultTemplateContext(this, inputNode, null));
    if (outputNodes != null) {
      if (outputNodes.size() == 1) {
        // [artem] I have no idea why same mappings are not done for switch, but it's the way it goes from rev d552b27
        SNode reducedNode = outputNodes.iterator().next();
        // output node should be accessible via 'findCopiedNode'
        generator.addCopiedOutputNodeForInputNode(inputNode, reducedNode);
        // preserve user objects
        if (TracingUtil.getInput(reducedNode) == null) {
          CopyUtil.copyUserObjects(inputNode, reducedNode);
          // keep track of 'original input node'
          // XXX in fact, copyUserObjects, above, already did that for us
          TracingUtil.deriveOriginalNode(inputNode, reducedNode);
        }
      }
      generator.recordTransformInputTrace(inputNode, outputNodes);
      return outputNodes;
    }
    return null;
  }


  protected final Set<SNodeReference> myFailedRules = new ConcurrentHashSet<>();
  /*
   * returns null if no reductions found
   */
  @Nullable
  Collection<SNode> tryToReduce(FastRuleFinder<TemplateReductionRule> ruleFinder, @NotNull TemplateContext context) throws GenerationFailureException, GenerationCanceledException {
    final SNode inputNode = context.getInput();
    TemplateReductionRule reductionRule = null;
    // find rule
    List<TemplateReductionRule> conceptRules = ruleFinder.findReductionRules(inputNode);
    if (conceptRules == null) {
      return null;
    }
    TraceFacility traceSession = generator.getTraceSession();
    try {
      for (TemplateReductionRule rule : conceptRules) {
        RuleTrace2 ruleTrace2 = traceSession != null ? traceSession.reductionRule(rule) : null;
        reductionRule = rule;
        final boolean reductionBlocked = myReductionTrack.isReductionBlocked(inputNode, rule);
        if (ruleTrace2 != null) {
          ruleTrace2.blocked(reductionBlocked);
        }
        if (!reductionBlocked) {
          if (rule instanceof TemplateRuleWithCondition) {
            final boolean applicable = getQueryExecutor().isApplicable((TemplateRuleWithCondition) rule, context);
            if (ruleTrace2 != null) {
              ruleTrace2.condition(applicable);
            }
            if (!applicable) {
              continue;
            }
            // fall-through
          }
          try {
            RuleTrace ruleTrace = traceSession != null ? traceSession.reductionRuleReached(rule) : null;
            if (ruleTrace != null) {
              // FIXME fill ruleTrace with relevant context information
              // e.g. other rules for the concept, those detected as !isApplicable, etc.
              ruleTrace.push();
            }
            myReductionTrack.enter(inputNode, rule);
            Collection<SNode> outputNodes = getQueryExecutor().applyRule(rule, context);
            if (ruleTrace2 != null) {
              ruleTrace2.completed(outputNodes);
            }
            if (outputNodes != null) {
              SNodeId in = context.getInput() == null ? null : context.getInput().getNodeId();
              getTrace().trace(in, GenerationTracerUtil.translateOutput(outputNodes), rule.getRuleNode());
              generator.copyNodeAttributes(context, outputNodes, this);
              return outputNodes;
            }
          } catch (DismissTopMappingRuleException ex) {
            // it's ok, just continue with a next applicable rule, if any
            generator.reportDismissRuleException(ex, reductionRule);
            if (ruleTrace2 != null) {
              ruleTrace2.dismissed();
            }
          } finally {
            myReductionTrack.leave();
          }
        }
      }
    } catch (AbandonRuleInputException ex) {
      return Collections.emptyList();
    } catch (TemplateProcessingFailureException ex) {
      SNodeReference ruleNode = reductionRule.getRuleNode();
      if (myFailedRules.add(ruleNode)) {
        getLogger().error(ruleNode, String.format("Reduction rule failed: %s", ex.getMessage()), ex.asProblemDescription());
      }
    } catch (GenerationFailureException | GenerationCanceledException ex) {
      throw ex;
    } catch (GenerationException ex) {
      // ignore
      // shall not happen provided we know all subclasses of GenerationException.
      // XXX why log? perhaps, could throw as GenerationFailureException? Meanwhile, did what TemplateGenerator#applyCreateRoot does
      getLogger().error(reductionRule == null ? null : reductionRule.getRuleNode(), "internal error: " + ex.toString());
    }
    return null;
  }
}
