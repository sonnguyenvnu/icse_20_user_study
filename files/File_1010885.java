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
package jetbrains.mps.idea.java.psi.impl;

import com.intellij.psi.PsiManager;
import jetbrains.mps.idea.core.psi.MPSPsiNodeFactory;
import jetbrains.mps.idea.core.psi.impl.MPSPsiNode;
import jetbrains.mps.idea.core.psi.impl.MPSPsiRef;
import jetbrains.mps.idea.java.Constants.ConceptNames;
import jetbrains.mps.idea.java.psi.impl.blTypes.MPSPsiListType;
import jetbrains.mps.idea.java.psi.impl.blTypes.MPSPsiMapType;
import jetbrains.mps.idea.java.psi.impl.blTypes.MPSPsiSequenceType;
import jetbrains.mps.idea.java.psi.impl.blTypes.MPSPsiSetType;
import jetbrains.mps.idea.java.psi.impl.blTypes.MPSPsiStringType;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.HashMap;
import java.util.Map;

/**
 * evgeny, 1/28/13
 */
public class JavaMPSPsiNodeFactory implements MPSPsiNodeFactory {

  private static final Map<String, NodeCreator> factories = new HashMap<String, NodeCreator>();
  private static final Map<String, RefCreator> refFactories = new HashMap<String, RefCreator>();

  static {
    factories.put(ConceptNames.ClassConcept, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiClass(id, concept, containingRole, manager);
      }
    });
    // TODO use MPS-generated constant value
    factories.put(ConceptNames.Interface, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiInterface(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.EnumClass, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiEnum(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.ClassifierType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiClassifierType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.PrimitiveType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiPrimitiveType(id, concept, containingRole, manager);
      }
    });
    NodeCreator wildCardTypeNodeCreator = new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiWildcardType(id, concept, containingRole, manager);
      }
    };
    factories.put(ConceptNames.UpperBoundType, wildCardTypeNodeCreator);
    factories.put(ConceptNames.LowerBoundType, wildCardTypeNodeCreator);
    factories.put(ConceptNames.WildCardType, wildCardTypeNodeCreator);
    factories.put(ConceptNames.StringType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiStringType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.ArrayType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiArrayType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.VariableArityType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiVariableArityType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.ConstructorDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiConstructor(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.MethodDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiMethod(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.ParameterDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiParameter(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.TypeVariableDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiTypeParameter(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.TypeVariableReference, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiTypeParamRef(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.FieldDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiField(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.EnumConstantDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiEnumConstant(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.StaticFieldDeclaration, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiField(id, concept, containingRole, manager);
      }
    });

    // collection types
    factories.put(ConceptNames.SequenceType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiSequenceType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.ListType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiListType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.SetType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiSetType(id, concept, containingRole, manager);
      }
    });
    factories.put(ConceptNames.MapType, new NodeCreator() {
      @Override
      public MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager) {
        return new MPSPsiMapType(id, concept, containingRole, manager);
      }
    });



    RefCreator dotBasedRefCreator = new RefCreator() {
      @Override
      public MPSPsiRef createReferenceNode(SReferenceLink role, SModelReference targetModel, SNodeId targetId, PsiManager manager) {
        return new MPSDotBasedPsiRef(role, targetModel, targetId, manager);
      }

      @Override
      public MPSPsiRef createReferenceNode(SReferenceLink role, String referenceText, PsiManager manager) {
        return new MPSDotBasedPsiRef(role, referenceText, manager);
      }
    };

    RefCreator methodRefCreator = new RefCreator() {
      @Override
      public MPSPsiRef createReferenceNode(SReferenceLink role, SModelReference targetModel, SNodeId targetId, PsiManager manager) {
        return new MPSPsiMethodRef(role, targetModel, targetId, manager);
      }

      @Override
      public MPSPsiRef createReferenceNode(SReferenceLink role, String referenceText, PsiManager manager) {
        return new MPSPsiMethodRef(role, referenceText, manager);
      }
    };

    refFactories.put(ConceptNames.Classifier, dotBasedRefCreator);
    refFactories.put(ConceptNames.VariableDeclaration, dotBasedRefCreator);
    // this is really for constructors (not all BaseMethodDeclarations)
    // it's done this way because currently Creators have link to ConstructorDeclaration
    // in a form of specialized link to BaseMethodDeclaration
    refFactories.put(ConceptNames.BaseMethodDeclaration, methodRefCreator);

  }

  @Override
  public MPSPsiNode create(SNodeId id, SConcept concept, String containingRole, PsiManager manager) {
    SConcept currConcept = concept;
    NodeCreator factory = null;

    while (factory == null && currConcept != null) {
      factory = factories.get(currConcept.getQualifiedName());
      currConcept = currConcept.getSuperConcept();
    }

    if (factory != null) {
      return factory.create(id, concept.getQualifiedName(), containingRole, manager);
    }

    return null;
  }

  private RefCreator getRefCreator(SAbstractConcept linkTargetConcept) {
    // if it's not SConcept (supposedly SInterfaceConcept) then we don't walk up the hierarchy
    if (!(linkTargetConcept instanceof SConcept)) {
      return refFactories.get(linkTargetConcept.getQualifiedName());
    }

    SConcept currConcept = (SConcept) linkTargetConcept;
    RefCreator factory = null;

    while (factory == null && currConcept != null) {
      factory = refFactories.get(currConcept.getQualifiedName());
      currConcept = currConcept.getSuperConcept();
    }
    return factory;
  }

  @Override
  public MPSPsiRef createReferenceNode(SReferenceLink role, SAbstractConcept linkTargetConcept, SModelReference targetModel, SNodeId targetId, PsiManager manager) {
    RefCreator factory = getRefCreator(linkTargetConcept);

    if (factory != null) {
      return factory.createReferenceNode(role, targetModel, targetId, manager);
    }

    return null;
  }

  @Override
  public MPSPsiRef createReferenceNode(SReferenceLink role, SAbstractConcept linkTargetConcept, String referenceText, PsiManager manager) {
    RefCreator factory = getRefCreator(linkTargetConcept);

    if (factory != null) {
      return factory.createReferenceNode(role, referenceText, manager);
    }

    return null;
  }

  private interface NodeCreator {
    MPSPsiNode create(SNodeId id, String concept, String containingRole, PsiManager manager);
  }

  private interface RefCreator {
    MPSPsiRef createReferenceNode(SReferenceLink role, SModelReference targetModel, SNodeId targetId, PsiManager manager);

    MPSPsiRef createReferenceNode(SReferenceLink role, String referenceText, PsiManager manager);
  }
}
