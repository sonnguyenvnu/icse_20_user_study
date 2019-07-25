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

package jetbrains.mps.idea.java.index;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.util.indexing.DataIndexer;
import com.intellij.util.indexing.FileContent;
import jetbrains.mps.core.platform.Platform;
import jetbrains.mps.extapi.model.SModelData;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.smodel.SNodeUtil;
import jetbrains.mps.util.CollectConsumer;
import jetbrains.mps.workbench.index.RootNodeNameIndex;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.util.Consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * FIXME see {@link SNodeDescriptorsDataExternalizer} for details
 * User: fyodor
 * Date: 3/28/13
 */
/*package*/ abstract class AbstractSModelIndexer<S, E> implements DataIndexer<String, Collection<E>, FileContent> {

  private static final Logger LOG = Logger.getLogger(AbstractSModelIndexer.class);

  private static final String[] JAVA_CLASS_CONCEPTS = {
    "jetbrains.mps.baseLanguage.structure.Annotation",
    "jetbrains.mps.baseLanguage.structure.ClassConcept",
    "jetbrains.mps.baseLanguage.structure.EnumClass",
    "jetbrains.mps.baseLanguage.structure.Interface",
    "jetbrains.mps.baseLanguage.tuples.structure.NamedTupleDeclaration",
    "jetbrains.mps.baseLanguage.unitTest.structure.BTestCase",
  };

  private static final String[] JAVA_METHOD_CONCEPTS = {
    "jetbrains.mps.baseLanguage.closures.structure.FunctionMethodDeclaration",
    "jetbrains.mps.baseLanguage.structure.AnnotationMethodDeclaration",
    "jetbrains.mps.baseLanguage.structure.InstanceMethodDeclaration",
    "jetbrains.mps.baseLanguage.structure.StaticMethodDeclaration",
    "jetbrains.mps.baseLanguage.unitTest.structure.TestMethod",
  };

  private static final String[] JAVA_FIELD_CONCEPTS = {
    "jetbrains.mps.baseLanguage.structure.FieldDeclaration",
    "jetbrains.mps.baseLanguage.structure.StaticFieldDeclaration",
  };

  protected void getJavaClasses(SModelData sModel, Consumer<SNode> consumer) {
    for (SNode root : sModel.getRootNodes()) {
      collectJavaClasses(root, consumer);
    }
  }

  protected void getJavaMethods(SModelData sModel, Consumer<SNode> consumer) {
    CollectConsumer<SNode> classes = new CollectConsumer<>(new ArrayList<>());
    for (SNode root : sModel.getRootNodes()) {
      collectJavaClasses(root, classes);
    }
    for (SNode cls : classes.getResult()) {
      collectJavaMethods(cls, consumer);
    }
  }

  protected void getJavaFields(SModelData sModel, Consumer<SNode> consumer) {
    CollectConsumer<SNode> classes = new CollectConsumer<>(new ArrayList<>());
    for (SNode root : sModel.getRootNodes()) {
      collectJavaClasses(root, classes);
    }
    for (SNode cls : classes.getResult()) {
      collectJavaFields(cls, consumer);
    }
  }

  private void collectJavaClasses(SNode cand, Consumer<SNode> consumer) {
    if (isClassOrInterface(cand)) {
      consumer.consume(cand);
      for (SNode chd : cand.getChildren()) {
        collectJavaClasses(chd, consumer);
      }
    }
  }

  private void collectJavaMethods(SNode node, Consumer<SNode> consumer) {
    for (SNode cand : node.getChildren()) {
      if (isMethod(cand)) {
        consumer.consume(cand);
        continue;
        // TODO: walk the body and collect methods from the anonymous classes?
      }
      collectJavaMethods(cand, consumer);
    }
  }

  private void collectJavaFields(SNode node, Consumer<SNode> consumer) {
    for (SNode cand : node.getChildren()) {
      if (isField(cand)) {
        consumer.consume(cand);
        continue;
      }
      collectJavaFields(cand, consumer);
     }
  }

  private boolean isClassOrInterface(SNode sNode) {
    return isExactConcept(sNode, JAVA_CLASS_CONCEPTS);
  }

  private boolean isMethod(SNode sNode) {
    return isExactConcept(sNode, JAVA_METHOD_CONCEPTS);
  }

  private boolean isField(SNode sNode) {
    return isExactConcept(sNode, JAVA_FIELD_CONCEPTS);
  }

  private boolean isExactConcept(SNode sNode, String[] concept) {
    String qualifiedName = sNode.getConcept().getQualifiedName();
    int idx = Arrays.binarySearch(concept, qualifiedName);
    return idx >= 0;
  }

  private final Platform myPlatform;
  {
    myPlatform = ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getPlatform();
  }

  @NotNull
  @Override
  public Map<String, Collection<E>> map(@NotNull final FileContent inputData) {
    final HashMap<String, Collection<E>> map = new HashMap<>();

    try {
      final SModelData model = RootNodeNameIndex.doModelParsing(myPlatform, inputData);
      if (model == null) {
        return Collections.emptyMap();
      }
      final SModelReference modelRef = model.getReference();
      getObjectsToIndex(model, object -> {
        String[] keys = getKeys(model, object);

        for (String key : keys) {
          Collection<E> collection = map.get(key);
          if (collection == null) {
            collection = new ArrayList<>();
            map.put(key, collection);
          }

          updateCollection(modelRef, object, collection);
        }
      });
    } catch (Exception e) {
      LOG.error("Error indexing model file " + inputData.getFileName(), e);
    }

    return map;
  }

  protected abstract void getObjectsToIndex(SModelData sModel, Consumer<S> consumer);

  protected abstract String[] getKeys(SModelData model, S object);

  protected abstract void updateCollection(SModelReference modelRef, S object, Collection<E> collection);

  protected String getSNodeName(SNode node) {
    String persistentName = node.getProperty(SNodeUtil.property_INamedConcept_name);
    return (persistentName == null) ? "null" : persistentName;
  }
}
