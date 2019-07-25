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

import jetbrains.mps.logging.Logger;
import jetbrains.mps.smodel.CopyUtil;
import jetbrains.mps.smodel.DynamicReference;
import jetbrains.mps.smodel.ModelImports;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.textgen.trace.TracingUtil;
import jetbrains.mps.util.SNodeOperations;
import org.apache.log4j.LogManager;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

public class CloneUtil {
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(CloneUtil.class));

  private final SModel myInputModel;
  private final SModel myOutputModel;
  private final SModelReference myOutputModelRef;
  private boolean myTraceOriginalInput = false;
  private final Factory myFactory;

  public CloneUtil(SModel inputModel, SModel outputModel) {
    this(inputModel, outputModel, new RegularSModelFactory());
  }
  public CloneUtil(SModel inputModel, SModel outputModel, Factory factory) {
    myInputModel = inputModel;
    myOutputModel = outputModel;
    myOutputModelRef = outputModel.getReference();
    myFactory = factory;
  }

  /**
   * Record origin of copied node with TracingUtil
   * @return <code>this</code> for convenience
   */
  public CloneUtil traceOriginalInput() {
    myTraceOriginalInput = true;
    return this;
  }

  /**
   * Creates cloned model, each node in target model has the same nodeId that corresponding node in source model
   * it allows to resolve internal references much faster
   */
  public void cloneModelWithImports() {
    //copy model with imports, used languages and devkits
    cloneModel();
    final ModelImports modelImports = new ModelImports(myOutputModel);
    modelImports.copyImportedModelsFrom(myInputModel);
    modelImports.copyEmployedDevKitsFrom(myInputModel);
    modelImports.copyUsedLanguagesFrom(myInputModel);
  }

  public void cloneModel() {
    for (SNode node : myInputModel.getRootNodes()) {
      SNode outputNode = clone(node);
      myOutputModel.addRootNode(outputNode);
    }
  }

  // FIXME CopyUtil.copy() respects references within cloned sub-tree, but doesn't work with node factory. Shall combine both into single utility
  public SNode clone(SNode inputNode) {
    SNode outputNode = myFactory.create(inputNode);

    CopyUtil.copyProperties(inputNode, outputNode);
    CopyUtil.copyUserObjects(inputNode, outputNode);
    // keep track of 'original input node'
    if (myTraceOriginalInput) {
      TracingUtil.putInputNode(outputNode, inputNode);
    }
    for (SReference reference : inputNode.getReferences()) {
      boolean ext = inputNode.getModel() == null || !inputNode.getModel().getReference().equals(reference.getTargetSModelReference());
      SModelReference targetModelReference = ext ? reference.getTargetSModelReference() : myOutputModelRef;
      SReference outRef = myFactory.create(reference, outputNode, targetModelReference);
      if (outRef != null) {
        outputNode.setReference(outRef.getLink(), outRef);
      }
    }

    for (SNode child : inputNode.getChildren()) {
      SContainmentLink role = child.getContainmentLink();
      assert role != null;
      outputNode.addChild(role, clone(child));
    }
    return outputNode;
  }

  public static DynamicReference create(SNode outputNode, SModelReference targetModelRef, DynamicReference prototype) {
    DynamicReference outputReference = new DynamicReference(
        prototype.getLink(),
        outputNode,
        targetModelRef,
        prototype.getResolveInfo());
    outputReference.setOrigin(prototype.getOrigin());
    return outputReference;
  }

  public interface Factory {
    SNode create(SNode prototype);
    SReference create(SReference prototype, SNode outputNode, SModelReference targetModelRef);
  }

  public static class RegularSModelFactory implements Factory {

    @Override
    public SNode create(SNode prototype) {
      return new jetbrains.mps.smodel.SNode(prototype.getConcept(), prototype.getNodeId());
    }

    @Override
    public SReference create(SReference prototype, SNode outputNode, SModelReference targetModelRef) {
      // [model] clone mechanism in smodel.SReference or elsewhere not to perform instanceof
      // Besides, what if there's custom openapi.SReference impl (GenSReference) I'm not aware of? How am I supposed to clone it here?
      if (prototype instanceof StaticReference) {
        if (targetModelRef == null) {
          LOG.warning("broken reference '" + prototype.getLink().getName() + "' in " + SNodeOperations.getDebugText(prototype.getSourceNode()), prototype.getSourceNode());
        } else {
          StaticReference outputReference = new StaticReference(
              prototype.getLink(),
              outputNode,
              targetModelRef,
              prototype.getTargetNodeId(),
              ((StaticReference) prototype).getResolveInfo());
          return outputReference;
        }
      } else if (prototype instanceof DynamicReference) {
        return CloneUtil.create(outputNode, targetModelRef, (DynamicReference) prototype);
      } else {
        LOG.error("internal error: can't clone reference '" + prototype.getLink().getName() + "' in " + SNodeOperations.getDebugText(prototype.getSourceNode()), prototype.getSourceNode());
        LOG.error(" -- was reference class : " + prototype.getClass().getName());
      }
      return null;
    }
  }
}
