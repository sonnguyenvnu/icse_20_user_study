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
package jetbrains.mps.generator.impl;

import jetbrains.mps.extapi.model.TransientSModel;
import jetbrains.mps.generator.GenerationTrace;
import jetbrains.mps.generator.TransientModelsModule;
import jetbrains.mps.smodel.SNodePointer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * @author Artem Tikhomirov
 */
public class GenTraceImpl implements GenerationTrace {
  private final TransientModelsModule myTransientModule;
  private Phase mySequence;
  private Phase myCurrent;

  public GenTraceImpl(@NotNull TransientModelsModule transientModule) {
    myTransientModule = transientModule;
  }

  @Override
  public void trace(@Nullable SNodeId input, @NotNull List<SNodeId> output, @NotNull SNodeReference templateNode) {
    myCurrent.add(input, output, templateNode);
  }

  @Override
  public void walkForward(SNode inputNode, @NotNull Visitor v) {
    Phase ph = findPhaseForInput(inputNode);
    if (ph == null) {
      return;
    }
    ArrayList<SNodeId> nodes = new ArrayList<>();
    final SNodeId startNodeId = inputNode.getNodeId();
    nodes.add(startNodeId);
    for (; ph != null && !nodes.isEmpty(); ph = ph.next) {
      Collection<Element> changes = ph.getChangesWithInput(nodes, myTransientModule);
      dispatch(v, ph, changes);
      if (!changes.isEmpty()) {
        LinkedHashSet<SNodeId> nextInputs = new LinkedHashSet<>();
        for (Element e : changes) {
          nextInputs.add(e.output);
        }
        nextInputs.add(startNodeId);
        nodes.clear();
        nodes.addAll(nextInputs);
      }
    }
  }

  @Override
  public void walkBackward(SNode node, @NotNull Visitor v) {
    Phase ph = findPhaseForOutput(node);
    if (ph == null) {
      // FIXME proceed with the last phase?
      return;
    }

    ArrayList<SNodeId> nodes = new ArrayList<>();
    final SNodeId startNodeId = node.getNodeId();
    nodes.add(startNodeId);
    for (; ph != null && !nodes.isEmpty(); ph = ph.prev) {
      Collection<Element> changes = ph.getChangesWithOutput(nodes, myTransientModule);
      dispatch(v, ph, changes);
      if (!changes.isEmpty()) {
        LinkedHashSet<SNodeId> prevOutputs = new LinkedHashSet<>();
        for (Element e : changes) {
          prevOutputs.add(e.input);
        }
        prevOutputs.add(startNodeId);
        nodes.clear();
        nodes.addAll(prevOutputs);
      }
    }
  }

  @Override
  public void nextStep(@NotNull SModelReference input, @NotNull SModelReference output) {
    if (mySequence == null) {
      mySequence = myCurrent = new Phase(input, output, null);
    } else {
      newTailPhase(input, output);
    }
  }

  @Override
  public void dropStep(@NotNull SModelReference input, @NotNull SModelReference output) {
    assert myCurrent != null;
    assert myCurrent.input.equals(input);
    myCurrent = myCurrent.prev;
    if (myCurrent == null) {
      mySequence = null;
    } else {
      myCurrent.next = null;
    }
  }

  private void newTailPhase(SModelReference in, SModelReference out) {
    assert myCurrent != null;
    myCurrent = new Phase(in, out, myCurrent);
    myCurrent.prev.next = myCurrent;
  }

  private Phase findPhaseForInput(SNode inputNode) {
    SModelReference mr = inputNode.getReference().getModelReference();
    for (Phase p = mySequence; p != null; p = p.next) {
      if (p.input.equals(mr)) {
        return p;
      }
    }
    if (inputNode.getModel() instanceof TransientSModel || inputNode.getModel() == null) {
      return null;
    }
    // we may start with node from original model (TransientModelsProvider gives GenTrace for SModelReference either of original or transient model)
    // in this case, start from the very first phase, and assume clone of original model into @0 kept node id.
    return mySequence;
  }

  private Phase findPhaseForOutput(SNode node) {
    SModelReference mr = node.getReference().getModelReference();
    for (Phase p = mySequence; p != null; p = p.next) {
      if (p.output.equals(mr)) {
        return p;
      }
    }
    return null;
  }

  static void dispatch(Visitor v, Phase ph, Collection<Element> changes) {
    v.beginStep(ph.input, ph.output);
    for (Element e : changes) {
      v.change(new SNodePointer(ph.input, e.input), new SNodePointer(ph.output, e.output), e.template);
    }
    v.endStep(ph.input, ph.output);
  }

