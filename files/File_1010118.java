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
package jetbrains.mps.ide.findusages.model.scopes;

import jetbrains.mps.ide.findusages.CantLoadSomethingException;
import jetbrains.mps.ide.findusages.CantSaveSomethingException;
import jetbrains.mps.ide.findusages.IExternalizeable;
import jetbrains.mps.project.Project;
import jetbrains.mps.smodel.BaseScope;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SearchScope;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class FindUsagesScope extends BaseScope implements SearchScope, IExternalizeable {
  private static final String SCOPE_CLASS_ATTR = "scope_class";
  private static final String SCOPE_TAG = "scope";

  protected final Set<SModule> myModules = new LinkedHashSet<>();
  protected final Set<SModel> myModels = new LinkedHashSet<>();

  @NotNull
  @Override
  public Iterable<SModule> getModules() {
    return myModules;
  }

  @NotNull
  @Override
  public Iterable<SModel> getModels() {
    return myModels;
  }

  /**
   * Register module and models it owns in the scope
   */
  protected void addModule(@NotNull SModule module) {
    primAddModule(module);
    for (SModel model : module.getModels()) {
      primAddModel(model);
    }
    scopeChanged();
  }

  protected void addModel(@NotNull SModel model) {
    primAddModel(model);
    final SModule module = model.getModule();
    if (module != null) {
      primAddModule(module);
    }
    scopeChanged();
  }

  protected final void primAddModel(SModel model) {
    myModels.add(model);
  }

  protected final void primAddModule(SModule module) {
    myModules.add(module);
  }

  /**
   * Subclasses shall override to react to scope change
   */
  protected void scopeChanged() {
    // no-op
  }

  @Override
  public void read(Element element, Project project) throws CantLoadSomethingException {
    // use constructor(Element, Project) instead!
    throw new UnsupportedOperationException();
  }

  @Override
  public void write(Element element, Project project) throws CantSaveSomethingException {
    // do nothing by default
  }

  public static FindUsagesScope load(Element element, Project project) throws CantLoadSomethingException {
    Element scopeXml = element.getChild(SCOPE_TAG);
    String scopeClass = scopeXml.getAttributeValue(SCOPE_CLASS_ATTR);
    try {
      return (FindUsagesScope) Class.forName(scopeClass).getConstructor(Element.class, Project.class).newInstance(scopeXml, project);
    } catch (Exception e) {
      throw new CantLoadSomethingException(e);
    }
  }

  public static void save(FindUsagesScope scope, Element element, Project project) throws CantSaveSomethingException {
    Element scopeXml = new Element(SCOPE_TAG);
    scopeXml.setAttribute(SCOPE_CLASS_ATTR, scope.getClass().getName());
    scope.write(scopeXml, project);
    element.addContent(scopeXml);
  }
}
