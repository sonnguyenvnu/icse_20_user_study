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
package jetbrains.mps.ide.findusages;

import gnu.trove.TIntArrayList;
import gnu.trove.TObjectIntHashMap;
import jetbrains.mps.components.CoreComponent;
import jetbrains.mps.ide.findusages.findalgorithm.finders.IInterfacedFinder;
import jetbrains.mps.smodel.LanguageAspect;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.language.LanguageRegistry;
import jetbrains.mps.smodel.language.LanguageRegistryListener;
import jetbrains.mps.smodel.language.LanguageRuntime;
import jetbrains.mps.smodel.runtime.FindUsageAspectDescriptor;
import jetbrains.mps.smodel.runtime.FinderRegistry;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public final class FindersManager implements CoreComponent, LanguageRegistryListener {
  private static final Logger LOG = LogManager.getLogger(FindersManager.class.getName());

  private static FindersManager INSTANCE;

  public static FindersManager getInstance() {
    return INSTANCE;
  }

  // XXX the only place I use SLanguageId map key is compatibility with legacy #addFinder(), to match SModuleReference to LanguageRuntime
  private final Map<SLanguageId, LanguageFinders> myLanguageFindersMap = new HashMap<>();
  private boolean myLoaded = false;

  private LanguageRegistry myLanguageRegistry;

  public FindersManager(LanguageRegistry languageRegistry) {
    myLanguageRegistry = languageRegistry;
  }

  @Override
  public void init() {
    if (INSTANCE != null) {
      throw new IllegalStateException("double initialization");
    }

    INSTANCE = this;
    myLanguageRegistry.addRegistryListener(this);
  }

  @Override
  public void dispose() {
    myLanguageRegistry.removeRegistryListener(this);
    INSTANCE = null;
  }

  public Set<IInterfacedFinder> getAvailableFinders(final SNode node) {
    checkLoaded();
    final Set<IInterfacedFinder> result = new HashSet<>();

    for (LanguageFinders lf : myLanguageFindersMap.values()) {
      try {
        lf.findersForConcept(node.getConcept()).filter(finder -> finder.isVisible(node) && finder.isApplicable(node)).forEach(result::add);
      } catch (Throwable t) {
        LOG.error("Finder's isApplicable method failed " + t.getMessage(), t);
      }
    }
    return Collections.unmodifiableSet(result);
  }

  /**
   * @param finderIdentity at the moment, fqn of finder implementation class. NOTE, it's not used for classloading as is, merely as identifier to find
   *                       registered implementation
   * @return {@code null} if no finder with supplied identity found or identity is null.
   */
  @Nullable
  public IInterfacedFinder getFinder(@Nullable String finderIdentity) {
    // Function.identity magic is to convey the idea finderIdentity is an identity, not a class name.
    // and to avoid IDEA's warning, too ;)
    final String className = Function.<String>identity().apply(finderIdentity);
    if (className == null) {
      return null;
    }
    checkLoaded();
    final String aspectNameWithDots = '.' + LanguageAspect.FIND_USAGES.getName() + '.';
    int aspectNamePos = className.lastIndexOf(aspectNameWithDots);
    final String cnSuffix = "_Finder";
    if (aspectNamePos == -1 || !className.endsWith(cnSuffix)) {
      return null;
    }
    final String declaringLanguageName = className.substring(0, aspectNamePos);
    // finderMangledName == NameUtil.toValidIdentifier(finder.name)
    final String finderMangledName = className.substring(aspectNamePos + aspectNameWithDots.length(), className.length() - cnSuffix.length());

    for (LanguageFinders lf : myLanguageFindersMap.values()) {
      if (!lf.matchesLanguage(declaringLanguageName)) {
        continue;
      }
      return lf.findByMangledName(finderMangledName);
    }
    return null;
  }

  //-------------reloading stuff----------------

  private void checkLoaded() {
    if (myLoaded) {
      return;
    }
    load();
  }

  private synchronized void load() {
    // withAvailableLanguages doesn't require model read
    // yet we may get here from different threads (e.g. highlighter and main/EDT, MPS-29909), have to be careful not to initialize twice
    if (myLoaded) {
      return;
    }
    myLanguageRegistry.withAvailableLanguages(this::initFindersDescriptor);
    myLoaded = true;
  }

  private void clear() {
    myLanguageFindersMap.clear();
    myLoaded = false;
  }

  private void initFindersDescriptor(LanguageRuntime language) {
    try {
      FindUsageAspectDescriptor descr = language.getAspect(FindUsageAspectDescriptor.class);
      if (descr != null) {
        // FIXME shall refactor load/clear mechanism to drop/load relevant LanguageFinder instances only.
        assert !myLanguageFindersMap.containsKey(language.getId()) : "At the moment, there's clear() once any language is unloaded, we shall not replace finders.";
        LanguageFinders finders = new LanguageFinders(language);
        myLanguageFindersMap.put(language.getId(), finders);
        descr.init(finders);
      }
    } catch (Throwable throwable) {
      LOG.error("Error while initializing find usages descriptor for language " + language.getNamespace(), throwable);
    }
  }

  @Override
  public void afterLanguagesLoaded(Iterable<LanguageRuntime> languages) {
  }

  @Override
  public void beforeLanguagesUnloaded(Iterable<LanguageRuntime> languages) {
    // FIXME shall drop relevant LanguageFinder instances only!
    // However myNodesByFinder is global and would either keep stale entries or cleared altogether on any reload.
    // Perhaps, shall drop it as it's not vital to have getDeclarationNode for legacy (non-migrated) finders.
    clear();
  }

  // XXX doesn't care about threading, although likely should
  private static final class LanguageFinders implements FinderRegistry {
    private final LanguageRuntime myLanguageRuntime;
    private final Map<SAbstractConcept, TIntArrayList> myFinders = new HashMap<>();
    private final TObjectIntHashMap<String> myNameToFinder = new TObjectIntHashMap<>();

    LanguageFinders(LanguageRuntime lr) {
      myLanguageRuntime = lr;
    }

    @Override
    public void add(@NotNull SAbstractConcept concept, int identityToken, @NotNull String mangledName) {
      TIntArrayList finderTokens = myFinders.get(concept);
      if (finderTokens == null) {
        myFinders.put(concept, finderTokens = new TIntArrayList());
      }
      if (!finderTokens.contains(identityToken)) {
        finderTokens.add(identityToken);
      }
      myNameToFinder.put(mangledName, identityToken);
    }

    boolean matchesLanguage(String namespace) {
      return myLanguageRuntime.getNamespace().equals(namespace);
    }

    IInterfacedFinder findByMangledName(String finderMangledName) {
      int token = myNameToFinder.get(finderMangledName);
      return token >= 0 ? instantiate(token) : null;
    }

    // XXX findersForNode(SNode) instead, to perform filtering isVisible+isApplicable here as well?
    Stream<IInterfacedFinder> findersForConcept(SAbstractConcept c) {
      return myFinders.keySet().stream().filter(c::isSubConceptOf).flatMap(concept -> instantiate(myFinders.get(concept)));
    }

    private IInterfacedFinder instantiate(int token) {
      FindUsageAspectDescriptor descr = myLanguageRuntime.getAspect(FindUsageAspectDescriptor.class);
      // could have passed descr instance as cons argument, otoh LR keeps its instance anyway, why bother.
      assert descr != null;
      return descr.instantiate(token);
    }

    private Stream<IInterfacedFinder> instantiate(TIntArrayList tokens) {
      FindUsageAspectDescriptor descr = myLanguageRuntime.getAspect(FindUsageAspectDescriptor.class);
      assert descr != null;
      return Arrays.stream(tokens.toNativeArray()).mapToObj(descr::instantiate);
    }
  }
}