  /*package*/ static final class Element {
    @Nullable
    public final SNodeId input;
    @Nullable
    public final SNodeId output;
    @NotNull
    public final SNodeReference template;

    public Element(@Nullable SNodeId in, @Nullable SNodeId out, @NotNull SNodeReference with) {
      input = in;
      output = out;
      template = with;
    }
  }

  /*package*/ static class Phase {
    private final ArrayList<Element> myTrace = new ArrayList<>();
    public Phase next;
    public final Phase prev;
    public final SModelReference input, output;
    private Map<SNodeId, Collection<Element>> inputIndex, outputIndex;
    private ConcurrentLinkedQueue<Element> myAdditionQueue = new ConcurrentLinkedQueue<>();
    private final Semaphore myTraceLock = new Semaphore(1);

    public Phase(@NotNull SModelReference inputModel, @NotNull SModelReference outputModel, @Nullable Phase previous) {
      input = inputModel;
      output = outputModel;
      prev = previous;
    }

    public void add(@Nullable SNodeId input, @NotNull List<SNodeId> output, @NotNull SNodeReference templateNode) {
      inputIndex = outputIndex = null;
      if (myTraceLock.tryAcquire()) {
        if (output.isEmpty()) {
          assert input != null : "Trace node deletion without input node?";
          myTrace.add(new Element(input, null, templateNode));
        } else {
          for (SNodeId n : output) {
            myTrace.add(new Element(input, n, templateNode));
          }
        }
        drainAdditionQueue();
        myTraceLock.release();
      } else {
        // do not block each thread reporting the trace, just memorize it in the concurrent queue
        // which will get drained by the thread which holds the lock already.
        for (SNodeId n : output) {
          myAdditionQueue.offer(new Element(input, n, templateNode));
        }
      }
    }

    public Collection<Element> getChangesWithInput(Iterable<SNodeId> inputs, TransientModelsModule transientModule) {
      buildIndex();
      return collectChanges(inputIndex, input, inputs, transientModule);
    }

    public Collection<Element> getChangesWithOutput(Iterable<SNodeId> outputs, TransientModelsModule transientModule) {
      buildIndex();
      return collectChanges(outputIndex, output, outputs, transientModule);
    }

    private static Collection<Element> collectChanges(Map<SNodeId, Collection<Element>> index, SModelReference model, Iterable<SNodeId> nodes, TransientModelsModule tm) {
      // FIXME In fact, shall not look outside of TransientModelsModule, however even if we move to separate repository for transient models,
      // it's ok to use SRepository here
      final SRepository modelRepository = tm.getRepository();
      final ArrayList<Element> rv = new ArrayList<>();
      for (SNodeId nid : nodes) {
        Collection<Element> changes = index.get(nid);
        if (changes == null || changes.isEmpty()) {
          SNode in = new SNodePointer(model, nid).resolve(modelRepository);
          if (in == null) {
            continue;
          }
          changes = findByAncestor(index, in);
        }
        rv.addAll(changes);
      }
      if (rv.isEmpty()) {
        return Collections.emptyList();
      }
      // tracer records output nodes, which means trace for outer rule/macro usually comes *after* trace for inner elements
      // with reverse here, we try to restore 'natural' order (as deemed by few our colleagues), starting from most distant
      // template element and down to most specific.
      Collections.reverse(rv);
      return rv;
    }

    private static Collection<Element> findByAncestor(Map<SNodeId, Collection<Element>> index, SNode node) {
      Collection<Element> rv;
      do {
        rv = index.get(node.getNodeId());
        node = node.getParent();
      } while (node != null && rv == null);
      return rv == null ? Collections.emptyList() : rv;
    }

    private void drainAdditionQueue() {
      while(!myAdditionQueue.isEmpty()) {
        myTrace.add(myAdditionQueue.poll());
      }
    }

    private void buildIndex() {
      if (inputIndex != null && outputIndex != null) {
        return;
      }
      // just in case there were trace elements after the queue was drained but the lock wasn't yet released.
      drainAdditionQueue();
      HashMap<SNodeId, Collection<Element>> index1 = new HashMap<>();
      HashMap<SNodeId, Collection<Element>> index2 = new HashMap<>();
      for (Element e : myTrace) {
        updateMap(e.input, e, index1);
        updateMap(e.output, e, index2);
      }
      inputIndex = index1;
      outputIndex = index2;
    }
    private static void updateMap(SNodeId nid, Element e, Map<SNodeId, Collection<Element>> map) {
      Collection<Element> c = map.get(nid);
      if (c == null) {
        map.put(nid, c = new ArrayList<>(5));
      }
      c.add(e);
    }
  }
}
