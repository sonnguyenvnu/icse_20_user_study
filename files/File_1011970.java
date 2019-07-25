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
package jetbrains.mps.nodefs;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.DeprecatedVirtualFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.LocalTimeCounter;
import jetbrains.mps.ide.MPSCoreComponents;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.smodel.RepoListenerRegistrar;
import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.smodel.event.NodeChangeCollector;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.event.AbstractModelChangeEvent;
import org.jetbrains.mps.openapi.event.SNodeAddEvent;
import org.jetbrains.mps.openapi.event.SNodeRemoveEvent;
import org.jetbrains.mps.openapi.event.SPropertyChangeEvent;
import org.jetbrains.mps.openapi.event.SReferenceChangeEvent;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SModule;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.module.SRepositoryContentAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NodeVirtualFileSystem extends DeprecatedVirtualFileSystem implements Disposable {

  public static NodeVirtualFileSystem getInstance() {
    return (NodeVirtualFileSystem) VirtualFileManager.getInstance().getFileSystem(NodeVirtualFileSystem.PROTOCOL);
  }
  public static final String PROTOCOL = "mps";

  /*
   * For transition period, left container of virtual files coming from MPSModuleRepository.getInstance(), and use it
   * as default when supplied repository is not found (regardless of whether supplied repo matches MPSModuleRepository instance) for
   * compatibility with existing code, that doesn't manage SRepository well. Shall drop as soon as MPSModuleRepository instance is history
   * (or at least managed and not exposed to user code).
   */
  @ToRemove(version = 3.4)
  private final RepositoryVirtualFiles myGlobalRepoFiles =
      new RepositoryVirtualFiles(this,
                                 ApplicationManager.getApplication().getComponent(MPSCoreComponents.class).getModuleRepository());;

  private final Object myRepoVFLock = new Object();
  // I don't expect this collection to grow significantly, hence just List
  private final List<RepositoryVirtualFiles> myPerRepositoryFiles = new CopyOnWriteArrayList<>();
  private final Map<RepositoryVirtualFiles, MyRepositoryListener> myFiles2ListenerMap = new HashMap<>();
  private final SRepositoryContentAdapter myRepositoryListener = new MyRepositoryListener(myGlobalRepoFiles);;
  private boolean myDisposed = false;

  public NodeVirtualFileSystem() {
    new RepoListenerRegistrar(myGlobalRepoFiles.getRepository(), myRepositoryListener).attach();
  }

  void register(@NotNull RepositoryVirtualFiles repoFiles) {
    MyRepositoryListener listener;
    synchronized (myRepoVFLock) {
      // assert not more than 1 file container per repository
      RepositoryVirtualFiles existing = findForRepository(repoFiles.getRepository());
      if (existing != null) {
        throw new IllegalArgumentException("Attempt to register another VirtualFile container for the same repository");
      }
      // sort of stack, most recent first. just for fun, no hidden assumptions.
      myPerRepositoryFiles.add(0, repoFiles);
      listener = new MyRepositoryListener(repoFiles);
      myFiles2ListenerMap.put(repoFiles, listener);
    }
    new RepoListenerRegistrar(repoFiles.getRepository(), listener).attach();
  }

  void unregister(@NotNull RepositoryVirtualFiles repoFiles) {
    MyRepositoryListener listener;
    synchronized (myRepoVFLock) {
      myPerRepositoryFiles.remove(repoFiles);
      listener = myFiles2ListenerMap.remove(repoFiles);
    }
    if (listener != null) {
      new RepoListenerRegistrar(repoFiles.getRepository(), listener).detach();
    }
  }

  public MPSNodeVirtualFile getFileFor(@NotNull SRepository repository, @NotNull final SNode node) {
    return getFileFor(repository, node.getReference());
  }

  public MPSNodeVirtualFile getFileFor(@NotNull SRepository repository, @NotNull final SNodeReference nodePointer) {
    final RepositoryVirtualFiles rvf = findForRepository(repository);
    return rvf != null ? rvf.getFileFor(nodePointer) : myGlobalRepoFiles.getFileFor(nodePointer);
  }

  public MPSModelVirtualFile getFileFor(@NotNull SRepository repository, @NotNull final SModelReference modelReference) {
    final RepositoryVirtualFiles rvf = findForRepository(repository);
    return rvf != null ? rvf.getFileFor(modelReference) : myGlobalRepoFiles.getFileFor(modelReference);
  }

  @Nullable
  private RepositoryVirtualFiles findForRepository(@NotNull SRepository repo) {
    synchronized (myRepoVFLock) {
      for (RepositoryVirtualFiles rvf : myPerRepositoryFiles) {
        if (repo.equals(rvf.getRepository())) {
          return rvf;
        }
      }
    }
    return null;
  }

  @Override
  public void dispose() {
    new RepoListenerRegistrar(myGlobalRepoFiles.getRepository(), myRepositoryListener).detach();
    myDisposed = true;
  }

  @Override
  @NotNull
  @NonNls
  public String getProtocol() {
    return PROTOCOL;
  }

  @Override
  @Nullable
  public VirtualFile findFileByPath(final @NotNull @NonNls String path) {
    for (RepositoryVirtualFiles rvf : myPerRepositoryFiles) { // going by snapshot here and checking all persisted repositories
      VirtualFile file = new ModelAccessHelper(rvf.getRepository()).runReadAction(() -> {
        synchronized (myRepoVFLock) {
          if (myPerRepositoryFiles.contains(rvf)) { // double check
            return rvf.findFileByPath(path);
          }
          return null;
        }
      });
      if (file != null) {
        return file;
      }
    }
    return new ModelAccessHelper(myGlobalRepoFiles.getRepository()).runReadAction(() -> myGlobalRepoFiles.findFileByPath(path));
  }

  @Override
  public void refresh(boolean asynchronous) {
    // no-op
  }

  @Override
  @Nullable
  public VirtualFile refreshAndFindFileByPath(@NotNull String path) {
    return null;
  }

  private void updateModificationStamp(Collection<MPSNodeVirtualFile> files) {
    // identical timestamp for all roots touched simultaneously
    final long vfsStamp = LocalTimeCounter.currentTime();
    final long localStamp = System.currentTimeMillis();
    for (MPSNodeVirtualFile vf : files) {
      vf.setModificationStamp(vfsStamp);
      vf.setTimeStamp(localStamp);
    }
  }

  private class MyRepositoryListener extends SRepositoryContentAdapter {
    private final RepositoryVirtualFiles myRepoFiles;
    private final NodeChangeCollector myChangeCollector = new NodeChangeCollector();

    /**
     * FIXME the only reason we don't use single listener instance (we can obtain proper SRepository from the change event's model/node)
     * FIXME is that our project repository implementation is not capable of event sending, all events come from global repository.
     *       Thus, it would be impossible to find proper RepositoryVirtualFiles instance. Shall fix ProjectRepository and its base impl
     *       to send events on its own.
     */
    public MyRepositoryListener(RepositoryVirtualFiles repoFiles) {
      myRepoFiles = repoFiles;
    }

    @Override
    protected boolean isIncluded(SModule module) {
      return !module.isReadOnly();
    }

    @Override
    protected void startListening(SModel model) {
      // we listen to SModelListener#modelReplaced
      model.addModelListener(this);
      // we care about node changes
      model.addChangeListener(this);
    }

    @Override
    protected void stopListening(SModel model) {
      model.removeChangeListener(this);
      model.removeModelListener(this);
      forget(model);
    }

    // XXX I keep this method instead of direct access to myRepoFiles field in a desperate hope to have single repo listener some day,
    //     which would pick RVF instance based on model's repository. And that's the reason I check for null return value
    @Nullable
    private RepositoryVirtualFiles findRepoFiles(SModel m) {
      if (m.getRepository() == null) {
        return null;
      }
      return findRepoFiles(m.getRepository());
    }

    @Nullable
    private RepositoryVirtualFiles findRepoFiles(SRepository repository) {
      // TODO single listener instance and find RVF by repo
      return myRepoFiles;
    }

    private void forget(SModel modelDescriptor) {
      final RepositoryVirtualFiles rvf = findRepoFiles(modelDescriptor);
      if (rvf == null) {
        return;
      }
      VFSNotifier vfsNotifier = rvf.getNotifier(new VFSNotifier(rvf));
      vfsNotifier.deleted(rvf.getKnownVirtualFilesIn(modelDescriptor.getReference()));
      vfsNotifier.execute();
    }

    // SModelListener#modelReplaced
    @Override
    public void modelReplaced(SModel md) {
      final RepositoryVirtualFiles rvf = findRepoFiles(md);
      if (rvf == null) {
        return;
      }
      final Collection<MPSNodeVirtualFile> filesInModel = rvf.getKnownVirtualFilesIn(md.getReference());
      updateModificationStamp(filesInModel);

      Collection<MPSNodeVirtualFile> deletedFiles = new ArrayList<>();
      Collection<MPSNodeVirtualFile> changedFiles = new ArrayList<>();
      for (MPSNodeVirtualFile vf : filesInModel) {
        // XXX reconsider vf.getNode() (with SRepository in file construction time), vf.getNode(myRepository) and explicit resolve here
        if (vf.getNode() == null) {
          deletedFiles.add(vf);
        } else {
          changedFiles.add(vf);
        }
      }
      VFSNotifier vfsNotifier = rvf.getNotifier(new VFSNotifier(rvf));
      vfsNotifier.deleted(deletedFiles);
      vfsNotifier.changed(changedFiles);
      vfsNotifier.execute();
    }

    @Override
    public void commandStarted(SRepository repository) {
      myChangeCollector.start();
    }

    @Override
    public void commandFinished(SRepository repository) {
      myChangeCollector.stop();
      final List<AbstractModelChangeEvent> events = myChangeCollector.purge();
      final RepositoryVirtualFiles rvf = findRepoFiles(repository);
      if (rvf == null || events.isEmpty()) {
        return;
      }
      Collection<MPSNodeVirtualFile> deletedFiles = new ArrayList<>();
      Collection<MPSNodeVirtualFile> changedFiles = new ArrayList<>();
      for (AbstractModelChangeEvent evt : events) {
        if (evt instanceof SPropertyChangeEvent) {
          // candidate for rename
          MPSNodeVirtualFile vf = rvf.getVirtualFile(((SPropertyChangeEvent) evt).getNode().getReference());
          if (vf != null) {
            changedFiles.add(vf);
          }
        } else if (evt instanceof SNodeRemoveEvent) {
          // SNode.getReference() for deleted node produces invalid pointer
          MPSNodeVirtualFile vf = rvf.getVirtualFile(new SNodePointer(evt.getModel().getReference(), ((SNodeRemoveEvent) evt).getChild().getNodeId()));
          if (vf != null) {
            deletedFiles.add(vf);
          }
        } else if (evt instanceof SNodeAddEvent) {
          // SNode.getReference() for (later) deleted node produces invalid pointer
          MPSNodeVirtualFile vf = rvf.getVirtualFile(new SNodePointer(evt.getModel().getReference(), ((SNodeAddEvent) evt).getChild().getNodeId()));
          if (vf != null) {
            deletedFiles.remove(vf);
          }

        }
      }
      VFSNotifier vfsNotifier = rvf.getNotifier(new VFSNotifier(rvf));
      vfsNotifier.deleted(deletedFiles);
      vfsNotifier.changed(changedFiles);
      vfsNotifier.execute();
    }

    @Override
    public void propertyChanged(@NotNull SPropertyChangeEvent event) {
      updateFileTimestampOfAffectedNodes(event, event.getNode().getReference(), new SNodePointer(event.getNode().getContainingRoot()));
      myChangeCollector.propertyChanged(event);
    }

    @Override
    public void referenceChanged(@NotNull SReferenceChangeEvent event) {
      updateFileTimestampOfAffectedNodes(event, event.getNode().getReference(), new SNodePointer(event.getNode().getContainingRoot()));
    }

    @Override
    public void nodeAdded(@NotNull SNodeAddEvent event) {
      if (event.isRoot()) {
        // added root of no interest - there could be no file for it yet.
        return;
      }
      final SNode affectedNode = event.getParent();
      updateFileTimestampOfAffectedNodes(event, new SNodePointer(affectedNode), new SNodePointer(affectedNode.getContainingRoot()));
    }

    @Override
    public void nodeRemoved(@NotNull SNodeRemoveEvent event) {
      // SNode.getReference() for deleted node produces invalid pointer
      final SNodeReference removedNode = new SNodePointer(event.getModel().getReference(), event.getChild().getNodeId());
      updateFileTimestampOfAffectedNodes(event, removedNode, event.isRoot() ? removedNode : new SNodePointer(event.getParent().getContainingRoot()));
      myChangeCollector.nodeRemoved(event);
    }

    /*
     * SModelAdapter that used to update timestamps was deliberately extracted out of end of command listener. Guess, either to
     * update TS immediately or to support multiple TS changes within single command. That's why I keep this immediate TS update approach
     * in the new repository listener, too.
     *
     * XXX 1. Do we need to update TS on model imports change? Present openapi listener doesn't support these changes, but old code didn't care either
     * XXX 2. Why don't we update TS of MPSModelVirtualFile?
     */
    private void updateFileTimestampOfAffectedNodes(AbstractModelChangeEvent event, /*not null*/ SNodeReference changed, @Nullable SNodeReference root) {
      final RepositoryVirtualFiles rvf = findRepoFiles(event.getModel());
      if (rvf == null) {
        return;
      }
      ArrayList<MPSNodeVirtualFile> files = new ArrayList<>(2);
      final MPSNodeVirtualFile vf1 = rvf.getVirtualFile(changed);
      if (vf1 != null) {
        files.add(vf1);
      }
      if (root != null && root != changed) {
        MPSNodeVirtualFile vf2 = rvf.getVirtualFile(root);
        if (vf2 != null && !vf2.equals(vf1)) {
          files.add(vf2);
        }
      }
      updateModificationStamp(files);
    }
  }

  private class VFSNotifier implements Runnable {
    private final RepositoryVirtualFiles mySource;
    private final Set<MPSNodeVirtualFile> myDeletedFiles = new HashSet<>();
    private final Set<MPSNodeVirtualFile> myChangedFiles = new HashSet<>();
    private final AtomicBoolean myPendingChanges = new AtomicBoolean();

    public VFSNotifier(@NotNull RepositoryVirtualFiles source) {
      mySource = source;
    }

    public synchronized void deleted(Collection<MPSNodeVirtualFile> deletedFiles) {
      if (!deletedFiles.isEmpty()) {
        myPendingChanges.set(true);
      }
      myDeletedFiles.addAll(deletedFiles);
    }

    public synchronized void changed(Collection<MPSNodeVirtualFile> changed) {
      if (!changed.isEmpty()) {
        myPendingChanges.set(true);
      }
      myChangedFiles.addAll(changed);
    }

    /**
     * Asynchronous invocation does not guarantee that node reference will persist in the given repository.
     * However Artem proposed to get rid of SNodeReference#resolve at all.
     */
    public void execute() {
      if (hasPendingNotifications()) {
        mySource.scheduleNotifier(this);
      }
    }

    @Override
    public void run() {
      if (myDisposed) {
        return;
      }
      ArrayList<MPSNodeVirtualFile> deletedFiles;
      ArrayList<MPSNodeVirtualFile> changedFiles;
      synchronized (this) {
        deletedFiles = new ArrayList<>(myDeletedFiles);
        changedFiles = new ArrayList<>(myChangedFiles);
        myDeletedFiles.clear();
        myChangedFiles.clear();
      }

      // notifier is shared, it's possible to get both changed and deleted notification for the same file
      // no reason to report changes for deleted.
      changedFiles.removeAll(deletedFiles);

      for (MPSNodeVirtualFile deletedFile : deletedFiles) {
        fireBeforeFileDeletion(this, deletedFile);
        deletedFile.invalidate();
        fireFileDeleted(this, deletedFile, deletedFile.getName(), null);
      }

      for (MPSNodeVirtualFile changedFile : changedFiles) {
        String oldName = changedFile.getName();
        changedFile.updateFields();
        String newName = changedFile.getName();
        if (!oldName.equals(newName)) {
          // XXX this effectively reverts 0ec4b371f9acef4c82b644dfa3a295961b515efc, I wonder what's the reason not to send file rename events?
          firePropertyChanged(this, changedFile, VirtualFile.PROP_NAME, oldName, newName);
        }
      }
    }

    private boolean hasPendingNotifications() {
      return myPendingChanges.get();
    }
  }
}
