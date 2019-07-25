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
package jetbrains.mps.ide.ui.tree.module;

import com.intellij.icons.AllIcons.Nodes;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.ui.tree.MPSTreeNode;
import jetbrains.mps.ide.ui.tree.SortUtil;
import jetbrains.mps.ide.ui.tree.TextTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelReferenceTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode.LongModelNameText;
import jetbrains.mps.ide.ui.tree.smodel.SModelTreeNode.ShortModelNameText;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.Generator;
import jetbrains.mps.smodel.Language;
import jetbrains.mps.smodel.language.LanguageAspectSupport;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;

import java.util.List;

public class ProjectLanguageTreeNode extends ProjectModuleTreeNode {
  private Project myProject;
  private boolean myShortNameOnly;
  private boolean myInitialized;

  protected ProjectLanguageTreeNode(@NotNull Language language, Project project, boolean shortNameOnly) {
    super(language);
    myShortNameOnly = shortNameOnly;
    myProject = project;
    setNodeIdentifier(language.getModuleReference().toString());
    setIcon(IdeIcons.LANGUAGE_ICON);
  }

  @Override
  public boolean isInitialized() {
    return myInitialized;
  }

  @Override
  protected void doInit() {
    ModuleNodeChildrenProvider childrenProvider = getAncestor(ModuleNodeChildrenProvider.class);
    if (childrenProvider == null || !childrenProvider.populate(this, getModule())) {
      populate();
    }
    myInitialized = true;
  }

  @NotNull
  @Override
  public Language getModule() {
    return (Language) super.getModule();
  }

  @Override
  public String getModuleText() {
    String languageUID = getModule().getModuleName();

    if (myShortNameOnly) {
      languageUID = NameUtil.shortNameFromLongName(languageUID);
    }

    return languageUID;
  }

  private void populate() {
    ShortModelNameText textSource = new ShortModelNameText();
    for (SModel m : LanguageAspectSupport.getAspectModels(getModule())) {
      add(new SModelTreeNode(m, textSource));
    }

    // language accessory models
    if (getModule().getAccessoryModels().size() > 0) {
      TextTreeNode accessories = new AccessoriesModelTreeNode(this);

      List<SModel> sortedModels = SortUtil.sortModels(getModule().getAccessoryModels());
      LongModelNameText modelText = new LongModelNameText();
      for (SModel model : sortedModels) {
        SModule m = model.getModule();
        boolean currentModule = m == null || m == getModule();
        if (!currentModule) {
          accessories.add(new SModelReferenceTreeNode(model, myProject));
        } else {
          accessories.add(new SModelTreeNode(model, modelText));
        }
      }
      this.add(accessories);
    }

    for (Generator generator : getModule().getOwnedGenerators()) {
      MPSTreeNode generatorNode = createFor(myProject, generator);
      add(generatorNode);
    }

    if (!getModule().getRuntimeModulesReferences().isEmpty()) {
      TextTreeNode languageRuntime = new RuntimeModulesTreeNode();
      for (SModuleReference mr : getModule().getRuntimeModulesReferences()) {
        languageRuntime.add(new SModuleReferenceTreeNode(mr, myProject));
      }
      add(languageRuntime);
    }

    if (getModule().getUtilModels().size() > 0) {
      TextTreeNode utilModels = new SModelGroupTreeNode();
      new SModelsSubtree(utilModels).create(getModule().getUtilModels());
      this.add(utilModels);
    }

    TextTreeNode allModels = new AllModelsTreeNode();
    allModels.setIcon(Nodes.ModuleGroup);
    new SModelsSubtree(allModels, false, false).create(getModule());
    add(allModels);
  }

  /**
   * need a dedicated instance as long as ProjectTreeFindHelper uses instanceof to decide whether to descend a node or not.
   */
  public static class AllModelsTreeNode extends TextTreeNode {
    /*package*/ AllModelsTreeNode() {
      super("all models");
    }
  }
}
