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
package jetbrains.mps.lang.dataFlow.framework;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

/**
 * @author simon
 */
public abstract class DataFlowAnalyzerBase<E> implements DataFlowAnalyzer<E> {
  @Override
  public abstract E initial(Program p);

  @Override
  public abstract E merge(Program p, List<E> input);

  @Override
  public E fun(E input, ProgramState s) {
    return fun(input, s, null);
  }

  public abstract E fun(E input, ProgramState s, @Nullable Map<ProgramState, E> stateValues);

  @Override
  public abstract AnalysisDirection getDirection();
}
