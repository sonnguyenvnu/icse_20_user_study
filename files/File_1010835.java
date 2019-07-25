package jetbrains.mps.jps.build;

import com.intellij.openapi.util.io.FileUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * evgeny, 11/28/12
 */
public class MPSIdeaRefreshComponent {
    private final Object LOCK = new Object();
    private Collection<String> created = new ArrayList<String>();
    private Collection<String> removed = new ArrayList<String>();
    private Collection<String> genRoots = new ArrayList<String>();

    public void refresh(String path) {
        synchronized (LOCK) {
            created.add(path);
        }
    }

    public void removed(Collection<String> paths) {
        synchronized (LOCK) {
            removed.addAll(paths);
        }
    }

    public void addOutputRoot(String path) {
        path = FileUtil.toCanonicalPath(path);
        synchronized (LOCK) {
            genRoots.add(path);
        }
    }

    Collection<String> getFilesToRefresh() {
        Set<String> result = new HashSet<String>();
        synchronized (LOCK) {
            result.addAll(created);
            for (String r : removed) {
                for (String root : genRoots) {
                    if (FileUtil.isAncestor(root, r, false)) {
                        result.add(r);
                        break;
                    }
                }
            }
        }
        return result;
    }

}
