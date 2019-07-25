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
package jetbrains.mps.workbench.choose;

import jetbrains.mps.progress.EmptyProgressMonitor;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.workbench.goTo.navigation.GotoNavigationUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.persistence.NavigationParticipant.NavigationTarget;
import org.jetbrains.mps.openapi.persistence.NavigationParticipant.TargetKind;

import java.util.Iterator;

/**
 * Serves as a {@linkplain SearchScope}-backed source of {@link org.jetbrains.mps.openapi.persistence.NavigationParticipant.NavigationTarget}
 * for {@link ChooseByNameData}
 * @author Artem Tikhomirov
 * @since 3.5
 */
public class NavigationTargetScopeIterable implements Iterable<NavigationTarget> {
  private final SearchScope myScope;
  private SRepository myRepo;

  public NavigationTargetScopeIterable(@NotNull SearchScope scope, @NotNull SRepository repo) {
    myScope = scope;
    myRepo = repo;
  }

  @NotNull
  @Override
  public Iterator<NavigationTarget> iterator() {
    return new ModelAccessHelper(myRepo).runReadAction(() -> GotoNavigationUtil.getNavigationTargets(TargetKind.ROOT, myScope, new EmptyProgressMonitor())).iterator();
  }
}
