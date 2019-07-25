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
package jetbrains.mps.generator.impl.plan;

import jetbrains.mps.generator.impl.MappingLabelExtractor;
import jetbrains.mps.generator.impl.ModelTransitions;
import jetbrains.mps.generator.impl.TransitionTrace;
import jetbrains.mps.generator.impl.cache.MappingsMemento;
import jetbrains.mps.generator.plan.CheckpointIdentity;
import jetbrains.mps.smodel.BaseFastNodeFinder;
import jetbrains.mps.smodel.FastNodeFinder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Keep information about mapping labels known at checkpoint step.
 *
 * CheckpointState represents state at a given Checkpoint. Model-wide state that records states for all checkpoints
 * of the given model is accessible though {@link ModelCheckpoints}
 *
 * @author Artem Tikhomirov
 * @since 3.3
 */
@Immutable
public class CheckpointState {
  private final MappingsMemento myState;
  private final SModel myCheckpointModel;
  private final CheckpointIdentity myPrevCheckpoint;
  private final CheckpointIdentity myCheckpoint;
  private FastNodeFinder myCheckpointModelLookup;
  private TransitionTrace myCheckpointModelTransitionTrace;

  public CheckpointState(@NotNull SModel checkpointModel, @Nullable CheckpointIdentity prevCheckpoint, @NotNull CheckpointIdentity cp) {
    // FIXME read and fill memento with MappingLabels
    //       now, just restore it from debug root we've got there. Later (once/if true persistence is done), shall consider
    //       option to keep mappings inside a model (not to bother with persistence) or to follow MappingsMemento approach with
    //       custom serialization code (and to solve the issue of associated model streams serialized/managed (i.e. deleted) along with a cp model)
    myState = new MappingLabelExtractor().restore(MappingLabelExtractor.findDebugNode(checkpointModel));
    myCheckpointModel = checkpointModel;
    myPrevCheckpoint = prevCheckpoint;
    myCheckpoint = cp;
  }

  public SModel getCheckpointModel() {
    return myCheckpointModel;
  }

  /**
   * @return identity of a checkpoint for the {@linkplain #getCheckpointModel() model} kept in this state.
   */
  public CheckpointIdentity getCheckpoint() {
    return myCheckpoint;
  }

  /**
   * @return identity of a checkpoint model that served as an input to get this state's checkpoint model, or {@code null} if there were
   *         no checkpoints during transformation prior to {@link #getCheckpoint() state's checkpoint} (i.e. original model served as an input)
   */
  @Nullable
  public CheckpointIdentity getOriginCheckpoint() {
    return myPrevCheckpoint;
  }

  @NotNull
  public Collection<String> getMappingLabels() {
    // FIXME wrap it (ML + inputs + outputs) into an object like MapLabelState, with getLabel(), getInputs() and getOutput(input).
    // MapLabelState.getInput may return object (LabelAssociatedValues), which in turn has getOutput()
    return myState.getMappingNameAndInputNodeToOutputNodeMap().keySet();
  }

  /*package*/ Collection<SNodeId> getInputs(String mappingLabel) {
    Map<SNodeId, Object> values = myState.getMappingNameAndInputNodeToOutputNodeMap().get(mappingLabel);
    assert values != null; // provided getMappingLabels().contains(mappingLabel)
    return values.keySet();
  }

  /**
   * @param input reference target that generally belongs to a preceding checkpoints (not necessarily immediate
   *              {@linkplain #getOriginCheckpoint() origin CP}, though).
   *              In case model of input node is separated from CP of this state object by few other CPs, it's caller
   *              responsibility to walk them backwards.
   *              IMPORTANT: it's assumed this CP state is of the input node's model, i.e.
   *              {@code crossModelEnv.getState(input.getModel()).find(==getCheckpoint())}
   * @return {@code null} if there's no node in this CP that records given node as it's origin.
   */
  @Nullable
  public SNode getCopiedOutput(SNode input) {
    // first, try if node copied with its id preserved
    final SNodeId inputNodeId = input.getNodeId();
    SNode candidate = getCheckpointModel().getNode(inputNodeId);
    if (candidate != null && candidate.getConcept().equals(input.getConcept())) {
      return candidate;
    }
    if (myCheckpointModelLookup == null) {
      myCheckpointModelLookup = new BaseFastNodeFinder(getCheckpointModel());
      // FIXME Likely, shall not mix (ModelTransitions->TransitionTrace) into  (ModelCheckpoints->CheckpointState) as they are for different execution lines
      //       (one is for checkpoints of active transformation, another to access saved checkpoints). OTOH, TransitionTrace is a nice abstraction, why
      //       CheckpointState could not use it? See {@link TransitionTrace} javadoc.
      myCheckpointModelTransitionTrace = new ModelTransitions().loadTransition(getCheckpoint(), getCheckpointModel());
    }
    // XXX this is dubious approach, implemented just for investigation purposes
    //     Likely, we shall keep information about copied nodes inside MM or its replacement, rather than walk nodes and match by id.
    return myCheckpointModelLookup.getNodes(input.getConcept(), false).stream().filter(n -> inputNodeId.equals(myCheckpointModelTransitionTrace.getOrigin(n))).findFirst().orElse(null);
  }

  @NotNull
  public Collection<SNode> getOutput(String mappingLabel, SNode input) {
    Map<SNodeId, Object> values = myState.getMappingNameAndInputNodeToOutputNodeMap().get(mappingLabel);
    assert values != null; // provided getMappingLabels().contains(mappingLabel)
    Object outputNodes = values.get(input.getNodeId());
    if (outputNodes instanceof Collection) {
      return resolve((Collection<SNodeId>) outputNodes);
    } else if (outputNodes instanceof SNodeId) {
      return resolve(Collections.singleton((SNodeId) outputNodes));
    }
    return Collections.emptyList();
  }

  @Nullable
  public SNode getOutputIfSingle(String mappingLabel, SNode input) {
    // FIXME move the check outside of this code, and don't use this method at all.
    // ModelCheckpoints.findTransformedNode shall fail if ML present and there are multiple outputs.
    if (!myState.getMappingNameAndInputNodeToOutputNodeMap().containsKey(mappingLabel)) {
      return null;
    }
    Collection<SNode> output = getOutput(mappingLabel, input);
    if (output.size() == 1) {
      return output.iterator().next();
    }
    return null;
  }


  @NotNull
  public List<SNode> getOutputWithoutInput(String mappingLabel) {
    return resolve(myState.getNewOutputNodes(mappingLabel));
  }

  private List<SNode> resolve(Collection<SNodeId> output) {
    ArrayList<SNode> rv = new ArrayList<>(output.size());
    for (SNodeId id : output) {
      SNode node = myCheckpointModel.getNode(id);
      assert node != null : "provided SNodeId comes from getOutput() it's unreasonable to expect model misses the node";
      rv.add(node);
    }
    return rv;
  }
}
