/*
 * Copyright 2003-2015 JetBrains s.r.o.
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

import jetbrains.mps.generator.IGeneratorLogger.ProblemDescription;
import jetbrains.mps.generator.TransientModelsModule;
import jetbrains.mps.generator.impl.GeneratorUtil;
import jetbrains.mps.generator.impl.RoleValidation.Status;
import jetbrains.mps.generator.impl.TemplateGenerator;
import jetbrains.mps.smodel.DynamicReference.DynamicReferenceOrigin;
import jetbrains.mps.smodel.SModelStereotype;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

/**
 * Restore a reference that points to a node from input model, either copied or transformed.
 * Created by: Sergey Dmitriev
 * Date: Jan 25, 2007
 */
public class ReferenceInfo_CopiedInputNode extends ReferenceInfo {

  private final SNode myInputNode;
  private final SNode myInputTargetNode;

  /**
   * @param inputTargetNode  reference target in input model
   */
  public ReferenceInfo_CopiedInputNode(SNode nodeBeingCopied, SNode inputTargetNode) {
    myInputNode = nodeBeingCopied;
    myInputTargetNode = inputTargetNode;
  }

  @Nullable
  @Override
  public SReference create(@NotNull PostponedReference ref) {
    if (myInputTargetNode != null) {
      // output target node might has been copied (reduced) from the input target node
      // here accept only one-to-one copying
      SNode ultimateTarget = ref.getGenerator().findCopiedOutputNodeForInputNode_unique(myInputTargetNode);
      if (ultimateTarget != null) {
        return createStaticReference(ref, ultimateTarget);
      }
      String resolveInfo = jetbrains.mps.util.SNodeOperations.getResolveInfo(myInputTargetNode);
      if (resolveInfo != null) {
        final SReference dr = createDynamicReference(ref, resolveInfo, new DynamicReferenceOrigin(null, myInputNode.getReference()));
        ref.getGenerator().registerDynamicReference(dr);
        return dr;
      }
      // if input was copied - return one of its copies
      // this can easy produce incorrect references
      SNode ambiguousTarget = ref.getGenerator().findCopiedOutputNodeForInputNode(myInputTargetNode);
      if (ambiguousTarget != null) {
        // RI_CIN is the only case doResolve_Tricky was implemented and hence checkResolveTarget check moved here.
        if (checkResolvedTarget(ref, ambiguousTarget)) {
          return createStaticReference(ref, ambiguousTarget);
        } else {
          return jetbrains.mps.smodel.SReference.create(ref.getLink(), ref.getSourceNode(), ref.getGenerator().getOutputModel().getReference(), null);
        }
      }
    }
    return createInvalidReference(ref, null);
  }

  private ProblemDescription[] getErrorDescriptions() {
    return new ProblemDescription[]{
      GeneratorUtil.describe(myInputNode, "input node")
    };
  }

  private boolean checkResolvedTarget(PostponedReference ref, SNode outputTargetNode) {
    final SNode outputSourceNode = ref.getSourceNode();
    final TemplateGenerator generator = ref.getGenerator();
    Status status = generator.getReferentRoleValidator(outputSourceNode, ref.getLink()).validate(outputTargetNode);
    if (status != null) {
      generator.getLogger().error(outputSourceNode.getReference(), status.getMessage(getClass().getSimpleName()), getErrorDescriptions());
      return false;
    }

    SModel referentNodeModel = outputTargetNode.getModel();
    if (referentNodeModel != null && referentNodeModel != outputSourceNode.getModel()) {
      if (SModelStereotype.isGeneratorModel(referentNodeModel)) {
        // references to template nodes are not acceptable
        String msg = "bad reference, cannot refer to a generator model: %s for role '%s' in %s";
        generator.getLogger().error(outputSourceNode.getReference(), String.format(msg,
                SNodeOperations.getDebugText(outputTargetNode), ref.getLink(), SNodeOperations.getDebugText(outputSourceNode)),
            getErrorDescriptions());
        return false;
      }
      if (referentNodeModel .getModule() instanceof TransientModelsModule) {
        // references to transient nodes in a model outside one being generated are not acceptable
        String msg = "bad reference, cannot refer to an external transient model: %s  for role '%s' in %s";
        generator.getLogger().error(outputSourceNode.getReference(), String.format(msg,
                SNodeOperations.getDebugText(outputTargetNode), ref.getLink(), SNodeOperations.getDebugText(outputSourceNode)),
            getErrorDescriptions());
        return false;
      }
    }
    return true;
  }
}
