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
import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager;
import jetbrains.mps.project.dependency.GlobalModuleDependenciesManager.Deptype;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModulesScope extends FindUsagesScope {
  private static final String MODULE_ID = "ref";
  private static final String MODULE_TAG = "module";
  private boolean myRespectVisible = false;
  private Map<SModuleReference, SModule> myResolveScope;

  public ModulesScope(@NotNull Iterable<? extends SModule> modules) {
    for (SModule module : modules) {
      if (module != null) {
        addModule(module);
      }
    }
  }

  public ModulesScope(@NotNull SModule... modules) {
    this(Arrays.asList(modules));
  }

  /**
   * Tells whether to use only specified modules as reference resolution scope for {@link #resolve(org.jetbrains.mps.openapi.module.SModuleReference)},
   * or all visible/accessible modules shall be considered
   */
  public void resolveRespectsAllVisible(boolean respectVisible) {
    myRespectVisible = respectVisible;
  }

  @Override
  public SModule resolve(@NotNull SModuleReference reference) {
    if (myResolveScope == null) {
      final Collection<SModule> allVisible;
      if (myRespectVisible) {
        allVisible = new GlobalModuleDependenciesManager(myModules).getModules(Deptype.VISIBLE);
      } else {
        allVisible = myModules;
      }
      myResolveScope = new HashMap<>(allVisible.size() * 4 / 3);
      for (SModule m : allVisible) {
        myResolveScope.put(m.getModuleReference(), m);
      }
    }
    return myResolveScope.get(reference);
  }

  @Override
  protected void scopeChanged() {

  }

  public ModulesScope(Element element, Project project) throws CantLoadSomethingException {
    this(resolveModules(element, project.getRepository()));
  }

  private static Iterable<SModule> resolveModules(Element element, SRepository repo) throws CantLoadSomethingException {
    List<SModule> result = new ArrayList<>();
    final Logger log = LogManager.getLogger(ModulesScope.class);
    for (Element moduleXml : element.getChildren(MODULE_TAG)) {
      try {
        final String moduleRef = moduleXml.getAttributeValue(MODULE_ID);
        if (moduleRef == null) {
          continue;
        }
        SModuleReference mr = PersistenceFacade.getInstance().createModuleReference(moduleRef);
        final SModule module = mr.resolve(repo);
        if (module != null) {
          result.add(module);
        } else {
          log.warn("module not found " + moduleRef);
        }
      } catch (IllegalArgumentException e) {
        throw new CantLoadSomethingException(e);
      }
    }
    return result;
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    for (SModule module : myModules) {
      Element moduleXml = new Element(MODULE_TAG);
      moduleXml.setAttribute(MODULE_ID, PersistenceFacade.getInstance().asString(module.getModuleReference()));
      element.addContent(moduleXml);
    }
  }

  public String toString() {
    return "module scope";
  }
}
