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
package jetbrains.mps.workbench.goTo.navigation;

import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.project.Project;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.persistence.NavigationParticipant.NavigationTarget;

/**
 * Bridges OpenAPI {@link NavigationTarget} with IDEA's {@link NavigationItem}
 * The only use left is one in {@code jetbrains.mps.idea.core.navigation.MPSIdeaGotoClassContributor}, where {@link NavigationItem} is
 * needed for a {@link NavigationTarget}. Though use of {@link jetbrains.mps.workbench.choose.NodePointerNavigationItem} is possible,
 * need to deal with {@link #navigate(boolean)} implementation anyway, so this class persists.
 * XXX Perhaps, shall relocate next to its only client and become package-local?
 * <p/>
 * Generally, it's a bad idea to associate particular behavior (here, navigation) with a data model for element chooser, but AFAIK
 * with {@link com.intellij.navigation.GotoClassContributor} there's no other way but to handle navigation here.
 */
public final class RootNodeElement implements NavigationItem {
  private final Project myProject;
  private final NavigationTarget myTarget;

  public RootNodeElement(Project mpsProject, NavigationTarget target) {
    myProject = mpsProject;
    myTarget = target;
  }

  /*package*/ NavigationTarget getTarget() {
    return myTarget;
  }

  @Override
  public String getName() {
    return myTarget.getPresentation();
  }

  @Override
  public ItemPresentation getPresentation() {
    return new SNodeDescriptorPresentation(myTarget);
  }

  public SModelReference getModel() {
    return myTarget.getNodeReference().getModelReference();
  }

  @Override
  public void navigate(boolean requestFocus) {
    new EditorNavigator(myProject).shallFocus(requestFocus).selectIfChild().open(myTarget.getNodeReference());
  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public boolean canNavigateToSource() {
    return true;
  }
}
