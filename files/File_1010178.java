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
package jetbrains.mps.generator.impl.reference;

import jetbrains.mps.generator.impl.GenerationFailureException;
import jetbrains.mps.generator.impl.GeneratorUtil;
import jetbrains.mps.smodel.DynamicReference.DynamicReferenceOrigin;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.model.SReference;

/**
 * @author Artem Tikhomirov
 * @since 2017.2
 */
public abstract class ReferenceInfo_MacroBase extends ReferenceInfo {

  protected ReferenceInfo_MacroBase() {
  }

  @Nullable
  @Override
  public final SReference create(@NotNull PostponedReference ref) {
    try {
      Object result = expandReferenceMacro(ref);
      if (result instanceof SNode) {
        final SNode outputTargetNode = checkOutputNode(ref, (SNode) result);
        return createStaticReference(ref, outputTargetNode);
      } else if (result instanceof String) {
        final String resolveInfoForDynamicResolve = (String) result;
        final SReference dr = createDynamicReference(ref, resolveInfoForDynamicResolve, new DynamicReferenceOrigin(getMacroNodeRef(), null));
        ref.getGenerator().registerDynamicReference(dr);
        return dr;
      } else if (result instanceof SNodeReference) {
        SNodeReference refTarget = (SNodeReference) result;
        return jetbrains.mps.smodel.SReference.create(ref.getLink(), ref.getSourceNode(), refTarget.getModelReference(), refTarget.getNodeId());
      }
      if (!ref.getLink().isOptional()) {
        return createInvalidReference(ref, getInvalidReferenceResolveInfo());
      }
      return null; // why not always invalid reference? Is there a convention that RM with null value means "forget it"?
    } catch (GenerationFailureException ex) {
      // It's not nice to handle exception here (it could be exception fro user code and from generator's code, and we have no idea what's the proper way to
      // handle them), but I feel it's worth trying to go on with invalid reference, and handling exception here is much better than silently ignoring it.
      ref.getGenerator().getLogger().handleException(ex);
      ref.getGenerator().getLogger().error(getMacroNodeRef(), ex.getMessage(), GeneratorUtil.describe(ref.getSourceNode(), "source node"));
      // when there's an error, it's better to see broken reference than no reference at all.
      return createInvalidReference(ref, getInvalidReferenceResolveInfo()); // perhaps, "failure" to indicate there's an error?
    }
  }

  @Nullable
  protected abstract Object expandReferenceMacro(PostponedReference ref) throws GenerationFailureException;

  @Nullable
  protected abstract SNodeReference getMacroNodeRef();

  @Nullable
  protected abstract String getInvalidReferenceResolveInfo();

  private SNode checkOutputNode(PostponedReference ref, SNode outputTargetNode) {
    // check referent because it's manual and thus error prone mapping
    if (outputTargetNode.getModel() == ref.getGenerator().getInputModel()) {
      // There are RM that return input node from getReferent (e.g. in closures). The code below handles these cases, although I'm not
      // quite confident it's a nice idea in the first place (getReferent shall not return input nodes, imo)
      // try to find copy in output model and replace target
      SNode outputTargetNode_output = ref.getGenerator().findCopiedOutputNodeForInputNode(outputTargetNode);
      if (outputTargetNode_output != null) {
        return outputTargetNode_output;
      } else {
        // FIXME showErrorIfStrict
        final String msg = "reference macro returned node from input model; role: %s in %s";
        ref.getGenerator().getLogger().warning(getMacroNodeRef(), String.format(msg, ref.getLink(), SNodeOperations.getDebugText(ref.getSourceNode())),
                                               GeneratorUtil.describe(ref.getSourceNode(), "source node"),
                                               GeneratorUtil.describeIfExists(outputTargetNode, "target node in input model"));
      }
    }
    return outputTargetNode;
  }
}
