/*
 * Copyright 2003-2016 JetBrains s.r.o.
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
import jetbrains.mps.generator.runtime.ApplySink;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.runtime.TemplateExecutionEnvironment;
import jetbrains.mps.generator.template.ITemplateProcessor;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Container for Template Fragments, collects them and applies through supplied TemplateProcessor.
 * <p>For weave rule/macro there's {@link jetbrains.mps.generator.impl.WeaveTemplateContainer} counterpart.
 * @author Artem Tikhomirov
 */
public class TemplateContainer extends RuleConsequenceProcessor {
  private final SNode myTemplateNode;
  private List<Pair<SNode, String>> myNodeAndMappingNamePairs;

  public TemplateContainer(@NotNull SNode templateContainer) {
    myTemplateNode = templateContainer;
  }

  public TemplateContainer(@NotNull Pair<SNode, String> fragment) {
    myTemplateNode = null;
    myNodeAndMappingNamePairs = Collections.singletonList(fragment);
  }

  /*
   * Initialize container once for a template, then apply multiple times.
   */
  private void initialize() throws TemplateProcessingFailureException {
    if (myNodeAndMappingNamePairs != null) {
      return;
    }
    List<SNode> fragments = checkAdjacentFragments();
    List<Pair<SNode, String>> result = new ArrayList<>(fragments.size());
    for (SNode fragment : fragments) {
      result.add(new Pair<>(SNodeOperations.getParent(fragment), GeneratorUtilEx.getMappingName_TemplateFragment(fragment, null)));
    }
    myNodeAndMappingNamePairs = result;
  }

  @NotNull
  @Override
  public List<SNode> processRuleConsequence(@NotNull TemplateContext ctx)
      throws GenerationFailureException, DismissTopMappingRuleException, GenerationCanceledException {
    initialize();
    ArrayList<SNode> outputNodes = new ArrayList<>();
    final TemplateExecutionEnvironment environment = ctx.getEnvironment();
    final GenerationTrace tracer = environment.getTrace();
    ITemplateProcessor templateProcessor = environment.getTemplateProcessor();
    for (Pair<SNode, String> nodeAndMappingNamePair : myNodeAndMappingNamePairs) {
      SNode templateNode = nodeAndMappingNamePair.o1;
      String innerMappingName = nodeAndMappingNamePair.o2;
      List<SNode> _outputNodes = templateProcessor.apply(templateNode, ctx.subContext(innerMappingName));
      SNode input = ctx.getInput();
      tracer.trace(input == null ? null : input.getNodeId(), GenerationTracerUtil.translateOutput(_outputNodes), templateNode.getReference());
      outputNodes.addAll(_outputNodes);
    }
    return outputNodes;
  }

  public void apply(ApplySink sink, TemplateContext ctx)
      throws GenerationFailureException, DismissTopMappingRuleException, GenerationCanceledException {
    initialize();
    final TemplateExecutionEnvironment environment = ctx.getEnvironment();
    final GenerationTrace tracer = environment.getTrace();
    ITemplateProcessor templateProcessor = environment.getTemplateProcessor();
    for (Pair<SNode, String> nodeAndMappingNamePair : myNodeAndMappingNamePairs) {
      SNode templateNode = nodeAndMappingNamePair.o1;
      String innerMappingName = nodeAndMappingNamePair.o2;
      List<SNode> _outputNodes = templateProcessor.apply(templateNode, ctx.subContext(innerMappingName));
      SNode input = ctx.getInput();
      tracer.trace(input == null ? null : input.getNodeId(), GenerationTracerUtil.translateOutput(_outputNodes), templateNode.getReference());
      sink.add(templateNode.getContainmentLink(), _outputNodes);
    }
  }

  @NotNull
  private List<SNode> checkAdjacentFragments() throws TemplateProcessingFailureException {
    List<SNode> fragments = GeneratorUtilEx.getTemplateFragments(myTemplateNode);
    if (fragments.isEmpty()) {
      throw new TemplateProcessingFailureException(myTemplateNode, "couldn't process template: no template fragments found");
    }
    if (fragments.size() > 1) {
      // GeneratorUtilEx.getTemplateFragments() shall not return null
      Iterator<SNode> it = fragments.iterator();
      SNode fragmentParent = it.next().getParent();
      assert fragmentParent != null; // free-floating fragment would be odd
      final SNode commonParent = fragmentParent.getParent();
      final SContainmentLink role = fragmentParent.getContainmentLink();
      while (it.hasNext()) {
        fragmentParent = it.next().getParent();
        assert fragmentParent != null; // free-floating fragment would be odd
        // it's parent template that specifies context node and its role where these template fragments would get injected into,
        // thus we check there's no assumption context node is different for fragments, and that they do not assume they may end up in distinct roles.
        // Technically, provided ITemplateProcessor.apply() would yield something extra but SNode, we could answer with Pair(SContainmentLink,SNode)
        // and inject TF outcome into different roles (see https://youtrack.jetbrains.com/issue/MPS-23373). However, this would compromise COPY-SRC
        // idea (it's attached to a distinct role), and we'd need something more general, like <<apply-templates/>> to handle all children
        if (commonParent != fragmentParent.getParent() || !role.equals(fragmentParent.getContainmentLink())) {
          String msg = "Couldn't process template: all template fragments must reside in the same parent node. Roles: expected %s, met %s";
          throw new TemplateProcessingFailureException(myTemplateNode, String.format(msg, role, fragmentParent.getContainmentLink().getName()));
        }
      }
    }
    return fragments;
  }
}
