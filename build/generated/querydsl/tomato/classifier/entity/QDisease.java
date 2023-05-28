package tomato.classifier.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDisease is a Querydsl query type for Disease
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDisease extends EntityPathBase<Disease> {

    private static final long serialVersionUID = 83048666L;

    public static final QDisease disease = new QDisease("disease");

    public final StringPath d_name = createString("d_name");

    public final StringPath id = createString("id");

    public final StringPath solution = createString("solution");

    public final StringPath src = createString("src");

    public QDisease(String variable) {
        super(Disease.class, forVariable(variable));
    }

    public QDisease(Path<? extends Disease> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDisease(PathMetadata metadata) {
        super(Disease.class, metadata);
    }

}

