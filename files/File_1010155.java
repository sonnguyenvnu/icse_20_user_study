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

import jetbrains.mps.generator.impl.query.MapNodeQuery;
import jetbrains.mps.generator.impl.query.MapPostProcessor;
import jetbrains.mps.generator.runtime.NodePostProcessor;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.generator.template.MapSrcMacroContext;
import jetbrains.mps.generator.template.MapSrcMacroPostProcContext;
import jetbrains.mps.generator.template.QueryExecutionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

/**
 * Default implementation of {@link NodePostProcessor}, with default implementation.
 * Left abstract to stress there's no value in this class unless
 * {@link NodePostProcessor#substitute()} and/or {@link NodePostProcessor#postProcess(SNode)} are overridden.
 * @author Artem Tikhomirov
 */
public abstract class MapSrcProcessor implements NodePostProcessor {
  private final SNodeReference myTemplateNode;
  private final SNode myOutputAnchor;
  private final TemplateContext myContext;

  protected MapSrcProcessor(@NotNull SNodeReference templateNode, @NotNull SNode outputAnchor, @NotNull TemplateContext context) {
    myTemplateNode = templateNode;
    myOutputAnchor = outputAnchor;
    myContext = context;
  }

  @NotNull
  @Override
  public final SNodeReference getTemplateNode() {
    return myTemplateNode;
  }

  @NotNull
  @Override
  public final SNode getOutputAnchor() {
    return myOutputAnchor;
  }

  /**
   * Default implementation returns {@link #getOutputAnchor()}
   */
  @NotNull
  @Override
  public SNode substitute() throws GenerationFailureException {
    return getOutputAnchor();
  }

  /**
   * Default implementation does nothing
   */
  @Override
  public void postProcess(@NotNull SNode outputNode) throws GenerationFailureException {
    // no-op
  }

  @NotNull
  public TemplateContext getTemplateContext() {
    return myContext;
  }

  /**
   * Support for substitute/post-process functions in interpreted templates
   */
  public static class MapSrcMacroProcessorInterpreted extends MapSrcProcessor {
    private final MapNodeQuery myMapNodeQuery;
    private final MapPostProcessor myPostProcessor;

    public MapSrcMacroProcessorInterpreted(@NotNull MapNodeQuery mapNodeQuery, @Nullable MapPostProcessor postProcessor, @NotNull SNodeReference mapSrcMacro, @NotNull SNode outputAnchor, @NotNull TemplateContext context) {
      super(mapSrcMacro, outputAnchor, context);
      myMapNodeQuery = mapNodeQuery;
      myPostProcessor = postProcessor;
    }

    public MapSrcMacroProcessorInterpreted(@NotNull MapPostProcessor postProcessor, @NotNull SNodeReference mapSrcMacro, @NotNull SNode outputAnchor, @NotNull TemplateContext context) {
      super(mapSrcMacro, outputAnchor, context);
      myMapNodeQuery = null;
      myPostProcessor = postProcessor;
    }

    @NotNull
    @Override
    public SNode substitute() throws GenerationFailureException {
      if (myMapNodeQuery != null) {
        TemplateContext tc = getTemplateContext();
        final QueryExecutionContext queryExecutor = tc.getEnvironment().getQueryExecutor();
        SNode child = queryExecutor.evaluate(myMapNodeQuery, new MapSrcMacroContext(tc, getOutputAnchor().getParent(), getTemplateNode()));
        if (child != null) {
          return child;
        }
        tc.getEnvironment().getLogger().error(getTemplateNode(), "Unexpected null value. Transform function of MAP-SRC didn't produce any result. Please check the function and make sure it always supplies a node");
        // fall-through
      }
      return super.substitute();
    }

    @Override
    public void postProcess(@NotNull SNode outputNode) throws GenerationFailureException {
      if (myPostProcessor != null) {
        TemplateContext tc = getTemplateContext();
        final QueryExecutionContext queryExecutor = tc.getEnvironment().getQueryExecutor();
        queryExecutor.execute(myPostProcessor, new MapSrcMacroPostProcContext(tc, outputNode, getTemplateNode()));
      }
    }
  }
}
