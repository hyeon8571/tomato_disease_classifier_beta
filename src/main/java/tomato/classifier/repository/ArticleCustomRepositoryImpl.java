package tomato.classifier.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleCustomRepositoryImpl implements ArticleCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ArticleCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.queryFactory = jpaQueryFactory;
    }
}
