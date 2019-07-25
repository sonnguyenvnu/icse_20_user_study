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
package jetbrains.mps.errors;

import jetbrains.mps.checkers.AbstractNodeCheckerInEditor;
import jetbrains.mps.checkers.ConstraintsChecker;
import jetbrains.mps.checkers.IChecker;
import jetbrains.mps.checkers.SuppressErrorsChecker;
import jetbrains.mps.checkers.RefScopeChecker;
import jetbrains.mps.checkers.TargetConceptChecker;
import jetbrains.mps.checkers.UsedLanguagesChecker;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.errors.item.IssueKindReportItem.CheckerCategory;
import jetbrains.mps.util.containers.MultiMap;

import java.util.ArrayList;
import java.util.List;

public final class CheckerRegistry implements CoreComponent {

  private MultiMap<CheckerCategory, IChecker<?, ?>> myCheckers;
  private MultiMap<CheckerCategory, AbstractNodeCheckerInEditor> myEditorCheckers;

  public CheckerRegistry() {
  }

  @Override
  public void init() {
    myCheckers = new MultiMap<>();
    myEditorCheckers = new MultiMap<>();
    registerCoreCheckers();
  }

  @Override
  public void dispose() {
    myCheckers.clear();
    myEditorCheckers.clear();
  }

  private void registerCoreCheckers() {
    registerChecker(new ConstraintsChecker());
    registerChecker(new TargetConceptChecker());
    registerChecker(new UsedLanguagesChecker());
    registerChecker(new RefScopeChecker());
    registerChecker(new SuppressErrorsChecker());
  }

  public void registerChecker(IChecker<?, ?> checker) {
    myCheckers.putValue(checker.getCategory(), checker);
  }

  public void unregisterChecker(IChecker<?, ?> checker) {
    myCheckers.removeValue(checker.getCategory(), checker);
  }

  public void registerEditorChecker(AbstractNodeCheckerInEditor checker) {
    myEditorCheckers.putValue(checker.getCategory(), checker);
  }

  public void unregisterEditorChecker(AbstractNodeCheckerInEditor checker) {
    myEditorCheckers.removeValue(checker.getCategory(), checker);
  }

  public List<IChecker<?, ?>> getCheckers() {
    return new ArrayList<>(myCheckers.values());
  }

  public List<AbstractNodeCheckerInEditor> getEditorCheckers() {
    ArrayList<AbstractNodeCheckerInEditor> result = new ArrayList<>(myEditorCheckers.values());
    for (IChecker<?, ?> checker: myCheckers.values()){
      if (checker instanceof AbstractNodeCheckerInEditor && myEditorCheckers.get(checker.getCategory()).isEmpty()) {
        result.add((AbstractNodeCheckerInEditor) checker);
      }
    }
    return result;
  }
}
