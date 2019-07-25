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
package jetbrains.mps.ide.generator;

import jetbrains.mps.generator.CustomGenerationModuleFacet;
import jetbrains.mps.ide.icons.IdeIcons;
import jetbrains.mps.ide.ui.dialogs.properties.tabs.BaseTab;
import jetbrains.mps.project.MPSProject;
import jetbrains.mps.scope.VisibleDepsSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleFacet;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SearchScope;
import org.jetbrains.mps.openapi.ui.persistence.FacetTab;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * @author Artem Tikhomirov
 * @since 3.3
 */
class CustomGenerationTab extends BaseTab implements FacetTab {
  private final MPSProject myProject;
  private final CustomGenerationModuleFacet myModuleFacet;
  private GenPlanPickPanel myPlanPanel;

  public CustomGenerationTab(MPSProject mpsProject, CustomGenerationModuleFacet moduleFacet) {
    super("Custom generation", IdeIcons.GENERATOR_ICON, "Alternative generation process for models of the module");
    myProject = mpsProject;
    myModuleFacet = moduleFacet;
  }

  @Override
  public SModuleFacet getFacet() {
    return myModuleFacet;
  }

  @Override
  public void init() {
    JPanel p = new JPanel();
    // XXX For now, restrict to plan models from the visible modules, generally, shall allow from anywhere
    //     Don't initialize scope here (hence 'lazy') as there's no model read when tab is initialized
    LazyVisibleDepsScope scope = new LazyVisibleDepsScope(myProject.getRepository(), myModuleFacet.getModule());
    myPlanPanel = new GenPlanPickPanel(myProject, scope, "Custom generation plan");
    myPlanPanel.setPlanModel(myModuleFacet.getPlanModelReference());
    p.setLayout(new BorderLayout());
    p.add(myPlanPanel, BorderLayout.NORTH);
    setTabComponent(p);
  }

  @Override
  public boolean isModified() {
    if (myPlanPanel.getPlanModel() == null) {
      return myModuleFacet.getPlanModelReference() != null;
    } else {
      return !myPlanPanel.getPlanModel().equals(myModuleFacet.getPlanModelReference());
    }
  }

  @Override
  public void apply() {
    myModuleFacet.setPlanModelReference(myPlanPanel.getPlanModel());
  }

  /*package*/ final static class LazyVisibleDepsScope implements SearchScope {
    private final SRepository myRepository;
    private final SModule myModule;
    private VisibleDepsSearchScope myDelegate;

    public LazyVisibleDepsScope(SRepository repository, SModule module) {
      myRepository = repository;
      myModule = module;
    }

    private void init() {
      // don't deal with multi-threading, the scope is local for single thread
      if (myDelegate == null) {
        myDelegate = new VisibleDepsSearchScope(myRepository, myModule);
      }
    }

    @NotNull
    @Override
    public Iterable<SModule> getModules() {
      init();
      return myDelegate.getModules();
    }

    @NotNull
    @Override
    public Iterable<SModel> getModels() {
      init();
      return myDelegate.getModels();
    }

    @Nullable
    @Override
    public SModel resolve(@NotNull SModelReference reference) {
      init();
      return myDelegate.resolve(reference);
    }

    @Nullable
    @Override
    public SModule resolve(@NotNull SModuleReference reference) {
      init();
      return myDelegate.resolve(reference);
    }

    @Nullable
    @Override
    public SNode resolve(@NotNull SNodeReference reference) {
      init();
      return myDelegate.resolve(reference);
    }
  }
}
