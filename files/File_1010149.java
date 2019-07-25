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

import jetbrains.mps.generator.IGeneratorLogger;
import jetbrains.mps.generator.IGeneratorLogger.ProblemDescription;
import jetbrains.mps.generator.impl.cache.MappingsMemento;
import jetbrains.mps.generator.runtime.TemplateContext;
import jetbrains.mps.textgen.trace.TracingUtil;
import jetbrains.mps.util.Pair;
import jetbrains.mps.util.ToStringComparator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Runtime state of mapping labels at some transformation step.
 * {@link MappingsMemento} is persistence-friendly companion.
 *
 * Evgeny Gryaznov, Feb 16, 2010
 */
public final class GeneratorMappings {
  private final IGeneratorLogger myLog;

  /* mapping,input -> output */
  private final ConcurrentMap<String, Map<SNode, Object>> myMappingNameAndInputNodeToOutputNodeMap = new ConcurrentHashMap<>();

  /* input -> output */
  private final ConcurrentMap<SNode, Object> myCopiedOutputNodeForInputNode = new ConcurrentHashMap<>();

  /* new style map: Object means multiple nodes for the template */
  private final ConcurrentMap<String, Object> myTemplateNodeIdToOutputNodeMap = new ConcurrentHashMap<>();

  /* new style map: template,input -> output */
  private final ConcurrentMap<Pair<String, SNode>, SNode> myTemplateNodeIdAndInputNodeToOutputNodeMap = new ConcurrentHashMap<>();

  /*
   * there might be few conditional roots, and we can't prevent them from using same ML (not too much sense, however)
   */
  private final CopyOnWriteArrayList<Pair<String, SNode>> myConditionalRoots = new CopyOnWriteArrayList<>();

  public GeneratorMappings(IGeneratorLogger log) {
    myLog = log;
  }

  // add methods

  void addOutputNodeByTemplateNode(String templateNodeId, @NotNull SNode outputNode) {
    if (myTemplateNodeIdToOutputNodeMap.putIfAbsent(templateNodeId, outputNode) != null) {
      myTemplateNodeIdToOutputNodeMap.put(templateNodeId, this);
    }
  }

  void addOutputNodeByInputNodeAndMappingName(SNode inputNode, String mappingName, SNode outputNode) {
    if (mappingName == null) {
      return;
    }
    Map<SNode, Object> currentMapping = myMappingNameAndInputNodeToOutputNodeMap.get(mappingName);
    if (currentMapping == null) {
      // we save labeled transformations, and chances are we get similar input/output nodes with the same ML
      // (e.g. multipleLoopVarDecl rmt -> rmt_var) more than once - e.g. typesystem makes a copy of some rules
      // to generate two methods, and if there's an MultiForLoop in the original code, we get two almost identical
      // ML entries (presentation of output and key's original nodes are the same, see comparator in #getSortedMappingKeys(),
      // below. Therefore, for nodes that are 'equal' that way, would like to keep an order they were registered at, hence LinkedHashMap
      myMappingNameAndInputNodeToOutputNodeMap.putIfAbsent(mappingName, new LinkedHashMap<>());
      currentMapping = myMappingNameAndInputNodeToOutputNodeMap.get(mappingName);
    }
    synchronized (currentMapping) {
      Object o = currentMapping.get(inputNode);
      if (o == null) {
        currentMapping.put(inputNode, outputNode);
      } else if (o instanceof List) {
        ((List<SNode>) o).add(outputNode);
      } else if (o != outputNode) {
        List<SNode> list = new ArrayList<>(4);
        list.add((SNode) o);
        list.add(outputNode);
        currentMapping.put(inputNode, list);
      } else {
        // TODO warning
      }
    }
  }

  /**
   * record a newly created node (generally, conditional root rule - no input node)
   * @param mappingLabel label
   * @param outputNode new node
   */
  void addNewOutputNode(String mappingLabel, SNode outputNode) {
    if (mappingLabel == null || outputNode == null) {
      return;
    }
    myConditionalRoots.add(new Pair<>(mappingLabel, outputNode));
  }

  void addCopiedOutputNodeForInputNode(SNode inputNode, SNode outputNode) {
    if (outputNode == null) {
      return;
    }

    Object prev = myCopiedOutputNodeForInputNode.putIfAbsent(inputNode, outputNode);
    // It's possible for the same pair input->output to get registered more than once,
    // e.g. when a rule does COPY-SRC for input. Both the copy macro and the rule would
    // try to register same output, and we shall not treat this as ambiguity. Generally it's
    // odd to use COPY-SRC for rule's primary transformation, we've had only 1 use like this
    // (build language, mapBuildProject did $WEAVE$ $COPY-SRC$).
    if (prev instanceof SNode && prev != outputNode) {
      // ambiguity! store prev element (wrapped into Collection)
      myCopiedOutputNodeForInputNode.put(inputNode, Collections.singletonList(prev));
    }
  }

