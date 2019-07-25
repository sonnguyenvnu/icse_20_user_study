/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.lang.dataFlow.framework;

import jetbrains.mps.lang.dataFlow.framework.instructions.*;
import jetbrains.mps.logging.Logger;
import org.apache.log4j.LogManager;

import java.util.*;
import java.util.Map.Entry;

public abstract class StructuralProgramBuilder<N> {
  private Program myProgram;
  protected final List<Runnable> myInvokeLater;
  protected final Map<N, Map<String, Integer>> myLabels;
  protected final InstructionBuilder instructionBuilder;
  private static final Logger LOG = Logger.wrap(LogManager.getLogger(StructuralProgramBuilder.class));
  private final ProgramBuilderContext myBuilderContext;

  public StructuralProgramBuilder(InstructionBuilder instructionBuilder, ProgramBuilderContext context) {
    this.myLabels = new HashMap<>();
    this.myInvokeLater = new ArrayList<>();
    this.instructionBuilder = instructionBuilder;
    myBuilderContext = context;
  }

  public StructuralProgramBuilder(InstructionBuilder instructionBuilder) {
    this(instructionBuilder, new ProgramBuilderContextImpl(Collections.emptyList()));
  }

  public StructuralProgramBuilder() {
    this(new InstructionBuilder());
  }

  public Program getProgram() {
    if (myProgram == null) {
      myProgram = createProgram();
    }
    return myProgram;
  }

  protected abstract void doBuild(N node);

  protected Program createProgram() {
    return new Program();
  }

  public Program buildProgram(N node) {
    build(node);

    for (Runnable r : myInvokeLater) {
      r.run();
    }

    getProgram().init();
    return getProgram();
  }

  public void build(N node) {
    getProgram().start(node);
    doBuild(node);
    getProgram().end(node);
  }

  public Position before(final N node) {
    return () -> getProgram().getStart(node);
  }

  public Position after(final N node) {
    return () -> getProgram().getEnd(node);
  }

  public int insertAfter(Instruction i) {
    return getProgram().indexOf(i) + 1;
  }

  public int insertBefore(Instruction i) {
    return getProgram().indexOf(i);
  }

  public Position label(final N node, final String label) {
    return () -> {
      if (!myLabels.containsKey(node) || !myLabels.get(node).containsKey(label)) {
        throw new RuntimeException("Can't find a label " + label + " for node " + node);
      }
      return myLabels.get(node).get(label);
    };
  }

  public void emitLabel(String label) {
    if (!myLabels.containsKey(getProgram().getCurrent())) {
      myLabels.put((N) getProgram().getCurrent(), new HashMap<>());
    }
    myLabels.get(getProgram().getCurrent()).put(label, getProgram().size());
  }

  protected void updateLabelsOnInsert(final int position) {
    for (Entry<N, Map<String, Integer>> labels : myLabels.entrySet()) {
      for (Entry<String, Integer> label : labels.getValue().entrySet()) {
        if (label.getValue() > position) {
          label.setValue(label.getValue() + 1);
        }
      }
    }
  }

  public void insertInstruction(Instruction instruction, int position) {
    updateLabelsOnInsert(position);
    getProgram().insert(instruction, position, false, false);
  }

  protected NopInstruction emitNopCommon() {
    return emitNopCommon(null);
  }

  protected NopInstruction emitNopCommon(String ruleNodeReference) {
    NopInstruction instruction = instructionBuilder.createNopInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    return instruction;
  }

  public void emitNop(final int insertPosition) {
    insertInstruction(emitNopCommon(null), insertPosition);
  }

  public void emitNop(final int insertPosition, String ruleNodeReference) {
    insertInstruction(emitNopCommon(ruleNodeReference), insertPosition);
  }

  public void emitNop() {
    getProgram().add(emitNopCommon(null));
  }

  public void emitNop(String ruleNodeReference) {
    getProgram().add(emitNopCommon(ruleNodeReference));
  }

  public void emitRead(Object var, String ruleNodeReference) {
    ReadInstruction instruction = instructionBuilder.createReadInstruction(ruleNodeReference, var);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitRead(Object var) {
    emitRead(var, null);
  }

  public void emitWrite(Object var, Object value, String ruleNodeReference) {
    WriteInstruction instruction = instructionBuilder.createWriteInstruction(ruleNodeReference, var, value);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitWrite(Object var, Object value) {
    emitWrite(var, value, null);
  }

  public void emitWrite(Object var, String ruleNodeReference) {
    emitWrite(var, null, ruleNodeReference);
  }

  public void emitWrite(Object var) {
    emitWrite(var, null, null);
  }

  public void emitRet(String ruleNodeReference) {
    RetInstruction instruction = instructionBuilder.createRetInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitRet() {
    emitRet(null);
  }

  public void emitJump(final Position position, String ruleNodeReference) {
    final JumpInstruction instruction = instructionBuilder.createJumpInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
    invokeLater(() -> {
      try {
        instruction.setJumpTo(position);
      } catch (DataflowBuilderException e) {
        LOG.warning("JumpTo instruction reference to outer node");
        instruction.getProgram().setHasOuterJumps(true);
      }
    });
  }

  public void emitJump(final Position position) {
    emitJump(position, null);
  }


  protected IfJumpInstruction emitIfJumpCommon(final Position position, String ruleNodeReference) {
    final IfJumpInstruction instruction = instructionBuilder.createIfJumpInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    invokeLater(() -> {
      try {
        instruction.setJumpTo(position);
      } catch (DataflowBuilderException e) {
        LOG.warning("IfJumpTo instruction reference to outer node");
        instruction.getProgram().setHasOuterJumps(true);
      }
    });
    return instruction;
  }

  public void emitIfJump(final Position position, String ruleNodeReference) {
    getProgram().add(emitIfJumpCommon(position, ruleNodeReference));
  }

  public void emitIfJump(final Position position) {
    getProgram().add(emitIfJumpCommon(position, null));
  }

  public void emitIfJump(final Position position, int insertPosition, String ruleNodeReference) {
    insertInstruction(emitIfJumpCommon(position, ruleNodeReference), insertPosition);
  }

  public void emitIfJump(final Position position, int insertPosition) {
    insertInstruction(emitIfJumpCommon(position, null), insertPosition);
  }

  public void emitTry(String ruleNodeReference) {
    TryInstruction instruction = instructionBuilder.createTryInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitTry() {
    emitTry(null);
  }

  public void emitFinally(String ruleNodeReference) {
    FinallyInstruction instruction = instructionBuilder.createFinallyInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitFinally() {
    emitFinally(null);
  }

  public void emitEndTry(String ruleNodeReference) {
    EndTryInstruction instruction = instructionBuilder.createEndTryInstruction(ruleNodeReference);
    onInstructionEmitted(instruction);
    getProgram().add(instruction);
  }

  public void emitEndTry() {
    emitEndTry(null);
  }

  public void addInstruction(Instruction instruction, Position position) {
    if (position == null) {
      getProgram().add(instruction);
    } else {
      insertInstruction(instruction, position.getPosition());
    }
  }

  protected void onInstructionEmitted(Instruction instruction) {
  }

  protected void invokeLater(Runnable r) {
    myInvokeLater.add(r);
  }

  public interface Position {
    int getPosition();
  }

  public boolean contains(Object o) {
    return getProgram().contains(o);
  }

  public List<Instruction> getInstructionsFor(Object o) {
    return getProgram().getInstructionsFor(o);
  }

  protected ProgramBuilderContext getBuilderContext() {
    return myBuilderContext;
  }
}
