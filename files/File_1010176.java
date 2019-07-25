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
package jetbrains.mps.generator.impl.reference;

import jetbrains.mps.generator.impl.TemplateGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SReference;

/**
 * These references are created in transient models.
 * They are always internal.
 */
public class PostponedReference extends jetbrains.mps.smodel.SReference {

  private ReferenceInfo myReferenceInfo;
  private SReference myReplacementReference;
  private TemplateGenerator myGenerator;

  public PostponedReference(@NotNull SReferenceLink role, @NotNull SNode sourceNode, @NotNull ReferenceInfo referenceInfo) {
    super(role, sourceNode);
    myReferenceInfo = referenceInfo;
  }

  /*
   * We used to set postponed references in output model nodes right away. With in-place transformation,
   * however, when input model is the same as output, certain scenarios might lead to ordering dependency
   * between postponed references.
   * E.g. when DeltaBuilder.prepareReferences() replaces a reference in input-output model
   * with PostponedReference, and one of ReferenceMacro resolvers already in the list of
   * PostponedReferenceUpdate depends on that reference (i.e. ref1 in <code>genContext.get output LABEL for (node.ref1.value)</code>)
   * During PostponedReferenceUpdate, RM has no chance to resolve its target as it first needs a PostponedReference far behind it in the
   * list to get resolved.
   *
   * Therefore, we no longer change references of a source node, but merely collect references for later processing. Of course,
   * we could have done this conditionally for in-place transformation only, but generally I don't see a reason not to do the
   * same in a separate input/output models case.
   *
   * This change has an amusing side affect that no PostponedReference could ever stay in a model. On one hand, this avoids
   * cryptic errors about unexpected PostponedReference and helps to eliminate checks for PR in a transient model. OTOH, potential
   * problems are easy to overlook, and hard to trace down (i.e. no reference is created where previous generator would fail)
   *
   * @see https://youtrack.jetbrains.com/issue/MPS-22271
   */
  public void registerWith(@NotNull TemplateGenerator generator) {
    myGenerator = generator;
    generator.register(this);
  }

  /*package*/ TemplateGenerator getGenerator() {
    return myGenerator;
  }

  @Override
  @Nullable
  public synchronized SModelReference getTargetSModelReference() {
    if (myReplacementReference != null) {
      return myReplacementReference.getTargetSModelReference();
    }
    // ok, reference is unresolved and not required
    return null;
  }

  @Override
  protected SNode getTargetNode_internal() {
    if (myReplacementReference == null) {
      return null;
    }
    return myReplacementReference.getTargetNode();
  }

  /**
   * @return null is not resolved and not required.
   */
  public SReference initReplacementReference() {
    if (myReplacementReference != null) {
      return myReplacementReference;
    }

    synchronized (this) {
      if (myReferenceInfo == null) {
        return myReplacementReference; // already processed
      }

      myReplacementReference = myReferenceInfo.create(this);
      // release resources
      myReferenceInfo = null;
    }
    return myReplacementReference;
  }

  /**
   * replaces this instance with ether StaticReference or with DynamicReference. (only static so far)
   * removes reference in case of error.
   */
  public void replace() {
    getSourceNode().setReference(getLink(), myReplacementReference);
  }
}
