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

import jetbrains.mps.generator.plan.CheckpointIdentity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Trace transformation of active model as it transitions from one CP to another.
 *
 * ModelTransitions with TransitionTrace pair is somewhat similar to ModelCheckpoints with CheckpointState, the former tracking
 * active transformation sequence, while latter representing recorded transformation sequence. Indeed, for the active transformation,
 * there might me partially ready recorded one, and perhaps we can reuse ModelCheckpoints. However, at the moment, access to ModelCheckpoints
 * object from CrossModelEnvironment is focused on load/restore of persisted state, and I don't want to intervene there right now. Besides,
 * for the scenario at hand, we do not really need complete CheckpointState, just a reference to checkpoint model.
 * @author Artem Tikhomirov
 * @since 3.4
 */
public final class ModelTransitions {
  private TransitionTrace myActiveTransition;
  private List<CheckpointIdentity> myCheckpoints = new ArrayList<>(5);

  public ModelTransitions() {
  }

  /**
   * indicates we start at the given checkpoint, so that any future changes to the model treat this checkpoint as start/origin
   * @param checkpoint last recorded checkpoint, or null if it's transformation of initial (i.e. not necessarily the @0 one, just no CP yet) model
   * @param transformationModel transient model with nodes deemed 'origin' of the checkpoint (we record their node identities as 'origins')
   * @param changedNodes map to translate identities of nodes in transient model to that of checkpoint model (nodes in CP models are 're-numbered'
   *                     to keep CP models stable between regenerations.
   */
  public void newTransition(@Nullable CheckpointIdentity checkpoint, @NotNull SModel transformationModel, @Nullable Map<SNodeId, SNodeId> changedNodes) {
    myActiveTransition = checkpoint == null ? new TransitionTrace(this) : new TransitionTrace(checkpoint, this);
    myActiveTransition.reset(transformationModel, changedNodes == null ? Function.identity() : nid -> changedNodes.getOrDefault(nid, nid));
    if (checkpoint != null) {
      myCheckpoints.add(checkpoint);
    }
  }

  /**
   * as long as TransitionTrace keep its values as user objects, we don't really need checkpointModel as we are not going to read it anyway,
   * keep it here just to keep API impression (provided we may want to use other mechanism than user objects to keep origin->transformed
   * mapping, e.g. as a distinct explicit map. Still, we'd likely need smth more than just a model, i.e. smth we can keep this map in)
   */
  public TransitionTrace loadTransition(@NotNull CheckpointIdentity checkpoint, @NotNull SModel checkpointModel) {
    myActiveTransition = new TransitionTrace(checkpoint, this);
    new TransitionTracePersistence(checkpointModel).load(myActiveTransition);
    return myActiveTransition;
  }

  @NotNull
  public TransitionTrace getActiveTransition() {
    assert myActiveTransition != null;
    return myActiveTransition;
  }

  /**
   * @return identity of a checkpoint active transition had started from, or {@code null} if it is the very first one, started from original model
   */
  @Nullable
  public CheckpointIdentity getMostRecentCheckpoint() {
    return myCheckpoints.isEmpty() ? null : myCheckpoints.get(myCheckpoints.size() - 1);
  }

  /**
   * @return recorded snapshot of model transition states so that we can use new object for another plan branch
   */
  public ModelTransitions fork() {
    if (myActiveTransition == null) {
      throw new IllegalStateException("How come I need to fork when there's no ongoing transformation?");
    }
    ModelTransitions rv = new ModelTransitions();
    rv.myCheckpoints.addAll(myCheckpoints);
    CheckpointIdentity recentCheckpoint = getMostRecentCheckpoint();
    rv.myActiveTransition = recentCheckpoint == null ? new TransitionTrace(rv) : new TransitionTrace(recentCheckpoint, rv);
    return rv;
  }
}
