package cc.mrbird.demo.filter;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author MrBird
 */
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
        // èŽ·å?–å½“å‰?æ­£åœ¨æ‰«æ??çš„ç±»çš„æ³¨è§£ä¿¡æ?¯
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // èŽ·å?–å½“å‰?æ­£åœ¨æ‰«æ??çš„ç±»çš„ç±»ä¿¡æ?¯
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // èŽ·å?–å½“å‰?æ­£åœ¨æ‰«æ??çš„ç±»çš„è·¯å¾„ç­‰ä¿¡æ?¯
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();
        return StringUtils.hasText("er");
    }
}
