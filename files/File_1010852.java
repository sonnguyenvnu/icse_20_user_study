/*
 * Copyright 2003-2014 JetBrains s.r.o.
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

package jetbrains.mps.idea.core.project;

import com.intellij.facet.FacetManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import jetbrains.mps.ide.project.ProjectHelper;
import jetbrains.mps.idea.core.facet.MPSFacet;
import jetbrains.mps.idea.core.facet.MPSFacetType;
import jetbrains.mps.smodel.DefaultSModelDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;

/**
 * Created by danilla on 12/3/14.
 */
public class UpgradeModelsPersistence implements ProjectComponent {
  // deliberately not using ModelPersistence.LAST_VERSION here not to make upgrading automatic
  private static int LAST_VERSION = 9;
  private Project myProject;

  public UpgradeModelsPersistence(Project project) {
    myProject = project;
  }

  @Override
  public void projectOpened() {
    StartupManager.getInstance(myProject).runWhenProjectIsInitialized(new Runnable() {
      @Override
      public void run() {
        ProjectHelper.getModelAccess(myProject).executeUndoTransparentCommand(new Runnable() {
          @Override
          public void run() {
            for (Module module : ModuleManager.getInstance(myProject).getModules()) {
              MPSFacet facet = FacetManager.getInstance(module).getFacetByType(MPSFacetType.ID);
              if (facet == null) {
                continue;
              }
              if(facet.getSolution() == null) {
                continue;
              }
              for (SModel model : facet.getSolution().getModels()) {
                if (!((model instanceof DefaultSModelDescriptor))) {
                  continue;
                }
                DefaultSModelDescriptor ourModel = (DefaultSModelDescriptor) model;
                if (ourModel.getPersistenceVersion() < LAST_VERSION) {
                  upgrade(ourModel);
                  Notifications.Bus.notify(new Notification("Model Persistence", "Model re-saved", "Model " + ourModel.getModelName() + " has been re-saved in newer persistence", NotificationType.INFORMATION), myProject);
                }
              }
            }
          }
        });
      }
    });
  }

  private void upgrade(DefaultSModelDescriptor model) {
    // removing self-import if any; pre persistence 9 they were very often present
    model.deleteModelImport(model.getReference());
    model.load();
    model.setPersistenceVersion(LAST_VERSION);
    model.setChanged(true);
    model.save();
  }

  @Override
  public void projectClosed() {
  }

  @Override
  public void initComponent() {
  }

  @Override
  public void disposeComponent() {
  }

  @NotNull
  @Override
  public String getComponentName() {
    return "Migrate models to persistence version 9";
  }
}
