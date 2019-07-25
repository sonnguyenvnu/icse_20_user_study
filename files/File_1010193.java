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

import jetbrains.mps.generator.runtime.TemplateDeclarationKey;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

/**
 * Provisional implementation of {@link TemplateDeclarationKey} to migrate existing uses of SNodeReference as a key to the interface.
 * @author Artem Tikhomirov
 * @since 2018.3
 */
@Immutable
public final class TemplateIdentity implements TemplateDeclarationKey {
  private final SNodeReference myNodeReference;
  private final String myTemplateName;

  private TemplateIdentity(SNodeReference nodeReference, String templateName) {
    myNodeReference = nodeReference;
    myTemplateName = templateName;
  }

  @Override
  public SModelReference getSourceModel() {
    return myNodeReference.getModelReference();
  }

  @Override
  public SNodeReference getSourceNode() {
    return myNodeReference;
  }

  @Override
  public String describe() {
    return String.format("Template declaration %s from %s", myTemplateName == null ? myNodeReference.getNodeId()  : myTemplateName, getSourceModel().getName());
  }

  // both arg and rv are not null
  public static TemplateDeclarationKey fromSourceNode(SNode/*node<TemplateDeclaration*/ templateDeclarationNode) {
    // XXX intention is to have extra info, e.g. template name to help describe template in a human-friendly way
    return new TemplateIdentity(templateDeclarationNode.getReference(), templateDeclarationNode.getName());
  }

  public static TemplateDeclarationKey fromPointer(SNodeReference template, @Nullable String templateName) {
    return new TemplateIdentity(template, templateName);
  }
}
