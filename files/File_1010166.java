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
package jetbrains.mps.generator.impl.plan;

import jetbrains.mps.generator.ModelGenerationPlan.Checkpoint;
import jetbrains.mps.generator.plan.CheckpointIdentity;
import jetbrains.mps.smodel.ModelImports;
import jetbrains.mps.util.CollectionUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * All checkpoint models known for (associated with) the given original model.
 * @author Artem Tikhomirov
 * @since 3.3
 */
public class ModelCheckpoints {
  // modifiable list (set, iterate+remove operations)
  private final List<CheckpointState> myStates;

  /**
   * @param state not null
   */
  /*package*/ ModelCheckpoints(CheckpointState state) {
    myStates = new ArrayList<>(2);
    myStates.add(state);
  }

  /**
   * @param states not null
   */
  /*package*/ ModelCheckpoints(Collection<CheckpointState> states) {
    // XXX shall I assert all states are for the same original model?
    // FIXME shall I assert no duplicated states (model for same cp)?
    myStates = new ArrayList<>(states); // copy
  }


  /**
   * Retrieve state that corresponds to transition between specified checkpoints.
   * Starting checkpoint is optional, original input model is assumed to be 'checkpoint' in this case.
   * FIXME perhaps, shall have a distinct Checkpoint to indicate initial model (don't want to keep the model in transients, though
   * and OTOH don't want to tell whether there's a model for Checkpoint based on special Checkpoint instance.
   * @return <code>null</code> if no saved state for the point found
   */
  @Nullable
  public CheckpointState find(@NotNull Checkpoint targetPoint) {
    return find(targetPoint.getIdentity());
  }

  @Nullable
  public CheckpointState find(@NotNull CheckpointIdentity tp) {
    for (CheckpointState cps : myStates) {
      if (cps.getCheckpoint().equals(tp)) {
        return cps;
      }
    }
    return null;
  }

  @Nullable
  /*package*/ CheckpointState findStateWith(SModel model) {
    for (CheckpointState cps : myStates) {
      if (cps.getCheckpointModel().equals(model)) {
        return cps;
      }
    }
    return null;
  }


  /**
   * Two models are transformed independently and their generation plans not necessarily identical. The moment we'd like to get output
   * for a certain target node, the node may belong to any earlier checkpoint of a target model, not necessarily immediately preceding one.
   * Most common scenario is that target node (here, inputNode) points to original model, M2, which has gone through few transformation steps,
   * CP1->CP2->CP3, and we'd like to get CP3 output given original M2 node. CP3 records its inputs as known at CP2, and we need to traverse
   * back to CP1 and original model to find out matching node, and then traverse back, assuming node was merely copied between CP1 and CP2.
   *
   * FIXME In fact, need both CheckpointState for tp and SNode as return values, perhaps worth extracting into a separate class which keeps both
   *       values as internal state, rather than obtaining it twice. Besides, separate class may hide assumption inputNode comes from same model
   *       this object has been constructed for.
   *
   * @param tp checkpoint we'd like to get copied output for
   * @param inputNode a node from one of previous checkpoints (including original model). This {@code {@link ModelCheckpoints} has to be
   *        obtained for the node's model.
   * @return outputNode a node in CP identified by {@code tp} representing a copy of supplied {@code inputNode}
   */
  @Nullable
  public SNode findCopiedNode(@NotNull CheckpointIdentity tp, @NotNull SNode inputNode) {
    // Given M1 and M2, where M1 hosts reference source and M2 is home for reference target, and cp sequence CP1->CP3 for M1, CP1->CP2->CP3 sequence for M2,
    // we need to synchronize @CP3 provided M1 still points to M2@CP1, while M2@CP3 records nodes M2@CP2 as their inputs which do not match nodes of M2@CP1
    // Therefore, we need to walk M2 CP sequence backwards up to CP1, match input and then re-construct transitions forward to CP3.
    // FIXME perhaps, could make use of myPlanStep.getLastCheckpoint() not to get beyond that CP. However, need further investigation
    //       regarding this, as there's no confidence why would both M1 and M2 share lastCheckpoint (CP3 might be the only one they share and synchronize at)
    CheckpointIdentity cpId = tp;
    ArrayDeque<CheckpointState> cpStateStack = new ArrayDeque<>();
    SNode copiedOutput;
    do {
      CheckpointState cp = find(cpId);
      if (cp == null) {
        // can hardly do anything if we can't trace CPs
        return null;
      }
      cpStateStack.push(cp);
      copiedOutput = cp.getCopiedOutput(inputNode);
      cpId = cp.getOriginCheckpoint();
    } while (copiedOutput == null && cpId != null);
    if (copiedOutput == null) {
      // we traced all known CPs of M2 and didn't find any that has a copied output for inputNode, alas
      return null;
    }
    // at the top of the cpStateStack is CP state with the inputNode->copiedOutput record, discard as we already know copiedOutput node,
    // i.e. CP1,CP2,CP3, and we discard CP1.
    cpStateStack.pop();
    // now, unwind the stack to get to output in 'targetPoint' (aka CP3)
    while (!cpStateStack.isEmpty() && copiedOutput != null) {
      CheckpointState cp = cpStateStack.pop();
      copiedOutput = cp.getCopiedOutput(copiedOutput);
    }
    return copiedOutput;
  }