  void addOutputNodeByInputAndTemplateNode(SNode inputNode, String templateNodeId, SNode outputNode) {
    // todo: combination of (templateN, inputN) -> outputN
    // todo: is not unique
    // todo: generator should report error on attempt to obtain not unique output-node
    if (templateNodeId == null) {
      return;
    }
    myTemplateNodeIdAndInputNodeToOutputNodeMap.put(new Pair<>(templateNodeId, inputNode), outputNode);
  }

  void addOutputNodeForContext(TemplateContext templateContext, String templateNodeId, SNode outputNode) {
    // todo: combination of (templateN, inputN) -> outputN
    // todo: is not unique
    // todo: generator should report error on attempt to obtain not unique output-node
    addOutputNodeByInputAndTemplateNode(templateContext.getInput(), templateNodeId, outputNode);
    for (SNode historyInputNode : templateContext.getInputHistory()) {
      Pair<String,SNode> key = new Pair<>(templateNodeId, historyInputNode);
      myTemplateNodeIdAndInputNodeToOutputNodeMap.putIfAbsent(key, outputNode);
    }
    addOutputNodeByTemplateNode(templateNodeId, outputNode);
  }

  // find methods

  public SNode findOutputNodeByTemplateNodeUnique(String templateNode) {
    Object o = myTemplateNodeIdToOutputNodeMap.get(templateNode);
    return o instanceof SNode ? (SNode) o : null;
  }

  public SNode findOutputNodeByInputNodeAndMappingName(@Nullable SNode inputNode, @Nullable String mappingName) {
    if (mappingName == null) {
      return null;
    }
    Map<SNode, Object> currentMapping = myMappingNameAndInputNodeToOutputNodeMap.get(mappingName);
    if (currentMapping == null) {
      return null;
    }
    Object o = currentMapping.get(inputNode);
    if (o instanceof List) {
      List<SNode> list = (List<SNode>) o;
      ProblemDescription[] descr = new ProblemDescription[list.size() + 1];
      for (int i = 0; i < list.size(); i++) {
        descr[i] = GeneratorUtil.describe(list.get(i), "output");
      }
      descr[list.size()] = GeneratorUtil.describe(inputNode, "input");
      String msg = "%d  output nodes found for mapping label '%s'";
      myLog.warning(inputNode == null ? null : inputNode.getReference(), String.format(msg, list.size(), mappingName), descr);
      return list.get(0);
    }

    return (SNode) o;
  }

  public List<SNode> findAllOutputNodesByInputNodeAndMappingName(SNode inputNode, String mappingName) {
    if (mappingName == null) {
      return null;
    }
    Map<SNode, Object> currentMapping = myMappingNameAndInputNodeToOutputNodeMap.get(mappingName);
    if (currentMapping == null) {
      return null;
    }
    Object o = currentMapping.get(inputNode);
    if (o == null) {
      return Collections.emptyList();
    }
    if (o instanceof List) {
      return ((List<SNode>) o);
    }
    return Collections.singletonList((SNode) o);
  }

  public SNode findCopiedOutputNodeForInputNode(@NotNull SNode inputNode) {
    Object o = myCopiedOutputNodeForInputNode.get(inputNode);
    if (o instanceof SNode) {
      return (SNode) o;
    }
    if (o instanceof List) {
      return (SNode) ((List) o).get(0);
    }
    return null;
  }

  public SNode findOutputNodeByInputAndTemplateNode(SNode inputNode, String templateNodeId) {
    return myTemplateNodeIdAndInputNodeToOutputNodeMap.get(new Pair<>(templateNodeId, inputNode));
  }

  public boolean isInputNodeHasUniqueCopiedOutputNode(SNode inputNode) {
    Object o = myCopiedOutputNodeForInputNode.get(inputNode);
    return !(o instanceof List);
  }

  @Nullable
  public SNode findNewOutputNode(@Nullable String mappingLabel) {
    if (mappingLabel == null) {
      // all other methods tolerate null parameters, why this one would not?
      return null;
    }
    return myConditionalRoots.stream().filter(p -> mappingLabel.equals(p.o1)).findFirst().map(p -> p.o2).orElse(null);
  }

