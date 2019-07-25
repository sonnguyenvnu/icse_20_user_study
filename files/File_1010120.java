/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.ide.findusages.model.scopes;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.project.Project;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// FIXME likely we don't need distinct ModulesScope and ModelsScope, everything shall be part of FindUsagesScope
public class ModelsScope extends FindUsagesScope {
  private static final String MODEL_ID = "ref";
  private static final String MODEL_TAG = "model";

  public ModelsScope(@NotNull Iterable<? extends SModel> models) {
    for (SModel model : models) {
      if (model != null) {
        addModel(model);
      }
    }
  }

  public ModelsScope(SModel... models) {
    this(Arrays.asList(models));
  }


  public ModelsScope(Element element, Project project) throws CantLoadSomethingException {
    this(resolveModels(element, project.getRepository()));
  }

  @NotNull
  @Override
  public Iterable<SModule> getModules() {
    // FIXME shall return modules of the models it was initialized with
    // we've already collected required modules in the superclass, and it's safe to return scope here as nobody has been using this method anyway
    throw new UnsupportedOperationException();
  }

  private static List<SModel> resolveModels(Element element, SRepository repo) throws CantLoadSomethingException {
    List<SModel> result = new ArrayList<>();
    final Logger log = LogManager.getLogger(ModelsScope.class);
    for (Element modelXml : element.getChildren(MODEL_TAG)) {
      try {
        final String modelRef = modelXml.getAttributeValue(MODEL_ID);
        if (modelRef == null) {
          continue;
        }
        SModelReference mr = PersistenceFacade.getInstance().createModelReference(modelRef);
        final SModel model = mr.resolve(repo);
        if (model != null) {
          result.add(model);
        } else {
          log.warn("model not found " + modelRef);
        }
      } catch (IllegalArgumentException e) {
        throw new CantLoadSomethingException(e);
      }
    }
    return result;
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    for (SModel model : myModels) {
      Element modelXml = new Element(MODEL_TAG);
      modelXml.setAttribute(MODEL_ID, PersistenceFacade.getInstance().asString(model.getReference()));
      element.addContent(modelXml);
    }
  }

  public String toString() {
    return "model scope";
  }
}
