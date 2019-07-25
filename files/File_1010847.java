/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.make;

import com.intellij.facet.FacetManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.facet.MPSFacet;
import jetbrains.mps.idea.core.facet.MPSFacetType;
import jetbrains.mps.openapi.navigation.EditorNavigator;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.ModuleRepositoryFacade;
import jetbrains.mps.smodel.SNodePointer;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelNodeNavigatable implements Navigatable {

  private final String modelName;
  private final SNodeId nodeId;
  private final Project project;
  private final Module module;

  public ModelNodeNavigatable(String modelName, String nodeId, Project project, Module module) {
    this.modelName = modelName;
    this.nodeId = PersistenceFacade.getInstance().createNodeId(nodeId);
    this.project = project;
    this.module = module;
  }

  @Override
  public void navigate(final boolean requestFocus) {
    final jetbrains.mps.project.Project mpsProject = ProjectHelper.fromIdeaProject(project);
    final SModelReference mr = new ModelAccessHelper(mpsProject.getModelAccess()).runReadAction(() -> {
      SModel model = lookupModel();
      return (model == null) ? null : model.getReference();
    });
    if (mr != null) {
      new EditorNavigator(mpsProject).shallFocus(requestFocus).shallSelect(true).open(new SNodePointer(mr, nodeId));
    }
  }

  /**
   * Requires read action.
   *
   * @return model by name or {@code null}
   */
  public SModel lookupModel() {
    SRepository repository = ProjectHelper.getProjectRepository(project);
    assert repository.getModelAccess().canRead();
    SModel model = null;
    if (module != null) {
      MPSFacet facet = FacetManager.getInstance(module).getFacetByType(MPSFacetType.ID);
      for (SModel smd : facet.getSolution().getModels()) {
        if (smd.getName().getLongName().equals(modelName)) {
          model = smd;
        }
      }
    } else {
      model = new ModuleRepositoryFacade(repository).getModelByName(modelName);
    }
    return model;
  }

  @Override
  public boolean canNavigate() {
    return true;
  }

  @Override
  public boolean canNavigateToSource() {
    return true;
  }

  private static final Pattern TRANS_MODEL = Pattern.compile("\\[(\\d+)\\].*\\s([a-zA-Z_][a-zA-Z_0-9.]*)@(\\d+_\\d+)");
  private static final Pattern SOURCE_MODEL = Pattern.compile("\\[(\\d+)\\].*\\sin\\s+([a-zA-Z_][a-zA-Z_0-9.]*)");


  public static ModelNodeNavigatable extractNavigatable(String errorMsg, Project project, Module module) {
    if (errorMsg == null) return null;

    Matcher matcher = TRANS_MODEL.matcher(errorMsg);
    if (!matcher.find()) {
      matcher = SOURCE_MODEL.matcher(errorMsg);
      if (!matcher.find()) return null;
    }

    String nodeId = matcher.group(1);
    String modelName = matcher.group(2);
    return new ModelNodeNavigatable(modelName, nodeId, project, module);
  }

}