  // expose internal structure, to build GeneratorDebug_Mappings with MPS-coded DebugMappingsBuilder
  /*package*/ Collection<String> getAvailableLabels() {
    return myMappingNameAndInputNodeToOutputNodeMap.keySet();
  }

  // FIXME please, no Object, no Map<SNode,Object>. PLEASE!!!
  /*package*/Map<SNode,Object> getMappings(String label) {
    return myMappingNameAndInputNodeToOutputNodeMap.get(label);
  }
  /*package*/List<SNode> getSortedMappingKeys(String label) {
    ArrayList<Map.Entry<SNode, Object>> l = new ArrayList<>(myMappingNameAndInputNodeToOutputNodeMap.get(label).entrySet());
    Collections.sort(l, new Comparator<Entry<SNode, Object>>() {
      private final ToStringComparator myToStringComparator = new ToStringComparator();
      @Override
      public int compare(Entry<SNode, Object> o1, Entry<SNode, Object> o2) {
        int v = compareKeys(o1.getKey(), o2.getKey());
        if (v == 0) {
          // input node is the same (or indistinguishable), have to respect values to keep order consistent.
          // E.g. a pattern in a typesystem rule is copied to to different methods, there are two output classes
          // Pattern_nn7be_a0a0a0b0d and Pattern_nn7be_a0a0a0c, and label entry order is inconsistent
          return String.valueOf(o1.getValue()).compareTo(String.valueOf(o2.getValue()));
        }
        return v;
      }

      private int compareKeys(SNode n1, SNode n2) {
        int v = n1.getPresentation().compareTo(n2.getPresentation());
        if (v == 0) {
          // just in case presentation is the same (e.g. enumeration literals with the same name in different enums)
          // Hope, there could be no two nodes with the same presentation and same node id.
          SNodeReference o1, o2;
          do {
            o1 = TracingUtil.getInput(n1);
            o2 = TracingUtil.getInput(n2);
            n1 = n1.getParent();
            n2 = n2.getParent();
            // original input node is recorded for a top template node only, if a child template node serves as a ML key, we can't
            // find its input here unless traverse to parent.
            // highlevel sample: 'Car' is transformed with map_EditorDeclaration to ConceptEditorDeclaration with child CellModel_Collection, that
            // later serves as a key for 'cellFactory.factoryMethod' ML. Presentation for any CellModel_Collection is 'collection', and as long as it's
            // child template node of map_EditorDeclaration template, it doesn't get original input value (map_EditorDeclaration does).
          } while (o1 == null && o2 == null && n1 != null && n2 != null);

          if (o1 != null && o2 != null) {
            // use original input, if possible - actual input is from transient model
            v = myToStringComparator.compare(o1.getNodeId(), o2.getNodeId());
          }
        }
        return v;
      }
    });
    return l.stream().map(Entry::getKey).collect(Collectors.toList());
  }

  /*package*/Set<String> getConditionalRootLabels() {
    return myConditionalRoots.stream().map(p -> p.o1).collect(Collectors.toSet());
  }
  /*package*/List<SNode> getConditionalRoots(String label) {
    return myConditionalRoots.stream().filter(p -> label.equals(p.o1)).map(p -> p.o2).collect(Collectors.toList());
  }

  // serialization

  /**
   * Record MLs into checkpoint state, assuming output nodes of the mappings are from the model being marked as 'checkpoint',
   * and input nodes being traced with transitionTrace
   */
  public void export(CheckpointStateBuilder cp) {
    for (Entry<String, Map<SNode, Object>> o : myMappingNameAndInputNodeToOutputNodeMap.entrySet()) {
      String label = o.getKey();
      for (Entry<SNode, Object> i : o.getValue().entrySet()) {
        SNode inputNode = i.getKey();
        if (inputNode == null) {
          // FIXME shall I track nodes newly introduced at the given checkpoint step? Yes.
          //       However, for newly introduced nodes we keep separate collection, and there should be no
          //       null input nodes in this map. The check left just in case there's one (no check for null input in addOutput.. yet).
          continue;
        }
        // perhaps, would be useful to record mappings even without original node (marked as 'useless')
        // to ease debug (once there's mechanism to show mapping labels as part of transient model/module)
        Object value = i.getValue();
        if (value instanceof SNode) {
          cp.record(inputNode, label, (SNode) value);
        } else if (value instanceof Collection) {
          @SuppressWarnings("unchecked")
          Collection<SNode> collection = (Collection<SNode>) value;
          cp.record(inputNode, label, collection);
        }
      }
    }
    myConditionalRoots.forEach(p -> cp.record(p.o1, p.o2));
  }
}
