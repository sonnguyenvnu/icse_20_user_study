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
package jetbrains.mps.smodel.runtime;

import jetbrains.mps.smodel.DynamicReference;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.smodel.presentation.NodePresentationUtil;
import jetbrains.mps.smodel.presentation.ReferenceConceptUtil;
import jetbrains.mps.util.StringUtil;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeAccessUtil;
import org.jetbrains.mps.openapi.model.SReference;

/**
 * @author Radimir.Sorokin
 */
public final class NodePresentationProviders {

  private NodePresentationProviders() {
  }

  static NodePresentationProvider raw(String presentation) {
    return node -> presentation;
  }

  public static final NodePresentationProvider BY_NAME = node -> {
    String name = SNodeAccessUtil.getProperty(node, SNodeUtil.property_INamedConcept_name);
    return StringUtil.isEmpty(name) ? "<no name>[" + node.getConcept().getName() + "]" : name;
  };

  public static NodePresentationProvider byReference(SReferenceLink link, String prefix, String suffix) {
    return node -> {
      SReference ref = node.getReference(link);
      if (ref instanceof DynamicReference) {
        return prefix + ((DynamicReference) ref).getResolveInfo() + suffix;
      }
      SNode referent = node.getReferenceTarget(link);
      String refPresentation;
      if (referent == null) {
        refPresentation = "<no " + link.getName() + ">";
      } else if (referent == node) {
        refPresentation = referent.getConcept().getName();
      } else {
        refPresentation = NodePresentationUtil.presentation(referent, node);
      }
      return prefix + refPresentation + suffix;
    };
  }

  public static final NodePresentationProvider LEGACY = node -> {
    if (node.isInstanceOfConcept(SNodeUtil.concept_INamedConcept)) {
      String name = SNodeAccessUtil.getProperty(node, SNodeUtil.property_INamedConcept_name);
      if (name != null) {
        return name;
      }
      return "<no name>[" + node.getConcept().getName() + "]";
    }

    String smartRefPresentation = ReferenceConceptUtil.getPresentation(node);
    if (smartRefPresentation != null) {
      return smartRefPresentation;
    }

    String conceptAlias = node.getConcept().getConceptAlias();
    if (!StringUtil.isEmpty(conceptAlias)) {
      return conceptAlias;
    }

    return node.getConcept().getName();
  };
}
