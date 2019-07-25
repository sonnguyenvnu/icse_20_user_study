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
package jetbrains.mps.extapi.module;

import jetbrains.mps.classloading.DumbIdeaPluginFacet;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.project.facets.JavaModuleFacet;
import jetbrains.mps.project.facets.JavaModuleFacetImpl;
import jetbrains.mps.project.facets.TestsFacet;
import jetbrains.mps.project.facets.TestsFacetImpl;
import jetbrains.mps.smodel.BootstrapLanguages;
import jetbrains.mps.util.containers.MultiMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SLanguage;
import org.jetbrains.mps.openapi.module.FacetsFacade;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SModuleFacet;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * evgeny, 2/27/13
 */
public class FacetsRegistry extends FacetsFacade implements CoreComponent {
  private final FacetFactory TESTS_FACET_FACTORY = new FacetFactory() {
    @Override
    public SModuleFacet create(@NotNull SModule module) {
      final TestsFacetImpl rv = new TestsFacetImpl();
      rv.setModule(module);
      return rv;
    }
  };

  private final FacetFactory JAVA_MODULE_FACET_FACTORY = new FacetFactory() {
    @Override
    public SModuleFacet create(@NotNull SModule module) {
      final JavaModuleFacetImpl rv = new JavaModuleFacetImpl();
      rv.setModule(module);
      return rv;
    }
  };

  private FacetFactory DUMB_IDEA_PLUGIN_FACET_FACTORY;

  private MultiMap<String, String> myLanguageToFacetTypes = new MultiMap<>();

  private Map<FacetFactory, String> myFactoryType = new HashMap<>();
  private Map<String, FacetFactory> myFacetsByType = new HashMap<>();

  @Override
  public Set<String> getFacetTypes() {
    return myFacetsByType.keySet();
  }

  @Override
  public Set<String> getApplicableFacetTypes(Collection<SLanguage> usedLanguages) {
    Set<String> result = new LinkedHashSet<>();
    for (SLanguage lang : usedLanguages) {
      result.addAll(myLanguageToFacetTypes.get(lang.getQualifiedName()));
    }
    return result;
  }

  @Override
  public void registerLanguageFacet(@NotNull SLanguage language, String facetType) {
    if (!(myFacetsByType.containsKey(facetType))) {
      throw new IllegalArgumentException("unknown facet type");
    }
    myLanguageToFacetTypes.putValue(language.getQualifiedName(), facetType);
  }

  @Override
  public void unregisterLanguageFacet(@NotNull SLanguage language, String facetType) {
    myLanguageToFacetTypes.removeValue(language.getQualifiedName(), facetType);
  }

  @Nullable
  @Override
  public FacetFactory getFacetFactory(String facetType) {
    return myFacetsByType.get(facetType);
  }

  @Override
  public void addFactory(@NotNull String facetType, FacetFactory factory) {
    if (myFactoryType.containsKey(factory)) {
      throw new IllegalStateException("factory is already registered");
    }
    myFactoryType.put(factory, facetType);
    myFacetsByType.put(facetType, factory);
  }

  @Override
  public void removeFactory(FacetFactory factory) {
    String type = myFactoryType.remove(factory);
    if (type != null) {
      myFacetsByType.remove(type);
    }
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }
    INSTANCE = this;

    addFactory(JavaModuleFacet.FACET_TYPE, JAVA_MODULE_FACET_FACTORY);
    addFactory(TestsFacet.FACET_TYPE, TESTS_FACET_FACTORY);
    setUpDumbIdeaFacet();

    registerLanguageFacet(BootstrapLanguages.getBaseLang(), JavaModuleFacet.FACET_TYPE);
  }

  private void setUpDumbIdeaFacet() {
    FacetFactory existingFactory = getFacetFactory(DumbIdeaPluginFacet.FACET_TYPE);
    if (existingFactory == null) {
      DUMB_IDEA_PLUGIN_FACET_FACTORY = new FacetFactory() {
        @Override
        public SModuleFacet create(@NotNull SModule module) {
          final DumbIdeaPluginFacet rv = new DumbIdeaPluginFacet();
          rv.setModule(module);
          return rv;
        }
      };
      addFactory(DumbIdeaPluginFacet.FACET_TYPE, DUMB_IDEA_PLUGIN_FACET_FACTORY);
    }
  }

  @Override
  public void dispose() {
    unregisterLanguageFacet(BootstrapLanguages.getBaseLang(), JavaModuleFacet.FACET_TYPE);
    if (DUMB_IDEA_PLUGIN_FACET_FACTORY != null) {
      removeFactory(DUMB_IDEA_PLUGIN_FACET_FACTORY);
    }
    removeFactory(TESTS_FACET_FACTORY);
    removeFactory(JAVA_MODULE_FACET_FACTORY);
    INSTANCE = null;
  }
}