  /**
   * With {@code inputNode} coming from one of CP previous to the supplied one, try to find a copy of an input node in a preceding CP,
   * use it as a 'proper' input to retrieve transformed value, and trace this value, if any, up to the target CP.
   * @param tp target checkpoint
   * @param inputNode
   * @param mappingLabel
   * @return output node from checkpoint model {@code tp} of a labeled transformation, or its copy in there, if any.
   */
  @Nullable
  public SNode findTransformedNode(@NotNull CheckpointIdentity tp, @NotNull SNode inputNode, String mappingLabel) {
    // general case, inputNode, perhaps copied, was transformed with ML at an earlier transition, and copied up to the target (tp) one
    // X -> X' (copied); CP1; X ' => Y as ML1; CP2; Y -> Y' (copied); CP3
    // and we are between CP2 and CP3 (==tp) with a reference to X, eager to get Y'
    CheckpointIdentity cpId = tp;
    // stack of states we would need to look for copies in case we find Y (labeled transformation output) in some earlier phase
    ArrayDeque<CheckpointState> cpStateStack = new ArrayDeque<>();
    SNode output = null;
    SNode copiedInput = null;
    do {
      CheckpointState cp = find(cpId);
      if (cp == null) {
        // can hardly do anything if we can't trace CPs
        // FIXME shall I report missing checkpoint for a target model?
        return null;
      }
      cpStateStack.push(cp);
      output = cp.getOutputIfSingle(mappingLabel, inputNode);
      copiedInput = cp.getCopiedOutput(inputNode);
      cpId = cp.getOriginCheckpoint();
    } while (cpId != null && output == null && copiedInput == null);
    // Three major scenarios to expect (cpId could be null, it just means we've got to the very first transition):
    // (I) output == null && copiedInput != null (II) output != null && copiedInput == null (III) output == null && copiedInput == null
    if (output == null && copiedInput == null) {
      // (III), nothing I could do here, fail fast
      return null;
    }
    // Now we've got [CP1 top, CP2, CP3 bottom] in the stack and copiedInput X' from CP1
    assert cpStateStack.size() > 0; // while/push has been executed at least once
    // As CP1 is the last one we asked for, we don't need to ask again, remove it
    cpStateStack.pop();
    // I suspect it's possible to get (IV) both copiedInput != null && output != null, e.g. if I LABEL a node and copy it, preserving id somehow,
    // but didn't check, and look at this as output != null case.
    // (I) scenario. Break with the first CPS we find output at.
    while (!cpStateStack.isEmpty() && output == null && copiedInput != null) {
      CheckpointState cp = cpStateStack.pop();
      output = cp.getOutputIfSingle(mappingLabel, copiedInput);
      // perhaps, it's an extra step and there are further copies, X' -> X'' -> X''' ==> Y
      // therefore, we need to walk stack for copies
      copiedInput = cp.getCopiedOutput(copiedInput);
    }
    // if output == null, we are screwed, X' was never translated to Y with ML1
    //
    // unwind cpStateStack, taking copies for output node
    while (!cpStateStack.isEmpty() && output != null) {
      output = cpStateStack.pop().getCopiedOutput(output);
    }
    return output;
  }

  /*package*/ List<CheckpointIdentity> getKnownCheckpoints() {
    return StreamSupport.stream(myStates.spliterator(), false).map(CheckpointState::getCheckpoint).collect(Collectors.toList());
  }

  /**
   * we've got new state for some checkpoint
   * @param state not null
   * @return stale checkpoint (the one that has been discarded), if any.
   */
  /*package*/ CheckpointState updateAndDiscardOutdated(CheckpointState state) {
    for (int i = 0; i < myStates.size(); i++) {
      CheckpointState cps = myStates.get(i);
      if (!cps.getCheckpoint().equals(state.getCheckpoint())) {
        continue;
      }
      myStates.set(i, state);
      return cps;
    }
    // there's no matching state, record a new one
    myStates.add(state);
    return null;
  }

  /**
   * Once there's a discarded checkpoint state, we need to update (discard) states that used to reference it.
   * @param outdatedModels models that are no longer valid
   * @param discarded collection with stale states to update
   */
  /*package*/ void discardOutdated(Collection<SModelReference> outdatedModels, Collection<CheckpointState> discarded) {
    for (Iterator<CheckpointState> it = myStates.iterator(); it.hasNext(); ) {
      CheckpointState next = it.next();
      if (CollectionUtil.intersects(new ModelImports(next.getCheckpointModel()).getImportedModels(), outdatedModels)) {
        discarded.add(next);
        it.remove();
      }
    }
  }
}
